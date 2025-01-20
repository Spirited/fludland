import React, {useState} from "react";
import "../css/LoginPage.css"

const RegistrationForm = () => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [birthday, setBirthday] = useState();
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [phone, setPhone] = useState("");
    const [gender, setGender] = useState("");

    const handleRegistration = async (event) => {
        event.preventDefault();
        console.log(firstName);
        console.log(lastName);
        // console.log(birthday);
        console.log(email);
        console.log(username);
        console.log(password);
        console.log(confirmPassword);
        console.log(phone);
        console.log(gender);
    };

    const handleDateChange = (e) => {
        setBirthday(e.target.value);
    };

    return (
        <div className="container">
            <h3>Registration Form</h3>
            <form className="form registration" onSubmit={handleRegistration}>
                <div className="inputGroup">
                    <label>First Name:</label>
                    <input
                        className="input"
                        id="firstname"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        required
                    />
                </div>
                <div className="inputGroup">
                    <label>Last Name:</label>
                    <input
                        className="input"
                        id="lastname"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </div>
                <div className="inputGroup">
                    <label>Birthday:</label>
                    <input
                        type="date"
                        id="birthday"
                        className="input"
                        value={birthday}
                        onChange={(e) => handleDateChange(e)}
                    />
                </div>
                <div className="inputGroup">
                    <label type="email">Email:</label>
                    <input
                        className="input"
                        type="email"
                        id="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="inputGroup">
                    <label>Username:</label>
                    <input
                        className="input"
                        id="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div className="inputGroup">
                    <label>Password:</label>
                    <input
                        className="input"
                        type="password"
                        id="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="inputGroup">
                    <label>Confirm password:</label>
                    <input
                        className="input"
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    />
                </div>
                <div className="inputGroup">
                    <label>Mobile:</label>
                    <input
                        className="input"
                        id="mobile"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                    />
                </div>
                <div className="inputGroup">
                    <label>Gender:</label>
                    <select className="input" id="gender" value={gender} onChange={(e) => setGender(e.target.value)}>
                        <option>Male</option>
                        <option>Female</option>
                    </select>
                </div>
                <button type="submit" className="button">Register</button>
            </form>
        </div>
    );
};

export default RegistrationForm;