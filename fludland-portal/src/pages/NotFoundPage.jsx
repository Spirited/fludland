import React from "react";
import { Link } from "react-router-dom";
import "./ErrorPage.css";

const NotFoundPage = () => {
    return (
        <div className="error-container">
            <h1>404</h1>
            <p>Oops! The page you're looking for doesn't exist.</p>
            <Link to="/" className="back-home">Go to Home</Link>
        </div>
    );
};

export default NotFoundPage;
