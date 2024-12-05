import React, { useEffect, useState } from "react";
import axios from "axios";
import BookCard from "../components/BookCard";
import { Link } from "react-router-dom";

function Books() {
  const [bookList, setBookList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/books").then((res) => {
      setBookList(res.data);
    });
  }, []);
  console.log(bookList);

  return (
    <div>
      <div className="mx-3 p-2">
        <Link to="/addbook">
          <button className="btn btn-success">+ Book</button>
        </Link>
      </div>

      <ul class="list-group">
        <li class="list-group-item">
          <div className="d-flex flex-row">
            <strong className="col p-2">Book List</strong>
            <strong className="col p-2">Year</strong>
            <strong className="col p-2">Title</strong>
          </div>
        </li>
        {bookList.map((book, index) => (
          <li class="list-group-item" key={index}>
            <BookCard book={book} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Books;
