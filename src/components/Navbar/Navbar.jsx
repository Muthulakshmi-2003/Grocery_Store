import { Link , useNavigate } from "react-router-dom";
import { useState } from "react";

import "./Navbar.scss";
import shoppingBag from "../../assets/shopping-bag.png";


function Navbar(){

    const [search, setSearch] = useState(false);

    const navigate = useNavigate();

    const handleSearch = (e) => {

        e.preventDefault();

        if (search.trim()) {

            navigate(
                `/products?search=${encodeURIComponent(search)}`
            );
        }
    };
    return (
        <nav className="navbar">
            <div className="navbar__logo">
                <img src={shoppingBag} alt="Grocery Store Logo"/> KitBlink

            </div>

            <div className="navbar__links">
                 <form
                className="navbar__search"
                onSubmit={handleSearch}
            >

                <input
                    type="text"
                    placeholder="Search products..."
                    value={search}
                    onChange={(e) =>
                        setSearch(e.target.value)
                    }
                />

                <button type="submit">
                    🔍
                </button>

            </form>
                <a href="/">Home</a>
                <a href="/">Products</a>
                <a href="/orders">Orders</a>
                <a href="/cart">Cart</a>
                <Link to="/Login" >Login</Link>
            </div>
        </nav>
    );
}

export default Navbar;