import React from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  return (
    <div>
      <h2>Dashboard</h2>
      <Link to="/transfer">Transfer Money</Link>
      <br />
      <Link to="/transactions">View Transactions</Link>
    </div>
  );
}

export default Dashboard;