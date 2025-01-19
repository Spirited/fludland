import {Routes, Route} from "react-router-dom";
import LoginPage from "./LoginPage.jsx";
import RegistrationForm from "./RegistrationForm.jsx";
import {WelcomePage} from "../pages/Pages.jsx";

const RouteConfigs = () => (
    <Routes>
        <Route path="/" element={ <WelcomePage /> }/>
        <Route path="/login" element={<LoginPage />}/>
        <Route path="/signup" element={<RegistrationForm />} />
    </Routes>
);

export default RouteConfigs;