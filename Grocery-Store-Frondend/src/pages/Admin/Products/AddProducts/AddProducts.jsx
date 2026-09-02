import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import "./AddProducts.scss";

function AddProduct() {

    const navigate = useNavigate();

    const [product, setProduct] = useState({
        sku:"",
        name: "",
        categoryId: "",
        price: "",
        description: "",
        active: true
        
    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        setProduct({
            ...product,
            [name]: value
        });
    };


    const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        const productData = {
            sku: product.sku,
            name: product.name,
            description: product.description,
            price: Number(product.price),
            categoryId: Number(product.categoryId),
            active: true
        };

        console.log("Sending product:", productData);

        const response = await axios.post(
            "http://localhost:8085/products",
            productData
        );

        console.log("Product added:", response.data);

        alert("Product added successfully!");

        navigate("/admin/products");

    } catch (error) {

        console.error("Error adding product:", error);

        console.log(
            "Backend response:",
            error.response?.data
        );
    }
};

    return (

        <div className="add-product">

            <h1>Add Product</h1>

            <form onSubmit={handleSubmit}>

                <div className="form-group">

    <label>SKU</label>

    <input
        type="text"
        name="sku"
        value={product.sku}
        onChange={handleChange}
        placeholder="Enter product SKU"
        required
    />

</div>

                <div className="form-group">

                    <label>Product Name</label>

                    <input
                        type="text"
                        name="name"
                        value={product.name}
                        onChange={handleChange}
                        placeholder="Enter product name"
                        required
                    />

                </div>


                <div className="form-group">

                    <label>Category</label>

                    <select
                        name="categoryId"
                        value={product.categoryId}
                        onChange={handleChange}
                        required
                    >

                        <option value="">
                            Select Category
                        </option>

                        <option value="1">
                            Fruits
                        </option>

                        <option value="2">
                            Vegetables
                        </option>

                        <option value="3">
                            Dairy
                        </option>

                        <option value="4">
                            Packaged Foods
                        </option>

                        <option value="5">
                            Beverages
                        </option>

                        <option value="6">
                            Household Items
                        </option>

                    </select>

                </div>


                <div className="form-group">

                    <label>Price</label>

                    <input
                        type="number"
                        name="price"
                        value={product.price}
                        onChange={handleChange}
                        placeholder="Enter price"
                        required
                    />

                </div>


                <div className="form-group">

                    <label>Description</label>

                    <textarea
                        name="description"
                        value={product.description}
                        onChange={handleChange}
                        placeholder="Enter product description"
                    />

                </div>


               


                <div className="form-actions">

                    <button
                        type="button"
                        onClick={() => navigate("/admin/products")}
                    >
                        Cancel
                    </button>

                    <button type="submit">
                        Add Product
                    </button>

                </div>

            </form>

        </div>
    );
}

export default AddProduct;