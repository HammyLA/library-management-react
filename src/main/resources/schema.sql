
-- post

/*
CREATE TABLE Book (
    bookid INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    year_published INT NOT NULL
);
*/

CREATE TABLE Member (
    memberid INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    date_of_membership TIMESTAMP NOT NULL
)