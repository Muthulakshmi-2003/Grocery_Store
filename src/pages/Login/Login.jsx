import { useState } from "react";
import { Link } from "react-router-dom";
import "./Login.scss";

function Login(){
    const [email , setEmail] = useState("");
    const [password , setPassword] = useState("");

    const handleLogin = (e) => {
        e.preventDefault();

        console.log("Email: "+email);
        console.log("Password: "+password);

    };

    return(
        <div className="login">
      <div className="login__card">

        <h1>Welcome Back </h1>
        <p>Login to your Grocery Store account</p>

        <form onSubmit={handleLogin}>

          <div className="login__field">
            <label>Email</label>
            <input
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="login__field">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <button type="submit">
            Login
          </button>

        </form>

        <p className="login__register">
          Don't have an account?
          <Link to="/register" >Register</Link>
        </p>

      </div>
    </div>

    );
}
export default Login;