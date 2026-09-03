import { useNavigate } from "react-router-dom";

import "./Dashboard.scss";

function Dashboard() {
  const navigate = useNavigate();

  return (
    <div className="admin-dashboard">
      <aside className="admin-dashboard-sidebar">
        <h2>Grocery Admin</h2>

        <button onClick={() => navigate("/admin/dashboard")}>Dashboard</button>

        <button onClick={() => navigate("/admin/products")}>Products</button>

        <button>Logout</button>
      </aside>

      <main className="admin-dashboard-content">
        <div className="admin-dashboard-top">
          <h1>Dashboard</h1>

          <button
            className="admin-profile-btn"
            onClick={() => navigate("/admin/dashboard")}
          >
            👤 Admin
          </button>
        </div>

        <div className="dashboard-cards">
          <div className="dashboard-card">
            <h3>Total Products</h3>
            <p>50+</p>
          </div>

          <div className="dashboard-card">
            <h3>Total Orders</h3>
            <p>500+</p>
          </div>

          <div className="dashboard-card">
            <h3>Total Customers</h3>
            <p>1000+</p>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Dashboard;
