import React, { useEffect, useState } from "react";
import axios from "axios";
import BookCard from "../components/BookCard";
import { Link } from "react-router-dom";
import SearchBar from "../components/SearchBar";

function Books() {
  const [bookList, setBookList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/books").then((res) => {
      setBookList(res.data);
    });
  }, []);

  const searchBook = (searchedBook) => {
    console.log(searchedBook)
    axios.get(`http://localhost:8080/api/books/search`, {
      params: {
        title: searchedBook,
      }
    }).then((res) => {
      setBookList(res.data);
    });
  };

  console.log(bookList);

  return (
    <>
      {" "}
      <div className="mx-3 p-2 border-dark">
        <Link to="/addbook">
          <button className="btn btn-success">+ Book</button>
        </Link>
      </div>
      <div className="d-flex flex-row">
        <div className="col-3 p-3 container-sm">
          <SearchBar onSearchSubmit={searchBook} />
        </div>
        <div className="col container-md">
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
      </div>
    </>
  );
}

export default Books;
