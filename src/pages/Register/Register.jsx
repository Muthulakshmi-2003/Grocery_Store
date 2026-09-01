import { Link } from "react-router-dom";
import "./Register.scss";
import shoppingBag from "../../assets/shopping-bag.png";

function Register(){
    return (
        <div className="register-page">
            <div className="register-card">
                <div className="register-left">
          <div className="brand">
               <img src={shoppingBag} alt="Grocery Store Logo"/> KitBlink
          </div>

          <h1>Join Our  FreshMart</h1>

          <p>
            Create your account and start shopping
             at the best prices.
          </p>

          <div className="benefits">
            <p>✓ Fresh & Quality Products</p>
            <p>✓ Fast Home Delivery</p>
            <p>✓ Best Prices</p>
          </div>
        </div>

  
        <div className="register-right">
          <h2>Create Account</h2>

          <p className="subtitle">
            Sign up to get started
          </p>

          <form>
            <div className="input-group">
              <label>Full Name</label>
              <input
                type="text"
                placeholder="Enter your full name"
              />
            </div>

            <div className="input-group">
              <label>Email</label>
              <input
                type="email"
                placeholder="Enter your email"
              />
            </div>

            <div className="input-group">
              <label>Password</label>
              <input
                type="password"
                placeholder="Enter your password"
              />
            </div>

            <div className="input-group">
              <label>Confirm Password</label>
              <input
                type="password"
                placeholder="Confirm your password"
              />
            </div>

            <button type="submit">
              Create Account
            </button>
          </form>

          <p className="login-text">
            Already have an account?{" "}
            <Link to="/login">Login</Link>
          </p>
        </div>
            </div>
        </div>
    );
}

export default Register;