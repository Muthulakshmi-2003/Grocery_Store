import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";

import "./Product.scss";

function Product() {
  const [products, setProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("All");

  const [searchParams] = useSearchParams();

  const search = searchParams.get("search");
  const category = searchParams.get("category");

  const BaseUrl = "/images/ProductJpg/";
  const [showLoginPopup, setShowLoginPopup] = useState(false);

  const categories = [
    {
      id: 1,
      name: "Fruits",
    },
    {
      id: 2,
      name: "Vegetables",
    },
    {
      id: 3,
      name: "Dairy",
    },
    {
      id: 4,
      name: "Packaged Foods",
    },
    {
      id: 5,
      name: "Beverages",
    },
    {
      id: 6,
      name: "Household Items",
    },
  ];

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        let response;

        if (selectedCategory != "All") {
          response = await axios.get(
            `http://localhost:8085/products/category/${selectedCategory}`,
          );
        } else if (category) {
          const selectedCat = categories.find(
            (cat) => cat.name.toLowerCase() === category.toLowerCase(),
          );
          if (selectedCat) {
            setSelectedCategory(String(selectedCat.id));

            response = await axios.get(
              `http://localhost:8085/products/category/${selectedCat.id}`,
            );
          }

          response = await axios.get(
            `http://localhost:8085/products/category/${category}`,
          );
        } else if (search) {
          response = await axios.get(
            `http://localhost:8085/products/search?name=${search}`,
          );
        } else {
          response = await axios.get(`http://localhost:8085/products`);
        }

        console.log("Product API response:", response.data);

        setProducts(response.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, [selectedCategory, category, search]);

  const handleAddToCart = (product) => {
    const user = JSON.parse(localStorage.getItem("user"));

    if (!user || !user.email) {
      setShowLoginPopup(true);
      return;
    }

    const cartKey = `cart_${user.email}`;

    const existingCart = JSON.parse(localStorage.getItem(cartKey)) || [];

    const existingProduct = existingCart.find((item) => item.id === product.id);

    if (existingProduct) {
      existingProduct.quantity += 1;
    } else {
      existingCart.push({
        ...product,
        quantity: 1,
      });
    }

    localStorage.setItem(cartKey, JSON.stringify(existingCart));

    alert("Added to cart!");
  };

  return (
    <div className="product">
      <h1>Products</h1>

      <div className="product-filter">
        <label>Filter by Category</label>

        <select
          value={selectedCategory}
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="All">All Products</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>

      {search && (
        <p>
          Search results for:
          <strong> "{search}"</strong>
        </p>
      )}

      {category && <p>Products in selected category</p>}

      <div className="product-grid">
        {products.length > 0 ? (
          products.map((product) => (
            <div className="product-card" key={product.id}>
              <img src={`${BaseUrl}${product.name}.jpg`} alt={product.name} />

              <h3>{product.name}</h3>

              <p>{product.description}</p>

              <h4>₹{product.price}</h4>

              <button onClick={() => handleAddToCart(product)}>
                Add to Cart
              </button>
            </div>
          ))
        ) : (
          <p>No products found.</p>
        )}
      </div>
      {showLoginPopup && (
        <div className="login-popup-overlay">
          <div className="login-popup">
            <h3>Login Required</h3>

            <p>Please login to add products to cart.</p>

            <button onClick={() => setShowLoginPopup(false)}>OK</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Product;
