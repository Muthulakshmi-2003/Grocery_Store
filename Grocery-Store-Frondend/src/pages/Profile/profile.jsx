import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import "./Profile.scss";
import api from "../../api/api";

function Profile() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");

    navigate("/login");
  };

  const [user, setUser] = useState(null);

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem("token");
        console.log("Profile Token:", token);

        const response = await api.get("/auth/profile", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log("Profile data:", response.data);

        setUser(response.data);
      } catch (error) {
        console.error("Failed to fetch profile:", error);
      }
    };

    fetchProfile();
  }, []);

  return (
    <div className="profile">
      <div className="profile-card">
        <div className="profile-header">
          <div className="profile-avatar">👤</div>

          <h2>My Profile</h2>
          <p>Manage your account details</p>
        </div>

        <div className="profile-details">
          <div className="profile-field">
            <span>Name</span>
            <p>{user?.name}</p>
          </div>

          <div className="profile-field">
            <span>Email</span>
            <p>{user?.email}</p>
          </div>

          <div className="profile-field">
            <span>Phone Number</span>
            <p>{user?.phone}</p>
          </div>
        </div>

        <div className="profile-actions">
          <button
            className="profile-edit-btn"
            onClick={() => navigate("/profile/edit")}
          >
            Edit Profile
          </button>

          <button className="profile-logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>
    </div>
  );
}

export default Profile;
