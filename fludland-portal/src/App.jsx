import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./templates/Header.jsx";
import Footer from "./templates/Footer.jsx";

const App = () => (
    <Router>
        <Header/>
        <Footer/>
    </Router>
)

export default App;