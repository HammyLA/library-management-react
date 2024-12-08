import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { formatDate } from "../utility/util";

function MemberDetails() {
  const navigate = useNavigate();
  const [memberDetails, setMemberDetails] = useState();
  const [errorMessage, setErrorMessage] = useState("");
  const [renting, setRenting] = useState(false);

  const { id } = useParams();

  useEffect(() => {
    // Fetch member details by member ID
    axios.get(`http://localhost:8080/api/members/${id}`).then((res) => {
      setMemberDetails(res.data);
    });

    // Fetch member's renting status
    axios.get(`http://localhost:8080/api/members/${id}/status`).then((res) => {
      console.log(res.data);
      setRenting(res.data);
    });
  }, [id]);

  const handleEdit = () => {
    navigate(`/members/${id}/edit`);
  };

  const handleRemove = () => {
    setErrorMessage("");
    try {
      // Attempt to delete member
      axios
        .delete(`http://localhost:8080/api/members/${id}`)
        .then(function (response) {
          console.log("Successfully deleted: ", response.data);
          navigate("/members"); // Redirect to members list
        })
        .catch(function (error) {
          if (error.status === 400) {
            setErrorMessage("Member currently renting book");
          } else {
            setErrorMessage("Unexpected Error");
          }
        });
    } catch (error) {
      console.log("Failed Delete", error);
    }
  };

  console.log(memberDetails);

  // Rendering the member details after the data is retrieved
  if (memberDetails) {
    return (
      <>
        <div className="px-3 py-2">
          <button
            className="btn btn-danger"
            disabled={renting}
            onClick={handleRemove}
          >
            Remove Member
          </button>
          <button className="btn btn-primary m-3" onClick={handleEdit}>
            Edit
          </button>
        </div>
        {errorMessage && (
          <div className="p-3">
            <div className="alert alert-danger" role="alert">
              {errorMessage}
            </div>
          </div>
        )}
        <div className="d-flex justify-content-center p-5">
          <h1>Member Details</h1>
        </div>
        <div className="container-sm">
          <h4>ID: {memberDetails.memberid}</h4>
          <h4>
            Name: {memberDetails.firstName} {memberDetails.lastName}
          </h4>
          <h4>Email: {memberDetails.email}</h4>
          <h4>Membership Date: {formatDate(memberDetails.dateOfMembership)}</h4>
          <h4 className={renting ? "text-danger" : "text-success"}>
            Renting Status:{" "}
            {renting ? "Outstanding Rental" : "No Outstanding Rentals"}
          </h4>
        </div>
      </>
    );
  }

  // Render while the data is being fetched
  return (
    <div className="d-flex justify-content-center p-5">
      <h1>Retrieving Information</h1>
    </div>
  );
}

export default MemberDetails;
