import { useEffect, useState } from "react";
import "./Cart.scss";
import axios from "axios";

function Cart() {
  const [cartItems, setCartItems] = useState([]);

  const BaseUrl = "/images/ProductJpg/";

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user || !user.email) {
      setCartItems([]);
      return;
    }

    const cartKey = `cart_${user.email}`;

    const cart = JSON.parse(localStorage.getItem(cartKey)) || [];

    setCartItems(cart);
  }, []);


  const increaseQuantity = (id) => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems.map((item) =>
      item.id === id
        ? {
            ...item,
            quantity: item.quantity + 1,
          }
        : item,
    );

    setCartItems(updatedCart);

    localStorage.setItem(cartKey, JSON.stringify(updatedCart));
  };


  const decreaseQuantity = (id) => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems
      .map((item) =>
        item.id === id
          ? {
              ...item,
              quantity: item.quantity - 1,
            }
          : item,
      )
      .filter((item) => item.quantity > 0);

    setCartItems(updatedCart);

    localStorage.setItem(cartKey, JSON.stringify(updatedCart));
  };


  const handleOrderNow = async (item) => {
    try {
      const orderData = {
        customerId: 103,
        items: [
          {
            productId: item.id,
            quantity: item.quantity,
          },
        ],
      };

      console.log("Sending order:", orderData);

      const response = await axios.post(
        "http://localhost:8085/orders",
        orderData,
      );

      console.log("Order created:", response.data);

      alert(`Order placed successfully! Order ID: ${response.data.orderId}`);
    } catch (error) {
      console.error("Order creation failed:", error);

      alert("Failed to place order.");
    }
  };

  const removeFromCart = (id) => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems.filter((item) => item.id !== id);

    setCartItems(updatedCart);

    localStorage.setItem(cartKey, JSON.stringify(updatedCart));
  };

  return (
    <div className="cart">
      <h1 className="cart-title">My Cart</h1>

      {cartItems.length === 0 ? (
        <p className="cart-empty">Your cart is empty</p>
      ) : (
        <div className="cart__list">
          {cartItems.map((item) => (
            <div className="cart-item" key={item.id}>
              <img
                className="cart-image"
                src={`${BaseUrl}${item.name}.jpg`}
                alt={item.name}
              />

              <div className="cart-details">
                <h2 className="cart-product-name">{item.name}</h2>

                <p className="cart-description">{item.description}</p>

                <p className="cart-category">Category: {item.category}</p>

                <p className="cart-price">Price: ₹{item.price}</p>

             

                <div className="cart-quantity">
                  <span>Quantity:</span>

                  <button
                    className="cart-quantity-button"
                    onClick={() => decreaseQuantity(item.id)}
                  >
                    −
                  </button>

                  <span className="cart-quantity-value">{item.quantity}</span>

                  <button
                    className="cart-quantity-button"
                    onClick={() => increaseQuantity(item.id)}
                  >
                    +
                  </button>
                </div>

                <p className="cart-total">
                  Total: ₹{(item.price * item.quantity).toFixed(2)}
                </p>

                <button
                  className="cart-order-button"
                  onClick={() => handleOrderNow(item)}
                >
                  Order Now
                </button>
                <button
                  className="cart-remove-btn"
                  onClick={() => removeFromCart(item.id)}
                >
                  Remove
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default Cart;
