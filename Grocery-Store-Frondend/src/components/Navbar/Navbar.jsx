import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

import "./Navbar.scss";
import shoppingBag from "../../assets/shopping-bag.png";
import searchIcon from "../../assets/Search-icon.png";

function Navbar() {
  const navigate = useNavigate();

  const openSearch = () => {
    navigate("/search");
  };

  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem("token"));

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <img src={shoppingBag} alt="Grocery Store Logo" /> KitBlink
      </div>

      <div className="navbar-links">
        <button className="navbar-searchIcon" onClick={openSearch}>
          <img src={searchIcon} alt="searchicon" />
        </button>

        <a href="/">Home</a>

        <Link to="/products">Products</Link>

        <Link to="/cart">Cart</Link>
        <div className="navbar-links">
          {isLoggedIn ? (
            <button
              className="navbar-user-icon"
              onClick={() => navigate("/profile")}
            ></button>
          ) : (
            <Link to="/Login">Login</Link>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
