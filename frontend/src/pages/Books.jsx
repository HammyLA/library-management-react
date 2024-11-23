import React, { useEffect } from "react";
import axios from "axios";

function Books() {
  useEffect(() => {
    axios.get("http://localhost:8080/api/books").then((res) => {
      console.log(res.data);
      console.log(res.data[1]);
    });
  }, []);

  return <div>Books Page</div>;
}

export default Books;
