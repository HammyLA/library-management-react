import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function BookDetails() {
  const navigate = useNavigate();
  const [bookDetails, setBookDetails] = useState(); // Book details state
  const [isRented, setIsRented] = useState(); // Rental status state
  const book = useParams(); // Extract book ID from URL

  useEffect(() => {
    // Fetch book details and rental status
    axios
      .get(`http://localhost:8080/api/books/${book.id}`)
      .then((res) => setBookDetails(res.data));
    axios
      .get(`http://localhost:8080/api/books/${book.id}/status`)
      .then((res) => setIsRented(res.data));
  }, []);

  // Navigate to rental page
  const handleAdd = () => navigate(`/addrental/${book.id}`);

  // Delete book from system and navigate back to books list
  const handleRemove = () => {
    try {
      axios.delete(`http://localhost:8080/api/books/${book.id}`);
    } catch (error) {
      console.log("Failed Delete", error);
    }
    navigate("/books");
  };

  // Render book details if available
  if (bookDetails) {
    return (
      <>
        <div className="p-4">
          <button
            className="btn btn-success m-3"
            disabled={isRented}
            onClick={handleAdd}
          >
            Rent Book
          </button>
          <button
            className="btn btn-danger m-3"
            disabled={isRented}
            onClick={handleRemove}
          >
            Remove Book
          </button>
        </div>
        <div className="d-flex justify-content-center p-3">
          <h1>Book Details</h1>
        </div>
        <div className="container-sm border border-secondary p-5">
          <h1>{bookDetails.title}</h1>
          <div className="d-flex flex-row">
            <div className="col-4 m-5">
              <h4>ID: {bookDetails.bookid}</h4>
              <h4>Author: {bookDetails.author}</h4>
              <h4>Genre: {bookDetails.genre}</h4>
              <h4>Year Published: {bookDetails.yearPublished}</h4>
              <h4 className={isRented ? "text-danger" : "text-success"}>
                Availability: {isRented ? "Unavailable" : "Available"}
              </h4>
            </div>
            <div className="col m-5">
              <h4>About</h4>
              <h5>{bookDetails.description}</h5>
            </div>
          </div>
        </div>
      </>
    );
  }

  // Loading state if book details are not yet fetched
  return (
    <div className="d-flex justify-content-center p-5">
      <h1>Please Wait</h1>
    </div>
  );
}

export default BookDetails;
