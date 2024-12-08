import axios from "axios";
import React, { useEffect, useState } from "react";
import RentalCard from "../components/RentalCard";

function Rentals() {
  const [rentalList, setRentalList] = useState([]);
  const [overdue, setOverdue] = useState(false);

  useEffect(() => {
    // Fetch all rentals from the backend when the component is mounted
    axios.get("http://localhost:8080/api/rentals").then((res) => {
      setRentalList(res.data);
    });
  }, []);

  function onFilterChanged(checked) {
    // Handle the filtering of rentals based on the overdue status
    if (checked) {
      setOverdue(true);
      // Fetch only overdue rentals from the backend
      axios.get("http://localhost:8080/api/rentals/overdue").then((res) => {
        setRentalList(res.data);
      });
    } else {
      setOverdue(false);
      // Fetch all rentals from the backend when the overdue filter is cleared
      axios.get("http://localhost:8080/api/rentals").then((res) => {
        setRentalList(res.data);
      });
    }
  }

  console.log(rentalList);

  return (
    <div>
      <div class="p-3 px-4 mb-3 mx-4 form-check">
        {/* Checkbox to filter overdue rentals */}
        <input
          type="checkbox"
          class="form-check-input"
          id={overdue}
          value={overdue}
          checked={overdue}
          onChange={(e) => onFilterChanged(e.target.checked)}
        />
        <label class="form-check-label" for={overdue}>
          <strong>Overdue</strong>
        </label>
      </div>
      <ul class="list-group">
        <li class="list-group-item">
          {/* Rental list headers */}
          <div className="d-flex flex-row">
            <strong className="p-2 col">Rentals</strong>
            <strong className="p-2 col">MemID</strong>
            <strong className="p-2 col">BookID</strong>
            <strong className="p-2 col">Due</strong>
          </div>
        </li>
        {/* Render each rental in the list */}
        {rentalList.map((rental, index) => (
          <li class="list-group-item" key={index}>
            <RentalCard rental={rental} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Rentals;
