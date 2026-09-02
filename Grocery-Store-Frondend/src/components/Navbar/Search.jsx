import { useState } from "react";
import { useNavigate } from "react-router-dom";
import searchIcon from "../../assets/Search-icon.png";
import "./Search.scss";

function Search() {
    const [search, setSearch] = useState("");
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
        <div className="search-page">
            <form  onSubmit={handleSearch}>
                <div className="search-box">
                <button type="submit" className="search-icon">
                    <img src={searchIcon} alt="Search Icon" />
                </button>
                <input
                    type="text"
                    placeholder="Search products..."
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                    autoFocus
                />
                </div>
                
            </form>
        </div>
    );
}

export default Search;