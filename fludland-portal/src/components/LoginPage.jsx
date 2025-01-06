import React, { useState } from 'react';
import "../css/LoginPage.css"

const LoginPage = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = (event) => {
        event.preventDefault();
        console.log("Email: ", email);
        console.log("Password: ", password);
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
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
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

