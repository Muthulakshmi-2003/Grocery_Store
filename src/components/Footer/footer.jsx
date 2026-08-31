import "./Footer.scss";

function Footer() {
    return (
        <footer className="footer">

            <div className="footer__container">

                {/* Brand */}
                <div className="footer__brand">
                    <h2>  KitBlink Store

                    </h2>

                    <p>
                        Fresh groceries delivered to your
                        doorstep quickly and conveniently.
                    </p>
                </div>


                {/* Company */}
                <div className="footer__column">
                    <h3>Company</h3>

                    <a href="#">About Us</a>
                    <a href="#">Contact Us</a>
                    <a href="#">Careers</a>
                    <a href="#">Privacy Policy</a>
                    <a href="#">Terms & Conditions</a>
                </div>


                {/*  Links */}
                <div className="footer__column">
                    <h3>Useful Links</h3>

                    <a href="#">Home</a>
                    <a href="#">My Account</a>
                    <a href="#">My Orders</a>
                    <a href="#">Cart</a>
                    <a href="#">Help & Support</a>
                </div>


                {/* Categories */}
                <div className="footer__column">
                    <h3>Categories</h3>

                    <a href="#">Vegetables</a>
                    <a href="#">Fruits</a>
                    <a href="#">Dairy Products</a>
                    <a href="#">Beverages</a>
                    <a href="#">Household Items</a>
                </div>


                {/* App */}
                <div className="footer__app">

                    <h3>Download Our App</h3>

                    <button>Google Play</button>

                    <button>App Store</button>

                    <h3>Follow Us</h3>

                    <div className="footer__social">
                        <a href="#">Facebook</a>
                        <a href="#">Instagram</a>
                        <a href="#">Twitter</a>
                    </div>

                </div>

            </div>


            {/* copy rights */}
            <div className="footer__bottom">

                <p>
                    © 2026 KitBlink Store. All rights reserved.
                </p>

            </div>

        </footer>
    );
}

export default Footer;