import "../css/header_and_footer.css"
import {Link} from "react-router-dom";

const Header = () => (
    <header className="header">
        <div className="logo">MyLogo</div>
        <h1>My App Header</h1>
        <div className="actions">
            <nav>
                <Link className="btn login" to={"/login"}>Login</Link>
                <Link className="btn signup" to={"/signup"}>Sign Up</Link>
            </nav>
        </div>
    </header>
);

export default Header;
