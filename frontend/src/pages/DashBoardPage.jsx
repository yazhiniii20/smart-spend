import {useState,useEffect} from 'react';
import api from '../api/axios';

function DashboardPage() {
    const[user,setUser] = useState(null);
    const[dashboard,setDashboard] = useState(null);
    const[expenses,setExpenses] = useState([]);
   
    useEffect(() => {      
    const fetchUser = async () => {
      try{
         const response = await api.get("/auth/me");
         setUser(response.data);
      }catch(error){
       console.log(error);
      }
   };
       
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
       fetchUser();
       fetchDashboard();
       fetchExpenses();
    },[]);

    return(
      <div>
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
            </div>
          )}
          <div>
            <h2> My Expenses </h2>
            {expenses.map((expense) => (
             <div key={expense.id}>
             <h4>{expense.title}</h4>
             <p>₹{expense.amount}</p>
             <p>{expense.category}</p>
               <hr/>
             </div>
        ))}
          </div>
      </div>
    );
  }
  
export default DashboardPage;