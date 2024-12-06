import axios from "axios";
import React, { useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import { formatDate } from "../utility/util";

function RentalCard(props) {


  return (
    <div className="d-flex flex-row align-items-center">
      <div className="p-2 col">
        <Link
          to={`/rentals/${props.rental.rentalid}`}
          params={{ id: props.rental.rentalid }}
        >
          <button type="button" className="btn btn-primary">
            Details
          </button>
        </Link>
      </div>
      <div className="p-2 col">{props.rental.member}</div>
      <div className="p-2 col">{props.rental.book}</div>
      <div className="p-2 col">{formatDate(props.rental.dueDate)}</div>
    </div>
  );
}

export default RentalCard;
