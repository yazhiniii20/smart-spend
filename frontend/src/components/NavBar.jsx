import "../styles/Navbar.css";
import logo from "../images/logo.png";

function Navbar({ onLogout }) {
    return (
      <nav className="navbar">
        <div className="navTitle">
        <img src = {logo} alt="logo" className="logo"/>
        <h2 className="title">SmartSpend</h2>
        </div>  
        <button onClick={onLogout}> Logout </button>
      </nav>
    );
  }
  
export default Navbar;