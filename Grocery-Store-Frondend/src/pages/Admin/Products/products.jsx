import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import "./Products.scss";

function Products() {

    const [products, setProducts] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = async () => {
        try {

            const response = await axios.get(
                "http://localhost:8085/products"
            );

            console.log("Products:", response.data);

            setProducts(response.data);

        } catch (error) {

            console.error("Error fetching products:", error);

        }
    };


    const handleDelete = async (productId) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this product?"
        );

        if (!confirmDelete) {
            return;
        }

        try {

            await axios.delete(
                `http://localhost:8085/products/${productId}`
            );

            // Remove deleted product from UI
            setProducts(
                products.filter(
                    (product) => product.id !== productId
                )
            );

        } catch (error) {

            console.error("Error deleting product:", error);

        }
    };
    const baseUrl = "/images/ProductJpg";


    return (

        <div className="admin-products">

            <div className="admin-products__header">

                <h1>Product Management</h1>

                <button className="add-product-btn" onClick={()=>navigate("/admin/products/add")}>
                    + Add Product
                </button>

            </div>


            <div className="admin-products__table">

                <table>

                    <thead>

                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>

                    </thead>


                    <tbody>

                        {products.map((product) => (

                            <tr key={product.id}>

                                <td>
                                    <img
                                        src={`${baseUrl}${product.name}.jpg`}
                                        alt={product.name}
                                    />
                                </td>

                                <td>
                                    {product.name}
                                </td>

                                <td>
                                    {product.categoryId}
                                </td>

                                <td>
                                    ₹{product.price}
                                </td>
                                <td>
                                    {product.description}
                                </td>

                                <td>

                                    <button>
                                        Edit
                                    </button>

                                    <button
                                        onClick={() =>
                                            handleDelete(product.id)
                                        }
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))}

                    </tbody>

                </table>

            </div>

        </div>
    );
}

export default Products;