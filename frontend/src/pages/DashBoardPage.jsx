import {useState,useEffect} from 'react';
import api from '../api/axios';
import CategoryPieChart from "../components/CategoryPieChart";
import { useNavigate } from "react-router-dom";
import "../styles/Dashboard.css";
import Navbar from "../components/NavBar";

function DashboardPage() {
    const[user,setUser] = useState(null);
    const[dashboard,setDashboard] = useState(null);
    const[expenses,setExpenses] = useState([]);
    const [title, setTitle] = useState("");
    const [amount, setAmount] = useState("");
    const [category, setCategory] = useState("");
    const [date, setDate] = useState("");
    const [notes, setNotes] = useState("");
    const [editingExpenseId, setEditingExpenseId] = useState(null);
    const [categoryData, setCategoryData] = useState([]);
    const navigate = useNavigate();

    const fetchDashboard = async() => {
      try{
       const response = await api.get("/dashboard");
       setDashboard(response.data);
      }catch(error){
       console.log(error);
      }
    };

    const fetchExpenses = async () => {
       try {
        const response =  await api.get("/expenses/my");
        setExpenses(response.data);
       } catch(error) {
        console.log(error);
      }
    };
    useEffect(() => {      
    const fetchUser = async () => {
      try{
         const response = await api.get("/auth/me");
         setUser(response.data);
      }catch(error){
       console.log(error);
      }
   };
       fetchUser();
       fetchDashboard();
       fetchCategoryBreakdown();
       fetchExpenses();
    },[]);
    
    const handleAddExpense = async () => {
      try {  
          await api.post("/expenses", {
              title,
              amount,
              category,
              date,
              notes
          });
  
          fetchExpenses();
          fetchDashboard();
          fetchCategoryBreakdown();
          setTitle("");
          setAmount("");
          setCategory("");
          setDate("");
          setNotes("");  
      } catch(error) {  
          console.log(error);  
      }
    };

    const handleDeleteExpense = async (expenseId) => {
      try {  
          await api.delete(`/expenses/${expenseId}`);  
          fetchExpenses();
          fetchDashboard(); 
          fetchCategoryBreakdown(); 
      } catch(error) {  
          console.log(error);  
        }
     };

     const fetchCategoryBreakdown = async () => {
        try {
            const response = await api.get("/expenses/category-breakdown");
            setCategoryData(response.data);
        } catch(error) {
            console.log(error);
        }
     };

     const handleEditExpense = (expense) => {
      setEditingExpenseId(expense.id);  
      setTitle(expense.title);
      setAmount(expense.amount);
      setCategory(expense.category);
      setDate(expense.date);
      setNotes(expense.notes);
    };

    const handleUpdateExpense = async () => {
      try {  
          await api.put(`/expenses/${editingExpenseId}`,
              {
                  title,
                  amount,
                  category,
                  date,
                  notes
              }
          );

          fetchExpenses();
          fetchDashboard();
          fetchCategoryBreakdown();  
          setEditingExpenseId(null);  
          setTitle("");
          setAmount("");
          setCategory("");
          setDate("");
          setNotes("");  
      } catch(error) {  
          console.log(error);  
      }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };
  if (!user || !dashboard) {
    return <h2>Loading...</h2>;
}

    return(
      <div className = "dashboard-container">        
          <div>
          <Navbar onLogout={handleLogout}/>
          </div>
          <div>
          <h1> DashBoard </h1>
          <p className="dashboard-subtitle"> Track your spending and financial activity </p>
          <div className="welcome-section">
          {user && (
            <h2>  Welcome {user.name}👋 </h2>
          )}
          </div>
          </div>
          {dashboard && (
              <div className="stats-container">
              <div className="card">
              <h3>
                Total Spent : ₹{dashboard.totalSpent}
              </h3>
              </div>
              <div className="card">
              <h3>
                Expense Count : {dashboard.expenseCount}
              </h3>
              </div>
              <div className="card">
              <h3>
              Top Category: {dashboard.topCategory}
              </h3>
              </div>
              <hr/>
            </div>
          )}
          <div className="content-section">
          <div className="chart-card">
          <h2>Expense Breakdown</h2>
            <CategoryPieChart data={categoryData}/>
          </div>
          <div className="form-card">
          <h2>{editingExpenseId ? "Update Expense" : "Add Expense"}</h2>
            <input type="text" placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)}/>
          <br/><br/>
             <input type="number" placeholder="Amount" value={amount} onChange={(e) => setAmount(e.target.value)}/>
          <br/><br/>
             <input type="text" placeholder="Category" value={category} onChange={(e) => setCategory(e.target.value)}/>
          <br/><br/>
              <input type="date" value={date} onChange={(e) => setDate(e.target.value)}/>
          <br/><br/>
              <input type="text"  placeholder="Notes" value={notes} onChange={(e) => setNotes(e.target.value)}/>
           <br/><br/>
              <button onClick={editingExpenseId ? handleUpdateExpense : handleAddExpense}>
              {editingExpenseId ? "Update Expense" : "Add Expense"} </button>
              {editingExpenseId && (<button onClick={() => {
                  setEditingExpenseId(null);
                  setTitle("");
                  setAmount("");
                  setCategory("");
                  setDate("");
                  setNotes(""); }}> Cancel </button> )}
        <hr/>
        </div>
        </div>
        {expenses.length === 0 ? (
         <div className="empty-state"> No expenses found.
                                       Add your first expense 🚀
          </div> ) : ( 
          <div className="expense-table-container">
            <h2> My Expenses </h2>
            <table className="expense-table">
                <thead>
                 <tr>
                  <th>Title</th>
                  <th>Amount</th>
                  <th>Category</th>
                  <th>Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
           <tbody>
               {expenses.map((expense) => (
                  <tr key={expense.id}>
                  <td>{expense.title}</td>
                  <td>₹{expense.amount}</td>
                  <td>{expense.category}</td>
                  <td>{expense.date}</td>
                  <td> <button className="edit-btn" onClick={() => handleEditExpense(expense)}> Edit </button>
                  <button className="delete-btn" onClick={() => handleDeleteExpense(expense.id)}> Delete </button>
                 </td>
           </tr>
         ))}
          </tbody>
             </table>
                 </div>
            )}
                    </div>
              );
            } 
export default DashboardPage;