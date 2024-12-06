import React from "react";
import { Link } from "react-router-dom";

function Header() {
  return (
    <>
      <nav className="navbar navbar-expand-lg bg-body-tertiary border-bottom">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            <h3 className="text-dark p-3">Online Library</h3>
          </Link>
          <div className="collapse navbar-collapse">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <Link className="nav-link" to="/">
                  <h6>Home</h6>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/books">
                  <h6>Books</h6>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/members">
                  <h6>Members</h6>
                </Link>
              </li>
              <li className="nav-item">
                <Link className="nav-link" to="/rentals">
                  <h6>Rentals</h6>
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
}

export default Header;
