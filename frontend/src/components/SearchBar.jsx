import axios from "axios";
import React, { useState } from "react";

function SearchBar(props) {
  const [titleSearch, setTitleSearch] = useState("");

  const handleSearch = (event) => {
    event.preventDefault();
    console.log(titleSearch);
    props.onSearchSubmit(titleSearch);
  };

  return (
    <form onSubmit={handleSearch}>
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search Books"
          aria-label="Search Books"
          aria-describedby="button-addon2"
          value={titleSearch}
          onChange={(e) => setTitleSearch(e.target.value)}
        />
        <button
          class="btn btn-outline-secondary"
          type="submit"
          id="button-addon2"
        >
          Search
        </button>
      </div>
    </form>
  );
}

export default SearchBar;
