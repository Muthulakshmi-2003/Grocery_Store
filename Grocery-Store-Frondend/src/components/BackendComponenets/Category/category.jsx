import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../Category/category.scss";

function Category() {
    const navigate = useNavigate();

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await axios.get("http://localhost:8085/categories");

                console.log("Category API response:", response.data);

                setCategories(response.data);
            } catch (error) {
                console.error("Error fetching categories:", error);
            }
        };

        fetchCategories();
    }, []);

    const baseUrl = "/images/categoriesJpg/";

    const handleCategory = (categoryId) => {
        navigate(`/products?category=${categoryId}`);
    };

    return (
        <div className="home-categories">

            <h2>Shop by Category</h2>

            <div className="category-grid">

                {categories.map((category) => (

                    <div
                        className="category-card"
                        key={category.id}
                        onClick={() => handleCategory(category.id)}
                    >

                        <div className="category-image">

                            <img
                                src={`${baseUrl}${category.name}.jpg`}
                                alt={category.name}
                            />

                        </div>

                        <p>{category.name}</p>

                    </div>

                ))}

            </div>

        </div>
    );
}

export default Category;