import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function CreateBook() {
  const navigate = useNavigate();

  // State variables to hold form field values
  const [title, setTitle] = useState(""); // Book title
  const [author, setAuthor] = useState(""); // Author name
  const [genre, setGenre] = useState(""); // Book genre
  const [description, setDescription] = useState(""); // Book description
  const [yearPublished, setYearPublished] = useState(2024); // Year the book was published (default is 2024)
  const [integerError, setIntegerError] = useState(""); // Error message for invalid year input

  // Handles form submission and validation
  const handleSubmit = (event) => {
    event.preventDefault(); // Prevents the default form submission behavior

    // Log the entered data to the console for debugging
    console.log(title);
    console.log(description);
    console.log(yearPublished);

    // Validate the yearPublished field to ensure it is a valid integer
    if (!Number.isInteger(Number.parseInt(yearPublished))) {
      setIntegerError("Enter Valid Year"); // Set error message if year is not valid
    } else {
      // Send POST request to the API to create a new book
      axios
        .post("http://localhost:8080/api/books", {
          title: title,
          author: author,
          genre: genre,
          description: description,
          yearPublished: yearPublished,
        })
        .then(function (response) {
          console.log(response); // Log successful response to the console
          navigate("/books"); // Redirect to the books list page upon successful creation
        })
        .catch(function (error) {
          console.log(error); // Log any errors that occur during the request
        });
    }
  };

  return (
    <div class="container-sm">
      {/* Page header */}
      <div class="d-flex justify-content-center p-5">
        <h1>Submit Book Details</h1>
      </div>

      {/* Book creation form */}
      <form onSubmit={handleSubmit}>
        {/* Title input */}
        <div class="mb-3">
          <label for="BookTitle" class="form-label">
            Enter Title
          </label>
          <input
            name="title"
            type="title"
            class="form-control"
            id="BookTitle"
            value={title}
            onChange={(e) => setTitle(e.target.value)} // Update title state on change
          />
        </div>

        {/* Author input */}
        <div class="mb-3">
          <label for="BookAuthor" class="form-label">
            Author Name
          </label>
          <input
            name="author"
            type="author"
            class="form-control"
            id="BookAuthor"
            value={author}
            onChange={(e) => setAuthor(e.target.value)} // Update author state on change
          />
        </div>

        {/* Genre input */}
        <div class="mb-3">
          <label for="BookGenre" class="form-label">
            Book Genre
          </label>
          <input
            name="genre"
            type="genre"
            class="form-control"
            id="BookGenre"
            value={genre}
            onChange={(e) => setGenre(e.target.value)} // Update genre state on change
          />
        </div>

        {/* Description input */}
        <div class="mb-3">
          <label for="BookDescription" class="form-label">
            Book Description
          </label>
          <textarea
            name="description"
            type="description"
            class="form-control"
            id="BookDescription"
            value={description}
            onChange={(e) => setDescription(e.target.value)} // Update description state on change
          />
        </div>

        {/* Year Published input */}
        <div class="mb-3">
          <label for="BookPublishYear" class="form-label">
            Year Published
          </label>
          <input
            name="year"
            type="year"
            class="form-control"
            id="BookPublishYear"
            value={yearPublished}
            onChange={(e) => setYearPublished(e.target.value)} // Update yearPublished state on change
          />
        </div>

        {/* Display error message if year is invalid */}
        {integerError && (
          <div className="py-3">
            <div className="alert alert-danger" role="alert">
              {integerError}
            </div>
          </div>
        )}

        {/* Submit button */}
        <button type="submit" class="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateBook;
