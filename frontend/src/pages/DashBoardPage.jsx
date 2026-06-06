import {useState,useEffect} from 'react';
import api from '../api/axios';
import CategoryPieChart from "../components/CategoryPieChart";
import { useNavigate } from "react-router-dom";

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

    return(
      <div>
          <div>
          <button onClick={handleLogout}> Logout </button>
          </div>
          <div>
          <h1> DashBoard </h1>
          {user && (
            <h2>
              Welcome {user.name}
            </h2>
          )}
          </div>
          {dashboard && (
              <div>
              <h3>
                Total Spent : ₹{dashboard.totalSpent}
              </h3>
              <h3>
                Expense Count : {dashboard.expenseCount}
              </h3>
              <h3>
              Top Category: {dashboard.topCategory}
              </h3>
              <hr/>
            </div>
          )}
          <div>
          <h2>Expense Breakdown</h2>
            <CategoryPieChart data={categoryData}/>
          </div>
          <div>
          <h2>Add Expense</h2>
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
          <div>
            <h2> My Expenses </h2>
            {expenses.map((expense) => (
             <div key={expense.id}>
             <h4>{expense.title}</h4>
             <p>₹{expense.amount}</p>
             <p>{expense.category}</p>
             <button onClick={() => handleEditExpense(expense)}>Edit </button>
             <button onClick={() => handleDeleteExpense(expense.id)}> Delete </button>
               <hr/>
             </div>
        ))}
          </div>
      </div>
    );
  }
  
export default DashboardPage;