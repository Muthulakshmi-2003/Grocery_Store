import { useState , useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/api";

import "./EditProfile.scss";

function EditProfile() {

    const navigate = useNavigate();

    const [user , setUser] = useState({
        name: "", email:"",phone:""
    });
    useEffect(()=>{
        const fetchProfile = async()=>{
            try{
                const token = localStorage.getItem("token");
                const response = await api.get("auth/profile",{
                    headers:{
                        Authorization: `Bearer ${token}`
                    }
                });
                setUser(response.data);
            } catch(error){
                console.log("Failed to fetch profile:", error);
            }
        };
        fetchProfile();
    } , []);

    const handleChange=(e)=>{
        setUser({
            ...user, [e.target.name]:e.target.value
        });
    };

    const handleSubmit = async(e)=>{
        e.preventDefault();
        try{
            const token = localStorage.getItem("token");

            const response = await api.put(
                "/auth/profileupdate",
                {
                    name: user.name,
                    phone: user.phone,
                    email : user.email
                },
                {
                    headers:{
                        Authorization: `Bearer ${token}`
                    }
                }
            );
            console.log("Profile updated:", response.data);

            alert("Profile updated successfully!");

            navigate("/profile");
        }
        catch (error) {

            console.error("Profile update failed:", error);

            alert("Failed to update profile");
        }
    
    };

    // const user = JSON.parse(
    //     localStorage.getItem("user")
    // );

    // const [name, setName] = useState(user?.name || "");
    // const [email, setEmail] = useState(user?.email || "");
    // const [phone, setPhone] = useState(user?.phone || "");

    // const handleSubmit = (event) => {

    //     event.preventDefault();

    //     const updatedUser = {
    //         ...user,
    //         name,
    //         email,
    //         phone
    //     };

    //     localStorage.setItem(
    //         "user",
    //         JSON.stringify(updatedUser)
    //     );

    //     navigate("/profile");
    // };

    return (
        <div className="edit-profile">

            <div className="edit-profile-card">

                <div className="edit-profile-header">

                    <h2>Edit Profile</h2>

                    <p>
                        Update your account information
                    </p>

                </div>

                <form
                    className="edit-profile-form"
                    onSubmit={handleSubmit}
                >

                    <div className="edit-profile-field">

                        <label>
                            Name
                        </label>

                        <input
                            type="text"
                            name = "name"
                            value={user.name}
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="edit-profile-field">

                        <label>
                            Email
                        </label>

                        <input
                            type="email"
                            value={user.email}
                            name="email"
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="edit-profile-field">

                        <label>
                            Phone Number
                        </label>

                        <input
                            type="text"
                            name="phone"
                            value={user.phone}
                            onChange={handleChange}
                            required
                        />

                    </div>

                    <div className="edit-profile-actions">

                        <button
                            type="button"
                            className="edit-profile-cancel"
                            onClick={() => navigate("/profile")}
                        >
                            Cancel
                        </button>

                        <button
                            type="submit"
                            className="edit-profile-save"
                        >
                            Save Changes
                        </button>

                    </div>

                </form>

            </div>

        </div>
    );
}

export default EditProfile;