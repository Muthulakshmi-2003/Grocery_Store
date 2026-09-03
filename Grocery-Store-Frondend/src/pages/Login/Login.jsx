import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import "./Login.scss";
import api from "../../api/api";

function Login() {
  const [showAdminPopup, setShowAdminPopup] = useState(false);
  const [showUserPopup, setUserPopup] = useState(false);
  const [showLoginFailed, setShowLoginFailed] = useState(false);

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (
      formData.email === "admin@gmail.com" &&
      formData.password === "admin123"
    ) {
      setShowAdminPopup(true);

      setTimeout(() => {
        navigate("/admin/dashboard");
      }, 1500);
      return;
    }

    try {
      const response = await api.post("/auth/login", formData);

      localStorage.setItem("token", response.data.token);

      localStorage.setItem("user", JSON.stringify(response.data));
      setUserPopup(true);
      setTimeout(() => {
        console.log("Login successful:", response.data);
        navigate("/");
      }, 2000);
    } catch (error) {
      console.error("Login failed:", error);
      setShowLoginFailed(true);
    }
  };

  return (
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
              onChange={(e) =>
                setFormData({
                  ...formData,
                  email: e.target.value,
                })
              }
              required
            />
          </div>

          <div className="login-field">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={formData.password}
              onChange={(e) =>
                setFormData({
                  ...formData,
                  password: e.target.value,
                })
              }
              required
            />
          </div>

          <button type="submit">Login</button>
        </form>

        <p className="login-register">
          Don't have an account?
          <Link to="/register">Register</Link>
        </p>
      </div>
      {showAdminPopup && (
        <div className="admin-popup-overlay">
          <div className="admin-popup">
            <h3>Welcome Admin!</h3>

            <p>Login successful</p>
          </div>
        </div>
      )}
      {showUserPopup && (
        <div className="admin-popup-overlay">
          <div className="admin-popup">
            <h3>Welcome User!!, Have a good day !!</h3>

            <p>Login successful</p>
          </div>
        </div>
      )}

      {showLoginFailed && (
        <div className="login-popup-overlay">
          <div className="login-popup">
            <h3>Login Failed</h3>

            <p>Incorrect email or password !!. Try Again</p>

            <button onClick={() => setShowLoginFailed(false)}>OK</button>
          </div>
        </div>
      )}
    </div>
  );
}
export default Login;
