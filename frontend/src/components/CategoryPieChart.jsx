import {PieChart, Pie, Cell, Tooltip, Legend} from "recharts";
  
  function CategoryPieChart({ data }) {
  
    const COLORS = ["#0088FE","#00C49F","#FFBB28","#FF8042","#8884D8"];
  
    return (
      <PieChart width={400} height={300}>
        <Pie data={data} dataKey="totalSpent" nameKey="category" outerRadius={100} label>
          {data.map((entry, index) => (
            <Cell key={index}  fill={
                COLORS[index % COLORS.length]
              }
            />
          ))}
        </Pie>  
        <Tooltip />
        <Legend />
      </PieChart>
    );
  }
  
  export default CategoryPieChart;