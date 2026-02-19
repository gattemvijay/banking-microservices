import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function Login() {
  console.log("Login component rendered");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
     console.log("Login button clicked");
    try {
      const res = await API.post("/api/users/login", {
        email,
        password,
      });

      localStorage.setItem("token", res.data.token);
      alert("Login Successful");
      navigate("/dashboard");
    } catch (error) {
        console.error("Login API error:", error);
        alert("Invalid credentials");
    }
  };

  return (
    <form style={{ padding: "50px" }} autoComplete="off">
      <h2>Banking Login</h2>

      <input id="email" 
        name="email"
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <br /><br />

      <input id="password" 
        name="password"
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br /><br />

      <button  type="button" onClick={handleLogin}>Login</button>
    </form>
  );
}

export default Login;