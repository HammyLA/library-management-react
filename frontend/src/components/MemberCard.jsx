import React from "react";
import { Link } from "react-router-dom";

function MemberCard(props) {
  return (
    <div className="d-flex align-items-center">
      <div className="p-2 col">
        <Link
          to={`/members/${props.member.memberid}`}
          params={{ id: props.member.memberid }}
        >
          <button type="button" className="btn btn-primary">
            Details
          </button>
        </Link>
      </div>
      <div className="p-2 col">{props.member.memberid}</div>
      <div className="p-2 col">{props.member.firstName}</div>
      <div className="p-2 col">{props.member.lastName}</div>
    </div>
  );
}

export default MemberCard;
