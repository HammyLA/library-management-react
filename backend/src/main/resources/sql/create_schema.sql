-- database we are assigning our data to
USE defaultdb;


-- Set up tables in the database

CREATE TABLE Book (
    bookid INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(80) DEFAULT 'NONE',
    genre VARCHAR(40) NOT NULL,
    description TEXT NOT NULL,
    year_published INT NOT NULL
);

CREATE TABLE Member (
    memberid INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(80) NOT NULL,
    date_of_membership TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Rental (
    rentalid INT AUTO_INCREMENT PRIMARY KEY,
    member INT NOT NULL,
    book INT NOT NULL, 
    day_of_rental TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP DEFAULT (DATE_ADD(day_of_rental, INTERVAL 14 DAY)),
    FOREIGN KEY (member) REFERENCES Member(memberid),
    FOREIGN KEY (book) REFERENCES Book(bookid)
);