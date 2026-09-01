import "./Home.scss";
import groceryBanner from "../../assets/Banner.jpg";

import sweets from "../../assets/sweets.png";
import dairy from "../../assets/dairy.png";
import household from "../../assets/HouseholdItems.png";

import fruitsCate from "../../assets/fruits-category.jpg";
import vegetablesCate from "../../assets/vegetables-category.jpg";
import dairyCate from "../../assets/dairy-cate.jpg";
import beverages from "../../assets/beverages-cate.jpg";
import packaged from "../../assets/packaged-categories.webp";
import householdCate from "../../assets/household-category.jpg";

import Vegetables from "../../components/Vegetables/vegetables";
import Fruits from "../../components/Fruits/fruit";
import Dairy from "../../components/DairyItems/dairy";
import PackagedFood from "../../components/PackagedFood/packagedfood";
import Beverages from "../../components/Beverages/beverages";
import Household from "../../components/HouseholdProducts/household";
import Footer from "../../components/Footer/footer";


function Home(){

    const topBrands = [
        {
           image : sweets,
           title : "Sweets",
        },
        {
            image : dairy ,
            title : "Dairy Products",
        },
        {
            image : household ,
            title : "Household Items",
        },
    ];

    const categories = [
        {
            image: fruitsCate,
            name: "Fruits",
        },
        {
            image: vegetablesCate,
            name: "Vegetables",
        },
        {
            image: dairyCate,
            name: "Dairy Products",
        },
        {
            image: beverages,
            name: "Cold Drinks & Juices",
        },
        {
            image: packaged,
            name: "Snacks & Munchies",
        },
        {
            image: householdCate,
            name: "Household products",
        },

  
    ]
    return (

        
        <div className="home">
            <div className="home__banner">
                <img
                    src={groceryBanner}
                    alt="Fresh groceries"/>
            </div>
            <div className="home__content">
            <h1>Welcome to Grocery Store</h1>
            <p>Fresh groceries delivered to you doorstep.</p>
            <button>Shop Now</button>
            </div>

            {/* // topBrands */}

            <div className="home__topBrands">
                {
                    topBrands.map((card, index)=>(
                        <div className="brands-cards" key={index}>
                            <img src = {card.image} alt="{card.title}" />
                            </div>
                    ))
                }
            </div>

            {/* Cateogires */}

            <div className="home__categories">
                <h2>
                    Shop by Category
                </h2>

                <div className="category-grid">

                    {categories.map((category, index) => (

                        <div
                            className="category-card"
                            key={index}
                        >

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

            <Vegetables />
            <Fruits />
            <Dairy />
            <PackagedFood />
            <Beverages />
            <Household />
            <Footer />







        </div>
    );
}

export default Home;