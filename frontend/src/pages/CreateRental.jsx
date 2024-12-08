import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import MembersList from "../components/MembersList";
import SearchBar from "../components/SearchBar";

function CreateRental() {
  const navigate = useNavigate();
  const props = useParams();

  // State variables to hold form field values and error message
  const [member, setMember] = useState(-1); // ID of the selected member
  const [book, setBook] = useState(props.id); // ID of the selected book (from route params)
  const [memberList, setMemberList] = useState([]); // List of members to be shown in the search
  const [errorMessage, setErrorMessage] = useState(""); // Holds error message to be displayed

  // Fetch member list when the component mounts
  useEffect(() => {
    axios.get("http://localhost:8080/api/members").then((res) => {
      setMemberList(res.data); // Store the list of members in state
    });
  }, []);

  // Search for members by name when user submits search
  const searchMember = (searchName) => {
    console.log(searchName); // Log the search term for debugging
    axios
      .get("http://localhost:8080/api/members/search", {
        params: {
          name: searchName, // Search members by name
        },
      })
      .then((res) => {
        setMemberList(res.data); // Update the member list with the search result
      });
  };

  // Handle rental form submission
  const handleSubmit = async (event) => {
    event.preventDefault(); // Prevent default form behavior (page reload)

    setErrorMessage(""); // Reset error message

    // Validate if the member exists
    const memberResponse = await axios.get(
      `http://localhost:8080/api/members/${member}/exists`
    );
    const memberValid = memberResponse.data; // Get response indicating if the member exists

    // Validate if the book exists
    const bookResponse = await axios.get(
      `http://localhost:8080/api/books/${book}/exists`
    );
    const bookValid = bookResponse.data; // Get response indicating if the book exists

    // If both member and book are valid, proceed with rental creation
    if (bookValid && memberValid) {
      const payload = {
        member: member,
        book: book,
      };

      // Send POST request to create a rental
      axios
        .post("http://localhost:8080/api/rentals", payload)
        .then(function (response) {
          console.log(response); // Log response from the server
          navigate("/rentals"); // Redirect to the rentals page upon success
        })
        .catch(function (error) {
          if (error.response) {
            // If the server responds with an error
            console.log("Error status:", error.response.status); // Log error status
            if (error.response.status === 400) {
              setErrorMessage("Book is Already Rented"); // Handle book already rented error
            } else {
              setErrorMessage("An unexpected error occurred."); // Handle other errors
            }
          }
        });
    } else {
      setErrorMessage("Enter valid values"); // Set error message if member or book is invalid
    }
  };

  return (
    <div class="container-sm">
      {/* Page header */}
      <div class="d-flex justify-content-center p-5">
        <h1>Rental Details</h1>
      </div>

      {/* Form and search functionality */}
      <div className="d-flex flex-row">
        <div className="col p-3">
          <form onSubmit={handleSubmit}>
            {/* Member ID input field */}
            <div class="mb-3">
              <label htmlFor="member" class="form-label">
                Member ID
              </label>
              <input
                name="member"
                class="form-control"
                value={member}
                onChange={(e) => setMember(e.target.value)} // Update member ID on change
              />
            </div>

            {/* Book ID input field */}
            <div class="mb-3">
              <label htmlFor="book" class="form-label">
                Book ID
              </label>
              <input
                name="book"
                class="form-control"
                value={book}
                onChange={(e) => setBook(e.target.value)} // Update book ID on change
              />
            </div>

            {/* Submit button */}
            <button type="submit" class="btn btn-primary">
              Submit
            </button>

            {/* Error message display */}
            {errorMessage && (
              <div className="py-3">
                <div className="alert alert-danger" role="alert">
                  {errorMessage}
                </div>
              </div>
            )}
          </form>
        </div>

        {/* Member search and list */}
        <div className="col">
          <SearchBar onSearchSubmit={searchMember} />{" "}
          {/* Search bar for member search */}
          <MembersList dataList={memberList} /> {/* Display list of members */}
        </div>
      </div>
    </div>
  );
}

export default CreateRental;
