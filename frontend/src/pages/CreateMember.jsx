import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function CreateMember() {
  const navigate = useNavigate();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(firstName);
    console.log(lastName);

    axios
      .post("http://localhost:8080/api/members", {
        firstName: firstName,
        lastName: lastName,
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });

    navigate("/members");
  };

  return (
    <div class="container-sm">
      <div class="d-flex justify-content-center p-5">
        <h1>Membership Details</h1>
      </div>

      <form onSubmit={handleSubmit}>
        <div class="mb-3">
          <label for="firstName" class="form-label">
            First Name
          </label>
          <input
            name="firstName"
            class="form-control"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>
        <div class="mb-3">
          <label for="lastName" class="form-label">
            Last Name
          </label>
          <input
            name="lastName"
            class="form-control"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>
        <button type="submit" class="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateMember;
