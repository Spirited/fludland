import {Link} from "react-router-dom";

const Header = () => (
    <header className="header">
        <div className="logo">
            <a href="/"><img src="../../public/images/logo.png" height="100" width="100"/></a>
        </div>
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
