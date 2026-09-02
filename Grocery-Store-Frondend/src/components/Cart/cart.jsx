import { useEffect, useState } from "react";
import "./Cart.scss";
import axios from "axios";

function Cart() {

    const [cartItems, setCartItems] = useState([]);

    const BaseUrl = "/images/";

  useEffect(() => {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user || !user.email) {
        setCartItems([]);
        return;
    }

    const cartKey = `cart_${user.email}`;

    const cart =
        JSON.parse(localStorage.getItem(cartKey)) || [];

    setCartItems(cart);

}, []);


    // Increase quantity
   const increaseQuantity = (id) => {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems.map((item) =>
        item.id === id
            ? {
                ...item,
                quantity: item.quantity + 1
            }
            : item
    );

    setCartItems(updatedCart);

    localStorage.setItem(
        cartKey,
        JSON.stringify(updatedCart)
    );
};


    // Decrease quantity
   const decreaseQuantity = (id) => {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems
        .map((item) =>
            item.id === id
                ? {
                    ...item,
                    quantity: item.quantity - 1
                }
                : item
        )
        .filter((item) => item.quantity > 0);

    setCartItems(updatedCart);

    localStorage.setItem(
        cartKey,
        JSON.stringify(updatedCart)
    );
};

    // Order Now
    const handleOrderNow = async (item) => {

    try {

        const orderData = {
            customerId: 103,
            items: [
                {
                    productId: item.id,
                    quantity: item.quantity
                }
            ]
        };

        console.log("Sending order:", orderData);

        const response = await axios.post(
            "http://localhost:8085/orders",
            orderData
        );

        console.log("Order created:", response.data);

        alert(
            `Order placed successfully! Order ID: ${response.data.orderId}`
        );

    } catch (error) {

        console.error("Order creation failed:", error);

        alert("Failed to place order.");

    }
};

const removeFromCart = (id) => {

    const user = JSON.parse(
        localStorage.getItem("user")
    );

    if (!user?.email) return;

    const cartKey = `cart_${user.email}`;

    const updatedCart = cartItems.filter(
        (item) => item.id !== id
    );

    setCartItems(updatedCart);

    localStorage.setItem(
        cartKey,
        JSON.stringify(updatedCart)
    );
};

    return (

        <div className="cart">

            <h1 className="cart__title">
                My Cart
            </h1>

            {cartItems.length === 0 ? (

                <p className="cart__empty">
                    Your cart is empty
                </p>

            ) : (

                <div className="cart__list">

                    {cartItems.map((item) => (

                        <div
                            className="cart__item"
                            key={item.id}
                        >

                            {/* Product Image */}

                            <img
                                className="cart__image"
                                src={`${BaseUrl}${item.name}.jpg`}
                                alt={item.name}
                            />


                            {/* Product Details */}

                            <div className="cart__details">

                                <h2 className="cart__product-name">
                                    {item.name}
                                </h2>

                                <p className="cart__description">
                                    {item.description}
                                </p>

                                <p className="cart__category">
                                    Category: {item.category}
                                </p>

                                <p className="cart__price">
                                    Price: ₹{item.price}
                                </p>


                                {/* Quantity */}

                                <div className="cart__quantity">

                                    <span>
                                        Quantity:
                                    </span>

                                    <button
                                        className="cart__quantity-button"
                                        onClick={() =>
                                            decreaseQuantity(item.id)
                                        }
                                    >
                                        −
                                    </button>

                                    <span className="cart__quantity-value">
                                        {item.quantity}
                                    </span>

                                    <button
                                        className="cart__quantity-button"
                                        onClick={() =>
                                            increaseQuantity(item.id)
                                        }
                                    >
                                        +
                                    </button>

                                </div>


                                {/* Total */}

                                <p className="cart__total">

                                    Total: ₹
                                    {(item.price * item.quantity).toFixed(2)}

                                </p>


                                {/* Order Button */}

                                <button
                                    className="cart__order-button"
                                    onClick={() =>
                                        handleOrderNow(item)
                                    }
                                >
                                    Order Now
                                </button>
                                 <button
            className="cart__remove-btn"
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