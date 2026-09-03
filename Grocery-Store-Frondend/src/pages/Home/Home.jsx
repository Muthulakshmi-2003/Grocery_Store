import "./Home.scss";
import groceryBanner from "../../assets/Banner.jpg";

import sweets from "../../assets/sweets.png";
import dairy from "../../assets/dairy.png";
import household from "../../assets/HouseholdItems.png";
import { Link } from "react-router-dom";

import Category from "../../components/BackendComponenets/Category/category";

import Vegetables from "../../components/Vegetables/vegetables";
import Fruits from "../../components/Fruits/fruit";
import Dairy from "../../components/DairyItems/dairy";
import PackagedFood from "../../components/PackagedFood/packagedfood";
import Beverages from "../../components/Beverages/beverages";
import Household from "../../components/HouseholdProducts/household";
import Footer from "../../components/Footer/footer";
import AboutUs from "../../components/AboutUs/AboutUs";

function Home() {
  const topBrands = [
    {
      image: sweets,
      title: "Packaged Foods",
    },
    {
      image: dairy,
      title: "Dairy",
    },
    {
      image: household,
      title: "Household Items",
    },
  ];

  return (
    <div className="home">
      <div className="home-banner">
        <img src={groceryBanner} alt="Fresh groceries" />
      </div>
      <div className="home-content">
        <h1>Welcome to Grocery Store</h1>
        <p>Fresh groceries delivered to you doorstep.</p>
        <Link to={"/products"}>
          <button>Shop Now</button>
        </Link>
      </div>

      <div className="home-topBrands">
        {topBrands.map((card, index) => (
          <Link
            to={`/products?category=${card.title}`}
            className="brands-cards"
            key={index}
          >
            <img src={card.image} alt={card.title} />
          </Link>
        ))}
      </div>

      <Category />

      <Vegetables />
      <Fruits />
      <Dairy />
      <PackagedFood />
      <Beverages />
      <Household />
      <AboutUs />
      <Footer />
    </div>
  );
}

export default Home;
