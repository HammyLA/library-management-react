import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import Members from "./pages/Members";
import Books from "./pages/Books";
import Rentals from "./pages/Rentals";
import Header from "./components/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import CreateBook from "./pages/CreateBook";

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
        </Routes>
      </Router>
    </div>
  );
}

export default App;
