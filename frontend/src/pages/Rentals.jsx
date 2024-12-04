import axios from "axios";
import React, { useEffect, useState } from "react";
import RentalCard from "../components/RentalCard";

function Rentals() {
  const [rentalList, setRentalList] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/rentals").then((res) => {
      setRentalList(res.data);
    });
  }, []);

  console.log(rentalList);

  return (
    <div>
      <ul class="list-group">
        <li class="list-group-item">
          <div className="d-flex flex-row">
            <strong className="p-2 flex-fill">Rentals</strong>
            <strong className="p-2 flex-fill">MemID</strong>
            <strong className="p-2 flex-fill">BookID</strong>
            <strong className="p-2 flex-fill">Rent Date</strong>
            <strong className="p-2 flex-fill">Due</strong>
          </div>
        </li>
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
