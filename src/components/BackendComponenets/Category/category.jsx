import { useNavigate } from "react-router-dom";
import "./Category.scss";
import "../../../pages/Home/Home.scss";

function Category() {

    const navigate = useNavigate();

    const categories = [
        {
            id: 1,
            name: "Fruits & Vegetables",
            image: "/images/fruits.jpg"
        },
        {
            id: 2,
            name: "Sweets",
            image: "/images/sweets.jpg"
        },
        {
            id: 3,
            name: "Dairy",
            image: "/images/dairy.jpg"
        },
        {
            id: 4,
            name: "Household",
            image: "/images/household.jpg"
        }
    ];

    const handleCategory = (categoryId) => {
        navigate(`/products?category=${categoryId}`);
    };
    return(
            <div className="home__categories">
                <h2>
                    Shop by Category
                </h2>

                <div className="category-grid">

                    {categories.map((category) => (

                        <div
                            className="category-card"
                            key={category.id}
                            onClick={() => handleCategory(category.id)} >

                            <div className="category-image">

                                <img
                                    src={category.image}
                                    alt={category.name}
                                />

                            </div>

                            <p>
                                {category.name}
                            </p>

                        </div>

                    ))}

                </div>

            </div>
    )
}

export default Category;