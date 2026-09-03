import "./Footer.scss";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-container">
        {/* Brand */}
        <div className="footer-brand">
          <h2> KitBlink Store</h2>

          <p>
            Fresh groceries delivered to your doorstep quickly and conveniently.
          </p>
        </div>

        
        <div className="footer-column">
          <h3>Company</h3>

          <a href="/about">About Us</a>
          <a href="#">Contact Us</a>
          <a href="#">Privacy Policy</a>
        </div>

        
        <div className="footer-column">
          <h3>Useful Links</h3>

          <a href="/">Home</a>
          <a href="/profile">My Account</a>
          <a href="/cart">Cart</a>
          <a href="#">Help & Support</a>
        </div>

     
        <div className="footer-column">
          <h3>Categories</h3>

          <a href="/products?category=2">Vegetables</a>
          <a href="/products?category=1">Fruits</a>
          <a href="/products?category=3">Dairy Products</a>
          <a href="/products?category=4">Packaged Foods</a>
          <a href="/products?category=5">Beverages</a>
          <a href="/products?category=6">Household Items</a>
        </div>

   
        <div className="footer-app">
          <h3>Download Our App</h3>

          <button>Google Play</button>

          <button>App Store</button>

          <h3>Follow Us</h3>

          <div className="footer-social">
            <a href="#">Facebook</a>
            <a href="#">Instagram</a>
            <a href="#">Twitter</a>
          </div>
        </div>
      </div>

     
      <div className="footer-bottom">
        <p>© 2026 KitBlink Store. All rights reserved.</p>
      </div>
    </footer>
  );
}

export default Footer;
