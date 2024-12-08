import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function CreateMember() {
  const navigate = useNavigate();

  // State variables to hold form field values
  const [firstName, setFirstName] = useState(""); // First name of the member
  const [lastName, setLastName] = useState(""); // Last name of the member
  const [email, setEmail] = useState(""); // Email address of the member

  // Handles form submission
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevents the default form submission behavior

    // Log the entered first and last name to the console for debugging
    console.log(firstName);
    console.log(lastName);

    // Send POST request to the API to create a new member
    axios
      .post("http://localhost:8080/api/members", {
        firstName: firstName,
        lastName: lastName,
        email: email,
      })
      .then(function (response) {
        console.log(response); // Log successful response to the console
      })
      .catch(function (error) {
        console.log(error); // Log any errors that occur during the request
      });

    // Navigate to the members page after successful submission
    navigate("/members");
  };

  return (
    <div class="container-sm">
      {/* Page header */}
      <div class="d-flex justify-content-center p-5">
        <h1>Membership Details</h1>
      </div>

      {/* Member creation form */}
      <form onSubmit={handleSubmit}>
        {/* First name input */}
        <div class="mb-3">
          <label for="firstName" class="form-label">
            First Name
          </label>
          <input
            name="firstName"
            class="form-control"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)} // Update firstName state on change
          />
        </div>

        {/* Last name input */}
        <div class="mb-3">
          <label for="lastName" class="form-label">
            Last Name
          </label>
          <input
            name="lastName"
            class="form-control"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)} // Update lastName state on change
          />
        </div>

        {/* Email input */}
        <div class="mb-3">
          <label for="email" class="form-label">
            Email Account
          </label>
          <input
            name="email"
            class="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)} // Update email state on change
          />
        </div>

        {/* Submit button */}
        <button type="submit" class="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateMember;
