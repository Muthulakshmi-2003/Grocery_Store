import "./Home.scss";
import groceryBanner from "../../assets/Banner.jpg";

import sweets from "../../assets/sweets.png";
import dairy from "../../assets/dairy.png";
import household from "../../assets/HouseholdItems.png";

import Category from "../../components/BackendComponenets/Category/category";

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

   
    return (

        
        <div className="home">
            <div className="home-banner">
                <img
                    src={groceryBanner}
                    alt="Fresh groceries"/>
            </div>
            <div className="home-content">
            <h1>Welcome to Grocery Store</h1>
            <p>Fresh groceries delivered to you doorstep.</p>
            <button>Shop Now</button>
            </div>

            {/* // topBrands */}

            <div className="home-topBrands">
                {
                    topBrands.map((card, index)=>(
                        <div className="brands-cards" key={index}>
                            <img src = {card.image} alt="{card.title}" />
                            </div>
                    ))
                }
            </div>

            {/* Cateogires */}

            <Category />

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