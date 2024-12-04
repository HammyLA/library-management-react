import React, { useEffect, useState } from "react";
import axios from "axios";
import BookCard from "../components/BookCard";

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
      <ul class="list-group">
        <li class="list-group-item">
          <div className="d-flex flex-row">
            <strong className="p-2">Book List</strong>
            <strong className="p-2">Year</strong>
            <strong className="d-flex justify-content-center p-2">Title</strong>
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
