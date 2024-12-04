import axios from "axios";
import React, { useEffect } from "react";
import { Link, useParams } from "react-router-dom";

function RentalCard(props) {


  return (
    <div className="d-flex align-items-center">
      <div className="p-2 flex-sm-grow-1">
        <Link
          to={`/rentals/${props.rental.rentalid}`}
          params={{ id: props.rental.rentalid }}
        >
          <button type="button" className="btn btn-primary">
            Details
          </button>
        </Link>
      </div>
      <div className="p-2 flex-sm-grow-1">{props.rental.member.id}</div>
      <div className="p-2 flex-sm-grow-1">{props.rental.book.id}</div>
      <div className="p-2 flex-sm-grow-1">{props.rental.dayOfRental}</div>
      <div className="p-2 flex-sm-grow-1">{props.rental.dueDate}</div>
    </div>
  );
}

export default RentalCard;
