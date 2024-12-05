import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

function BookDetails() {
  const [bookDetails, setBookDetails] = useState();
  const book = useParams();

  console.log(book.id);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/books/${book.id}`).then((res) => {
      setBookDetails(res.data);
    });
  }, []);
  console.log(bookDetails);

  if (bookDetails) {
    return (
      <>
        <div className="container-sm">
          <div className="d-flex justify-content-center p-5">
            <h1>Book Details</h1>
          </div>
          <h4>Book ID: {bookDetails.bookid}</h4>
          <h4>Book Title: {bookDetails.title}</h4>
          <div className="container-sm p-3">
            <h4>Description:</h4>
            <h5>{bookDetails.description}</h5>
          </div>
          <h4>Year Published: {bookDetails.yearPublished}</h4>
        </div>
      </>
    );
  }
  return (
    <div className="d-flex justify-content-center p-5">
      <h1>Please Wait</h1>
    </div>
  );
}

export default BookDetails;
