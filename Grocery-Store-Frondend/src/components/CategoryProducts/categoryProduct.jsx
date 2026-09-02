import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import "../CategoryProducts/categoryProduct.scss";

function CategoryProducts() {

    const { categoryId } = useParams();

    const [products, setProducts] = useState([]);

    useEffect(() => {

        const fetchProducts = async () => {

            try {

                const response = await axios.get(
                    `http://localhost:8085/products/category/${categoryId}`
                );

                console.log("Category products:", response.data);

                setProducts(response.data);

            } catch (error) {

                console.error("Error fetching products:", error);

            }

        };

        fetchProducts();

    }, [categoryId]);

    const baseUrl = "/images/ProductJpg/";
   

    return (
        <section className="product-section">

            <h2>Vegetable Products</h2>

            <div className="product-grid">

                {products.map((product) => (
    <div className="product-card" key={product.id}>
        <img  src={`${baseUrl}${product.name}.jpg`} alt={product.name} />
        <h3>{product.name}</h3>
        <p>{product.quantity}</p>
        <strong>₹{product.price}</strong>
        <button>Add</button>
    </div>
))}

            </div>

        </section>
    );
}

export default CategoryProducts;