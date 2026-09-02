import "../../styles/Variable.scss";
import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";



function Beverages(){
    const [beverages, setBeverages] = useState([]);

    useEffect(() => {

        const fetchBeverages = async () => {

            try {

                const response = await axios.get(
                    "http://localhost:8085/products/category/5"
                );

                console.log("Products:", response.data);

                setBeverages(response.data);

            } catch (error) {

                console.error("Error fetching vegetables:", error);

            }
        };

        fetchBeverages();

    }, []);

    const handleAddToCart = (product) => {

    const user = JSON.parse(localStorage.getItem("user"));

    if (!user || !user.email) {
        alert("Please login to add products to cart.");
        return;
    }

    const cartKey = `cart_${user.email}`;

    const existingCart =
        JSON.parse(localStorage.getItem(cartKey)) || [];

    const existingProduct = existingCart.find(
        item => item.id === product.id
    );

    if (existingProduct) {

        existingProduct.quantity += 1;

    } else {

        existingCart.push({
            ...product,
            quantity: 1
        });

    }

    localStorage.setItem(
        cartKey,
        JSON.stringify(existingCart)
    );

    alert("Added to cart!");
};
    const baseUrl = "/images/ProductJpg/";
    return (
        <section className="product-section">

            <div className="section-header">
                <h2>Soft Drinks </h2>

                <Link to="/products?category=5">
                    See All
                </Link>
            </div>

            <div className="product-grid">

                {beverages.slice(0, 4).map((product) => (
    <div className="product-card" key={product.id}>
        <img src={`${baseUrl}${product.name}.jpg`} alt={product.name} />
        <h3>{product.name}</h3>
        <p>{product.quantity}</p>
        <strong>₹{product.price}</strong>
        <button onClick={()=>handleAddToCart(product)}>Add</button>
    </div>
))}

            </div>

        </section>
    );
}

export default Beverages;