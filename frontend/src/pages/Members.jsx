import axios from "axios";
import React, { useEffect, useState } from "react";
import MemberCard from "../components/MemberCard";
import { Link } from "react-router-dom";

function Members() {
  const [memberList, setMemberList] = useState([]);
  useEffect(() => {
    axios.get("http://localhost:8080/api/members").then((res) => {
      setMemberList(res.data);
    });
  }, []);

  console.log(memberList);

  return (
    <div>
      <div className="mx-3 p-2">
        <Link to="/addmember">
          <button className="btn btn-success">+ Member</button>
        </Link>
      </div>
      <ul class="list-group">
        <li class="list-group-item">
          <div className="d-flex flex-row">
            <strong className="col p-2">Members</strong>
            <strong className="col p-2">MemID</strong>
            <strong className="col p-2">First Name</strong>
            <strong className="col p-2">Last Name</strong>
          </div>
        </li>
        {memberList.map((member, index) => (
          <li class="list-group-item" key={index}>
            <MemberCard member={member} />
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Members;
