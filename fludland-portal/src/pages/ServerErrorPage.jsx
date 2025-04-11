import React from "react";
import { Link } from "react-router-dom";
import "./ErrorPage.css";

const ServerErrorPage = () => {
    return (
        <div className="error-container">
            <h1>500</h1>
            <p>Something went wrong on our side. Please try again later.</p>
            <Link to="/" className="back-home">Return Home</Link>
        </div>
    );
};

export default ServerErrorPage;
