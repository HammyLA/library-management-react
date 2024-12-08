import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditMember() {
  const navigate = useNavigate(); // Navigate function for routing
  const { id } = useParams(); // Extracts the member ID from the URL parameters

  // State variables to hold form field values
  const [firstName, setFirstName] = useState(""); // First name of the member state
  const [lastName, setLastName] = useState(""); // Last name of the member state
  const [email, setEmail] = useState(""); // Email of the member state

  useEffect(() => {
    // Fetch current member data when the component is mounted
    axios
      .get(`http://localhost:8080/api/members/${id}`)
      .then((res) => {
        // Set the fetched data into respective state variables
        setFirstName(res.data.firstName);
        setLastName(res.data.lastName);
        setEmail(res.data.email);
      })
      .catch((error) => {
        console.log(error); // Log any error during the fetch operation
      });
  }, [id]); // Dependency array ensures the effect runs when `id` changes

  // Handles form submission
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevents the default form submission behavior

    // Log the entered first and last name to the console for debugging
    console.log(firstName);
    console.log(lastName);

    // Send POST request to the API to update the member details
    axios
      .post(`http://localhost:8080/api/members/${id}/edit`, {
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
        <h1>Edit Member Details</h1>
      </div>

      {/* Member edit form */}
      <form onSubmit={handleSubmit}>
        {/* First name input */}
        <div class="mb-3">
          <label for="firstName" class="form-label">
            First Name
          </label>
          <input
            name="firstName"
            class="form-control"
            value={firstName} // Bind firstName state to the input field
            onChange={(e) => setFirstName(e.target.value)} // Update firstName state on input change
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
            value={lastName} // Bind lastName state to the input field
            onChange={(e) => setLastName(e.target.value)} // Update lastName state on input change
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
            value={email} // Bind email state to the input field
            onChange={(e) => setEmail(e.target.value)} // Update email state on input change
          />
        </div>

        {/* Submit button */}
        <button type="submit" class="btn btn-primary">
          Submit {/* Button text */}
        </button>
      </form>
    </div>
  );
}

export default EditMember;
