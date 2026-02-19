import React, { useEffect, useState } from "react";
import API from "../services/api";

function Transactions() {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    API.get("/api/transactions/history")
      .then((res) => setTransactions(res.data))
      .catch(() => alert("Failed to fetch transactions"));
  }, []);

  return (
    <div>
      <h2>Transaction History</h2>
      <ul>
        {transactions.map((txn) => (
          <li key={txn.id}>
            {txn.fromAccountId} → {txn.toAccountId} | ₹{txn.amount}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Transactions;
