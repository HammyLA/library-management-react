import React from "react";
import "../styles/Home.css";

function Home() {
  return (
    <div className="vh-100 border d-flex flex-column justify-content-center align-items-center text-center custom-background text-white">
      {/* Main row containing the title */}
      <div className="row">
        <h1>
          <strong>Library Manager</strong>
        </h1>
      </div>

      {/* Sub row containing the description */}
      <div className="row">
        <h3>The website designed to make managing book rentals easier</h3>
      </div>
    </div>
  );
}

export default Home;
