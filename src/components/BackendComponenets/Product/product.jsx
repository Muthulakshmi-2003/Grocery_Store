import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";

import "./Product.scss";

function Product() {

    const [products, setProducts] = useState([]);

    const [searchParams] = useSearchParams();

    const search = searchParams.get("search");
    const category = searchParams.get("category");


    useEffect(() => {

        const fetchProducts = async () => {

            try {

                let response;

                // Search products
                if (search) {

                    response = await axios.get(
                        `http://localhost:8085/products/search?name=${encodeURIComponent(search)}`
                    );

                }

                // Products by category
                else if (category) {

                    response = await axios.get(
                        `http://localhost:8085/product-service/categories/${category}`
                    );

                }

                // All products
                else {

                    response = await axios.get(
                        "http://localhost:8085/product-service/products"
                    );

                }

                setProducts(response.data);

            } catch (error) {

                console.error(
                    "Error fetching products:",
                    error
                );

            }

        };

        fetchProducts();

    }, [search, category]);


    return (
        <div className="product">

            <h1>Products</h1>

            {search && (
                <p>
                    Search results for:
                    <strong> "{search}"</strong>
                </p>
            )}

            {category && (
                <p>
                    Products in selected category
                </p>
            )}


            <div className="product__grid">

                {products.length > 0 ? (

                    products.map((product) => (

                        <div
                            className="product__card"
                            key={product.id}
                        >

                            <img
                                src={product.imageUrl}
                                alt={product.name}
                            />

                            <h3>
                                {product.name}
                            </h3>

                            <p>
                                {product.description}
                            </p>

                            <h4>
                                ₹{product.price}
                            </h4>

                            <button>
                                Add to Cart
                            </button>

                        </div>

                    ))

                ) : (

                    <p>
                        No products found.
                    </p>

                )}

            </div>

        </div>
    );
}

export default Product;