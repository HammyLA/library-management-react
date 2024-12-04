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
          <div>Book ID: {bookDetails.bookid}</div>
          <div>Book Title: {bookDetails.title}</div>
          <div>Description: {bookDetails.description}</div>
          <div>Year Published: {bookDetails.yearPublished}</div>
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
