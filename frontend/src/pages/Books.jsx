import React, { useEffect, useState } from "react";
import axios from "axios";
import BookCard from "../components/BookCard";
import { Link } from "react-router-dom";
import SearchBar from "../components/SearchBar";
import FilterColumn from "../components/FilterColumn";

function Books() {
  const [bookList, setBookList] = useState([]); // State to store the list of books
  const [genreList, setGenreList] = useState([]); // State to store available genres
  const [selectedGenreList, setSelectedGenreList] = useState([]); // State to store selected genres for filtering

  // Fetch all books and update the genre list when the component mounts
  useEffect(() => {
    axios.get("http://localhost:8080/api/books").then((res) => {
      setBookList(res.data);
      updateGenreList(res.data);
    });
  }, []);

  // Search books by title and genre, updating the book list with the results
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

  // Update genre list by extracting unique genres from the fetched books
  const updateGenreList = (dataList) => {
    let s = new Set([]);
    for (var i = 0; i < dataList.length; i++) {
      s.add(dataList[i].genre);
    }
    setGenreList(Array.from(s).sort());
  };

  // Handle the selection/deselection of genres for filtering
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
      {/* Button to navigate to the add book page */}
      <div className="mx-3 p-2">
        <Link to="/addbook">
          <button className="btn btn-success">+ Book</button>
        </Link>
      </div>

      {/* Main layout with search bar, filters, and book list */}
      <div className="d-flex flex-row border-top">
        <div className="col-3 p-3 container-sm">
          <SearchBar onSearchSubmit={searchBook} /> {/* Search bar component */}
          <h4 className="p-3">Genres</h4>
          <FilterColumn
            filterList={genreList} // Pass available genres
            selectedList={selectedGenreList} // Pass selected genres for filtering
            onFilterChanged={handleFilter} // Handle genre filter changes
          />
        </div>

        {/* Book list rendering */}
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
                <BookCard book={book} />{" "}
                {/* Book card component to display book info */}
              </li>
            ))}
          </ul>
        </div>
      </div>
    </>
  );
}

export default Books;
