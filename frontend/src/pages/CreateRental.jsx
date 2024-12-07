import axios from "axios";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Members from "./Members";

function CreateRental() {
  const navigate = useNavigate();
  const props = useParams();

  const [member, setMember] = useState(-1);
  const [book, setBook] = useState(props.id);

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(member);
    console.log(book);

    if (member > 0 && book > 0) {
      const payload = {
        member: member,
        book: book,
      };
      axios
        .post("http://localhost:8080/api/rentals", payload)
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);
        });

      navigate("/rentals");
    }
  };

  return (
    <div class="container-sm">
      <div class="d-flex justify-content-center p-5">
        <h1>Rental Details</h1>
      </div>

      <div className="d-flex flex-row">
        <div className="col">
          <form onSubmit={handleSubmit}>
            <div class="mb-3">
              <label htmlFor="member" class="form-label">
                Member ID
              </label>
              <input
                name="member"
                class="form-control"
                value={member}
                onChange={(e) => setMember(e.target.value)}
              />
            </div>
            <div class="mb-3">
              <label htmlFor="book" class="form-label">
                Book ID
              </label>
              <input
                name="book"
                class="form-control"
                value={book}
                onChange={(e) => setBook(e.target.value)}
              />
            </div>
            <button type="submit" class="btn btn-primary">
              Submit
            </button>
          </form>
        </div>
        <div className="col">
          <Members />
        </div>
      </div>
    </div>
  );
}

export default CreateRental;
