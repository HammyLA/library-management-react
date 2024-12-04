import React from "react";
import { Link } from "react-router-dom";

function BookCard(props) {
  return (
    <div className="d-flex flex-row align-items-center">
      <div className="p-2">
        <Link
          to={`/books/${props.book.bookid}`}
          params={{ id: props.book.bookid }}
        >
          <button type="button" className="btn btn-primary">
            Details
          </button>
        </Link>
      </div>
      <div className="p-2">{props.book.yearPublished}</div>
      <div className="p-2 flex-grow-1">{props.book.title}</div>
    </div>
  );
}

export default BookCard;
