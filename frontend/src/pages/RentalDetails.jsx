import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { formatDate } from "../utility/util";

function RentalDetails() {
  const navigate = useNavigate();
  const [rentalDetails, setRentalDetails] = useState();
  const [isOverdue, setIsOverdue] = useState(false);
  const [overdueCharge, setOverdueCharge] = useState(0.0);
  const rentalid = useParams();

  useEffect(() => {
    // Fetch rental details from the backend using the rental ID from the URL
    axios
      .get(`http://localhost:8080/api/rentals/${rentalid.id}/details`)
      .then((res) => {
        setRentalDetails(res.data);
        // Calculate overdue charge based on the difference in days between the current date and the due date
        setOverdueCharge(
          new Date().getDate() - new Date(res.data.rental.dueDate).getDate()
        );
      });

    // Check if the rental is overdue
    axios
      .get(`http://localhost:8080/api/rentals/${rentalid.id}/overdue`)
      .then((res) => {
        setIsOverdue(res.data);
      });
  }, []);

  const handleRemove = () => {
    // Delete the rental record from the backend
    try {
      const response = axios.delete(
        `http://localhost:8080/api/rentals/${rentalid.id}`
      );
      console.log("Successfully deleted: ", response.data);
    } catch (error) {
      console.log("Failed Delete", error);
    }
    navigate("/rentals");
  };

  console.log(isOverdue);
  console.log(overdueCharge);
  console.log(rentalDetails);

  if (rentalDetails) {
    return (
      <>
        <div className="px-3 py-2">
          {/* Button to remove the rental */}
          <button className="btn btn-danger" onClick={handleRemove}>
            Remove Rental
          </button>
        </div>
        <div className="d-flex justify-content-center p-3">
          <h1>
            <strong>Rental Details</strong>
          </h1>
        </div>
        <div className="container-sm">
          <div className="d-flex">
            <h2>Rental Information</h2>
          </div>
          <div className="mx-5 p-2">
            {/* Display rental information such as dates and overdue charge */}
            <h5>Date Rented: {formatDate(rentalDetails.rental.dayOfRental)}</h5>
            <h5>Date Due: {formatDate(rentalDetails.rental.dueDate)}</h5>
            <h5 className={isOverdue ? "text-danger" : "text-success"}>
              <div>
                <strong>{isOverdue ? "OVERDUE" : "Not Overdue"}</strong>
              </div>
              <div>
                <strong>
                  {isOverdue ? "$ " + overdueCharge + ".00" : null}
                </strong>
              </div>
            </h5>
          </div>
        </div>
        <div className="container-sm">
          <div className="d-flex">
            <h2>Book Information</h2>
          </div>
          <div className="mx-5 p-2">
            {/* Display book information such as ID, title, description, and year published */}
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
            {/* Display member information such as ID, name, and email */}
            <h5>ID: {rentalDetails.member.memberid} </h5>
            <h5>
              Name: {rentalDetails.member.firstName}{" "}
              {rentalDetails.member.lastName}{" "}
            </h5>
            <h5>Email: {rentalDetails.member.email}</h5>
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
