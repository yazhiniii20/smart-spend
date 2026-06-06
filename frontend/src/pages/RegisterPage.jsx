import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";

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
                }
            );

            alert("Registration Successful");
            navigate("/");

        } catch(error) {
            console.log(error);
        }
    };

    return (
        <div>
            <h1>Register</h1>
            <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)}/>
            <br/><br/>
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
            <br/><br/>
            <input  type="password" placeholder="Password" value={password}  onChange={(e) => setPassword(e.target.value)}/>
            <br/><br/>
            <button onClick={handleRegister}> Register </button>
        </div>
    );
}

export default RegisterPage;