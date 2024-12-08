import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditBook() {
  const navigate = useNavigate(); // Navigate function for routing
  const { id } = useParams(); // Extracts the book ID from the URL parameters

  // State variables to hold form field values
  const [title, setTitle] = useState(""); // Book title state
  const [author, setAuthor] = useState(""); // Author name state
  const [genre, setGenre] = useState(""); // Book genre state
  const [description, setDescription] = useState(""); // Book description state
  const [yearPublished, setYearPublished] = useState(2024); // Year the book was published (default is 2024)
  const [integerError, setIntegerError] = useState(""); // Error message for invalid year input

  useEffect(() => {
    // Fetch current book data when the component is mounted
    axios
      .get(`http://localhost:8080/api/books/${id}`)
      .then((res) => {
        // Set the fetched data into respective state variables
        setTitle(res.data.title);
        setAuthor(res.data.author);
        setGenre(res.data.genre);
        setDescription(res.data.description);
        setYearPublished(res.data.yearPublished);
      })
      .catch((error) => {
        console.log(error); // Log any error during the fetch operation
      });
  }, [id]); // Dependency array ensures the effect runs when `id` changes

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
      // Send POST request to the API to update the book
      axios
        .post(`http://localhost:8080/api/books/${id}/edit`, {
          title: title,
          author: author,
          genre: genre,
          description: description,
          yearPublished: yearPublished,
        })
        .then(function (response) {
          console.log(response); // Log the successful response from the API
          navigate("/books"); // Redirect to the books list page upon successful update
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
        <h1>Edit Book Details</h1>
      </div>

      {/* Book edit form */}
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
            value={title} // Bind title state to the input field
            onChange={(e) => setTitle(e.target.value)} // Update title state on input change
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
            value={author} // Bind author state to the input field
            onChange={(e) => setAuthor(e.target.value)} // Update author state on input change
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
            value={genre} // Bind genre state to the input field
            onChange={(e) => setGenre(e.target.value)} // Update genre state on input change
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
            value={description} // Bind description state to the input field
            onChange={(e) => setDescription(e.target.value)} // Update description state on input change
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
            value={yearPublished} // Bind yearPublished state to the input field
            onChange={(e) => setYearPublished(e.target.value)} // Update yearPublished state on input change
          />
        </div>

        {/* Display error message if year is invalid */}
        {integerError && (
          <div className="py-3">
            <div className="alert alert-danger" role="alert">
              {integerError} {/* Show the error message if set */}
            </div>
          </div>
        )}

        {/* Submit button */}
        <button type="submit" class="btn btn-primary">
          Edit {/* Button text */}
        </button>
      </form>
    </div>
  );
}

export default EditBook;
