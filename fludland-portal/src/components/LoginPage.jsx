import React, { useState } from 'react';
import { useNavigate } from "react-router-dom"
import "../css/LoginPage.css"

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();
        console.log("Login: ", username);
        console.log("Password: ", password);

        console.log("Body", JSON.stringify({username, password}));

        try {
            const response = await fetch("http://localhost:8084/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({username, password})
            });

            console.log(response);

            if (response.status === 200) {
                const data = await response.json();
                console.log("Token: ", data);
                navigate("/main");
            } else {
                const errorData = await response.json();
                setError(errorData || "Login failed. Please try again.");
                console.log(error)
            }
        } catch (err) {
            setError("Something went wrong.Please try again later. " + err)
            console.log(error);
        }

        console.log("asdasdasd")
    }

    return (
        <div className="container">
            <h2>Login</h2>
            <form onSubmit={handleLogin} className="form">
                <div className="inputGroup">
                    <label htmlFor="email">Email:</label>
                    <input
                        type="email"
                        id="email"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                        className="input"
                    />
                </div>
                <div className="inputGroup">
                    <label htmlFor="password">Password:</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        className="input"
                    />
                </div>
                <button type="submit" className="button">
                    Log In
                </button>
            </form>
        </div>
    );
};

export default LoginPage

