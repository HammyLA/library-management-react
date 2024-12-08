import React from "react";
import MemberCard from "./MemberCard";

function MembersList(props) {
  return (
    <ul class="list-group">
      <li class="list-group-item">
        <div className="d-flex flex-row">
          <strong className="col p-2">Members</strong>
          <strong className="col p-2">MemID</strong>
          <strong className="col p-2">First Name</strong>
          <strong className="col p-2">Last Name</strong>
        </div>
      </li>
      {props.dataList.map((member, index) => (
        <li class="list-group-item" key={index}>
          <MemberCard member={member} />
        </li>
      ))}
    </ul>
  );
}

export default MembersList;
