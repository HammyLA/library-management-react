import axios from "axios";
import React, { useEffect, useState } from "react";
import MemberCard from "../components/MemberCard";
import { Link } from "react-router-dom";
import SearchBar from "../components/SearchBar";
import MembersList from "../components/MembersList";

function Members() {
  const [memberList, setMemberList] = useState([]);

  useEffect(() => {
    // Fetch the list of members from the API
    axios.get("http://localhost:8080/api/members").then((res) => {
      setMemberList(res.data);
    });
  }, []);

  const searchMember = (searchName) => {
    console.log(searchName);
    // Search members by name through the API
    axios
      .get("http://localhost:8080/api/members/search", {
        params: {
          name: searchName,
        },
      })
      .then((res) => {
        setMemberList(res.data); // Update member list with search results
      });
  };

  console.log(memberList);

  return (
    <div>
      <div className="mx-3 py-3">
        {/* Link to add a new member */}
        <Link to="/addmember">
          <button className="btn btn-success">+ Member</button>
        </Link>
        {/* Search bar for searching members */}
        <div className="col-5 p-3 container-sm">
          <SearchBar onSearchSubmit={searchMember} />
        </div>
      </div>

      <div className="d-flex flex-row">
        <div className="col">
          {/* Display the list of members */}
          <MembersList dataList={memberList} />
        </div>
      </div>
    </div>
  );
}

export default Members;
