import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import Members from "./pages/Members";
import Books from "./pages/Books";
import Rentals from "./pages/Rentals";
import Header from "./components/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import CreateBook from "./pages/CreateBook";
import BookDetails from "./pages/BookDetails";
import RentalDetails from "./pages/RentalDetails";
import MemberDetails from "./pages/MemberDetails";
import CreateMember from "./pages/CreateMember";
import CreateRental from "./pages/CreateRental";
import EditBook from "./pages/EditBook";
import "./App.css";
import EditMember from "./pages/EditMember";

function App() {
  const [count, setCount] = useState(0);

  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/books" element={<Books />} />
          <Route path="/members" element={<Members />} />
          <Route path="/rentals" element={<Rentals />} />
          <Route path="/addbook" element={<CreateBook />} />
          <Route path="/books/:id" element={<BookDetails />} />
          <Route path="/rentals/:id" element={<RentalDetails />} />
          <Route path="/members/:id" element={<MemberDetails />} />
          <Route path="/addmember" element={<CreateMember />} />
          <Route path="/addrental/:id" element={<CreateRental />} />
          <Route path="/books/:id/edit" element={<EditBook />} />
          <Route path="/members/:id/edit" element={<EditMember />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
