import React from "react";
import { Link } from "react-router-dom";

function BookCard(props) {
  return (
    <div className="d-flex flex-row align-items-center">
      <div className="col p-2">
        {/* Link to the book details page */}
        <Link
          to={`/books/${props.book.bookid}`}
          params={{ id: props.book.bookid }}
        >
          <button type="button" className="btn btn-primary">
            Details
          </button>
        </Link>
      </div>
      <div className="col p-2">{props.book.yearPublished}</div>
      <div className="col p-2">{props.book.title}</div>
      <div className="col p-2">{props.book.author}</div>
    </div>
  );
}

export default BookCard;
