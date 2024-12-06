import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

function BookDetails() {
  const [bookDetails, setBookDetails] = useState();
  const [isRented, setIsRented] = useState();
  const book = useParams();

  console.log(book.id);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/books/${book.id}`).then((res) => {
      setBookDetails(res.data);
    });
    axios
      .get(`http://localhost:8080/api/books/${book.id}/status`)
      .then((res) => {
        console.log(res);
        setIsRented(res.data);
      });
  }, []);

  console.log(bookDetails);
  console.log(isRented);

  if (bookDetails) {
    return (
      <>
        <div className="container-sm">
          <div className="d-flex justify-content-center p-5">
            <h1>Book Details</h1>
          </div>
          <div className="d-flex flex-row border border-secondary">
            <div className="col-4 p-5">
              <h4>ID: {bookDetails.bookid}</h4>
              <h4>Title: {bookDetails.title}</h4>
              <h4>Author: {bookDetails.author}</h4>
              <h4>Genre: {bookDetails.genre}</h4>
              <h4>Year Published: {bookDetails.yearPublished}</h4>
              <h4 className={isRented ? "text-danger" : "text-success"}>Availability: {isRented ? "Unavailable" : "Available"} </h4>
            </div>
            <div className="col">
              <div className="p-5">
                <h4>About</h4>
                <h5>{bookDetails.description}</h5>
              </div>
            </div>
          </div>
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
