import {useState} from 'react';
import {useNavigate} from "react-router-dom";
import api from "../api/axios";

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
     <div>
        <h1> Login Page </h1>
        <input type="email" value = {email} placeholder = "Enter email" onChange={(e) => setEmail(e.target.value)}/>
        <br/>
        <br/>
        <input type="password" value = {password} placeholder = "Enter password" onChange={(e) => setPassword(e.target.value)}/>
        <br/>
        <br/>
        <button onClick = {handleLogin}>Login</button>
     </div>
   );    
  }
  
  export default LoginPage;