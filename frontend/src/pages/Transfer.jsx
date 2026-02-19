import React, { useState } from "react";
import API from "../services/api";

function Transfer() {
  const [fromAccountId, setFrom] = useState("");
  const [toAccountId, setTo] = useState("");
  const [amount, setAmount] = useState("");

  const transfer = async () => {
    try {
      await API.post("/api/transactions/transfer", {
        fromAccountId,
        toAccountId,
        amount,
      });
      alert("Transfer successful");
    } catch (err) {
      alert("Transfer failed");
    }
  };

  return (
    <div>
      <h2>Transfer</h2>
      <input placeholder="From Account" onChange={(e) => setFrom(e.target.value)} />
      <input placeholder="To Account" onChange={(e) => setTo(e.target.value)} />
      <input placeholder="Amount" onChange={(e) => setAmount(e.target.value)} />
      <button onClick={transfer}>Send</button>
    </div>
  );
}

export default Transfer;