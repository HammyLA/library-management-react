import React, { useEffect, useState } from "react";
import axios from "axios";
import BookCard from "../components/BookCard";
import { Link } from "react-router-dom";
import SearchBar from "../components/SearchBar";
import FilterColumn from "../components/FilterColumn";

function Books() {
  const [bookList, setBookList] = useState([]);
  const [genreList, setGenreList] = useState([]);
  const [selectedGenreList, setSelectedGenreList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/books").then((res) => {
      setBookList(res.data);
      updateGenreList(res.data);
    });
  }, []);

  const searchBook = (searchedBook) => {
    console.log(selectedGenreList);
    let genres =
      selectedGenreList && selectedGenreList.length > 0
        ? selectedGenreList
        : genreList;
    axios
      .get(`http://localhost:8080/api/books/search`, {
        params: {
          title: searchedBook,
          genres: genres.toString(),
        },
      })
      .then((res) => {
        setBookList(res.data);
      });
  };

  const updateGenreList = (dataList) => {
    let s = new Set([]);
    for (var i = 0; i < dataList.length; i++) {
      s.add(dataList[i].genre);
    }
    setGenreList(Array.from(s).sort());
  };

  const handleFilter = (filter, isChecked) => {
    if (isChecked) {
      setSelectedGenreList((prev) => [...prev, filter]);
    } else {
      setSelectedGenreList((prev) => prev.filter((i) => i != filter));
    }
  };

  console.log(selectedGenreList);
  console.log(bookList);
  console.log(genreList);

  return (
    <>
      {" "}
      <div className="mx-3 p-2">
        <Link to="/addbook">
          <button className="btn btn-success">+ Book</button>
        </Link>
      </div>
      <div className="d-flex flex-row border-top">
        <div className="col-3 p-3 container-sm">
          <SearchBar onSearchSubmit={searchBook} />
          <h4 className="p-3">Genres</h4>
          <FilterColumn
            filterList={genreList}
            selectedList={selectedGenreList}
            onFilterChanged={handleFilter}
          />
        </div>
        <div className="col container-md">
          <ul class="list-group">
            <li class="list-group-item">
              <div className="d-flex flex-row">
                <strong className="col p-2">Book List</strong>
                <strong className="col p-2">Year</strong>
                <strong className="col p-2">Title</strong>
                <strong className="col p-2">Author</strong>
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
