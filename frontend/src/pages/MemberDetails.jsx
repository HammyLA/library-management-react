import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { formatDate } from "../utility/util";

function MemberDetails() {
  const [memberDetails, setMemberDetails] = useState();

  const memberid = useParams();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/members/${memberid.id}`)
      .then((res) => {
        setMemberDetails(res.data);
      });
  }, []);

  console.log(memberDetails);

  if (memberDetails) {
    return (
      <>
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
        </div>
      </>
    );
  }
  return (
    <div className="d-flex justify-content-center p-5">
      <h1>Retrieving Information</h1>
    </div>
  );
}

export default MemberDetails;
