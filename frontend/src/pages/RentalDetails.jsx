import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { formatDate } from "../utility/util";

function RentalDetails() {
  const [rentalDetails, setRentalDetails] = useState();
  const rentalid = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/rentals/${rentalid.id}/details`)
      .then((res) => {
        setRentalDetails(res.data);
      });
  }, []);

  console.log(rentalDetails);

  if (rentalDetails) {
    return (
      <>
        <div className="d-flex justify-content-center p-5">
          <h1>Rental Details</h1>
        </div>
        <div className="container-sm">
          <div className="d-flex">
            <h2>Book Information</h2>
          </div>
          <div className="mx-5 p-2">
            <h5>ID: {rentalDetails.book.bookid} </h5>
            <h5>Title: {rentalDetails.book.title} </h5>
            <h5>Description: {rentalDetails.book.description} </h5>
            <h5>Year Published: {rentalDetails.book.yearPublished} </h5>
          </div>
        </div>
        <div className="container-sm">
          <div className="d-flex">
            <h2>Member Information</h2>
          </div>
          <div className="mx-5 p-2">
            <h5>ID: {rentalDetails.member.memberid} </h5>
            <h5>Name: {rentalDetails.member.firstName} {rentalDetails.member.lastName} </h5>
          </div>
        </div>
        <div className="container-sm">
          <div className="d-flex">
            <h2>Rental Information</h2>
          </div>
          <div className="mx-5 p-2">
            <h5>Date Rented: {formatDate(rentalDetails.rental.dayOfRental)}</h5>
            <h5>Date Due: {formatDate(rentalDetails.rental.dueDate)}</h5>
          </div>
        </div>
      </>
    );
  }
  return (
    <div className="d-flex justify-content-center p-5">
      <h1>Retrieving Details</h1>
    </div>
  );
}

export default RentalDetails;