import React from "react";

function CreateBook() {
  const handleSubmit = (event) => {
    event.preventDefault();
    alert(`You selected: ${selectedYear}`);
  };

  return (
    <div class="container-sm">
      <div class="d-flex justify-content-center p-5">
        <h1>Submit Book Details</h1>
      </div>

      <form onSubmit={handleSubmit}>
        <div class="mb-3">
          <label for="BookTitle" class="form-label">
            BookTitle
          </label>
          <input type="title" class="form-control" id="BookTitle" />
        </div>
        <div class="mb-3">
          <label for="BookDescription" class="form-label">
            Book Description
          </label>
          <input type="description" class="form-control" id="BookDescription" />
        </div>
        <div class="mb-3">
          <label for="BookPublishYear" class="form-label">
            Year Published
          </label>
          <input type="year" class="form-control" id="BookPublishYear" />
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
