import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginPage from "./components/LoginPage.jsx";
import MainPage from "./components/MainPage.jsx";

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<LoginPage />}/>
              <Route path="/main" element={<MainPage />}/>
          </Routes>
      </Router>
  );
}

export default App
