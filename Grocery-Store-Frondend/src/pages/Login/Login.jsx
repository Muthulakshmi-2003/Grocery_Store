import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import "./Login.scss";
import api from "../../api/api";

function Login(){
    
  const [formData, setFormData] = useState({
    email: "",
    password: ""
});

const navigate = useNavigate();

const handleSubmit = async (e) => {
    e.preventDefault();

     if (
        formData.email === "admin@gmail.com" &&
        formData.password === "admin123"
    ) {
      alert("Welcome Admin");
        navigate("/admin/dashboard");
        return;
    }

    try {
        const response = await api.post(
            "/auth/login",
            formData
        );

        console.log("Login successful:", response.data);

        localStorage.setItem(
            "token",
            response.data.token
        );

        localStorage.setItem(
            "user",
            JSON.stringify(response.data)
        );

        navigate("/");

    } catch (error) {
        console.error("Login failed:", error);
    }
};

    

    return(
        <div className="login">
      <div className="login-card">

        <h1>Welcome Back </h1>
        <p>Login to your Grocery Store account</p>

        <form onSubmit={handleSubmit}>

          <div className="login-field">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={formData.email}
              onChange={(e) => setFormData({
                ...formData, email: e.target.value
              })}
              required
            />
          </div>

          <div className="login-field">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={formData.password}
              onChange={(e) => setFormData({
            ...formData,
            password: e.target.value
        })}
              required
            />
          </div>

          <button type="submit">
            Login
          </button>

        </form>

        <p className="login-register">
          Don't have an account?
          <Link to="/register" >Register</Link>
        </p>

      </div>
    </div>

    );
}
export default Login;