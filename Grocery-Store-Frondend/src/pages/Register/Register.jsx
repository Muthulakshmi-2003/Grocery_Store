import { Link } from "react-router-dom";
import { useState } from "react";
import "./Register.scss";
import shoppingBag from "../../assets/shopping-bag.png";
import api from "../../api/api";

function Register(){

  const [formData , setFormData] = useState({
    name:"",
    email:"",
    number:"",
    password:""
  });

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        const response = await api.post(
            "/auth/register",
            formData
        );
        alert("Registered Successfully!!");

        console.log("Registration successful:", response.data);

    } catch (error) {

        console.error("Registration failed:", error);

    }
};

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

          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <label>Full Name</label>
              <input
                type="text"
                placeholder="Enter your full name"
                value={formData.name}
                onChange={(e)=>setFormData({
                  ...formData, name:e.target.value
                })}
              />
            </div>

            <div className="input-group">
              <label>Email</label>
              <input
                type="email"
                placeholder="Enter your email"
                value={formData.email}
    onChange={(e) =>
        setFormData({
            ...formData,
            email: e.target.value
        })}
              />
            </div>
             <div className="input-group">
              <label>Phone Number</label>
              <input
                type="text"
                placeholder="Enter your phone number"
                onChange={(e) =>
        setFormData({
            ...formData,
            number: e.target.value
        })
    }
              />
            </div>



            <div className="input-group">
              <label>Password</label>
              <input
                type="password"
                placeholder="Enter your password"
                 value={formData.password}
    onChange={(e) =>
        setFormData({
            ...formData,
            password: e.target.value
        })
    }
              />
            </div>

            <div className="input-group">
              <label>Confirm Password</label>
              <input
                type="password"
                placeholder="Confirm your password"
                onChange={(e) =>
        setFormData({
            ...formData,
            password: e.target.value
        })
    }
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