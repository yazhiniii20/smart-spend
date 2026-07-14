import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import logo from "../images/logo.png";
import "../styles/Register.css"

function RegisterPage() {
    const navigate = useNavigate();
    const [name, setName] =  useState("");
    const [email, setEmail] =  useState("");
    const [password, setPassword] =  useState("");
    const [role, setRole] =  useState("USER");

    const handleRegister =  async () => {
        try {
            await api.post("/auth/register", {
                name,
                email,
                password,
                role
            });
            setRole(role);
            alert("Registration Successful");
            navigate("/");
        
        } catch (error) {        
            if (error.response) {
                alert(
                    Object.values(error.response.data).join("\n")
                );
            } else {
                alert("Server error");
            }
        }
    };

    return (
        <div className="register-container">
            <div className="register-card">
            <img src = {logo} alt="logo" className="logo"/>
            <h1>Register</h1>
            <p className="subtitle"> Smart spending starts with a single step. Register to begin !</p>
            <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)}/>
            <br/><br/>
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
            <br/><br/>
            <input  type="password" placeholder="Password" value={password}  onChange={(e) => setPassword(e.target.value)}/>
            <br/><br/>
            <button onClick={handleRegister}> Register </button>
            <p className="auth-p"> Already have an account?</p>
            <p className="auth-link" onClick={() => navigate("/")}> Login </p>
            </div>
        </div>
    );
}

export default RegisterPage;