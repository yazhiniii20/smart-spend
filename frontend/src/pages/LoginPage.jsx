import {useState} from 'react';
import {useNavigate} from "react-router-dom";
import api from "../api/axios";
import "../styles/Login.css";

function LoginPage() {
   const [email,setEmail] = useState("");
   const [password,setPassword] = useState("");
   const navigate = useNavigate();
   
   const handleLogin = async() => {
     try{
       const response = await api.post("/auth/login",{
        email,
        password
       });
       localStorage.setItem("token",response.data.token);      
       navigate("/dashboard");
     }catch(error){
        console.log(error);
     }
   };

   return(
     <div className="login-container">
        <div className="login-card">
        <h1>SmartSpend</h1>
        <p className="subtitle"> Track your expenses and manage your finances</p>
        <input type="email" value = {email} placeholder = "Enter email" onChange={(e) => setEmail(e.target.value)}/>
        <br/>
        <br/>
        <input type="password" value = {password} placeholder = "Enter password" onChange={(e) => setPassword(e.target.value)}/>
        <br/>
        <br/>
        <button onClick = {handleLogin}>Login</button>
        <p className="auth-link" onClick={() => navigate("/register")}> Create Account </p>
        </div>
     </div>
   );    
  }
  
  export default LoginPage;