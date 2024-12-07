import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function CreateBook() {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [genre, setGenre] = useState("");
  const [description, setDescription] = useState("");
  const [yearPublished, setYearPublished] = useState(2024);

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(title);
    console.log(description);
    console.log(yearPublished);

    axios
      .post("http://localhost:8080/api/books", {
        title: title,
        author: author,
        genre: genre,
        description: description,
        yearPublished: yearPublished,
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });

    navigate(("/books"));
  };

  return (
    <div class="container-sm">
      <div class="d-flex justify-content-center p-5">
        <h1>Submit Book Details</h1>
      </div>

      <form onSubmit={handleSubmit}>
        <div class="mb-3">
          <label for="BookTitle" class="form-label">
            Enter Title
          </label>
          <input
            name="title"
            type="title"
            class="form-control"
            id="BookTitle"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div class="mb-3">
          <label for="BookAuthor" class="form-label">
            Author Name
          </label>
          <input
            name="author"
            type="author"
            class="form-control"
            id="BookAuthor"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
          />
        </div>
        <div class="mb-3">
          <label for="BookGenre" class="form-label">
            Book Genre
          </label>
          <input
            name="genre"
            type="genre"
            class="form-control"
            id="BookGenre"
            value={genre}
            onChange={(e) => setGenre(e.target.value)}
          />
        </div>
        <div class="mb-3">
          <label for="BookDescription" class="form-label">
            Book Description
          </label>
          <textarea
            name="description"
            type="description"
            class="form-control"
            id="BookDescription"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>
        <div class="mb-3">
          <label for="BookPublishYear" class="form-label">
            Year Published
          </label>
          <input
            name="year"
            type="year"
            class="form-control"
            id="BookPublishYear"
            value={yearPublished}
            onChange={(e) => setYearPublished(e.target.value)}
          />
        </div>
        <div class="mb-3 form-check">
          <input type="checkbox" class="form-check-input" id="exampleCheck1" />
          <label class="form-check-label" for="exampleCheck1">
            Check me out
          </label>
        </div>
        <button type="submit" class="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateBook;
