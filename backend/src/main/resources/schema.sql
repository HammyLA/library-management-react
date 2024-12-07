-- Use this sql file to flush the database and reset all data within it

-- database we are assigning our data to
USE defaultdb;

-- Delete the Tables
DROP TABLE Rental;
DROP TABLE Book;
DROP TABLE Member;

-- Set up tables in the database

CREATE TABLE Book (
    bookid INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(1024) NOT NULL,
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

INSERT INTO Book (title, author, genre, description, year_published)
VALUES
('On The Origin Of Species', 'Charles Darwin', 'Educational', 'There''s been a handful of controversial books that have shaken up the very ground in which we build our beliefs on…. and Charles Darwin''s famous ''On the origin of species'' has been like no other. It all started on December 27, 1831, when the young naturalist left Plymouth Harbor aboard the HMS Beagle to spend the next (what turned out to be five long) years gallivanting around the globe conducting research on plants, animals and the environment in which they preside. His adventure turned into one of the greatest discoveries in the history of mankind - the theory of evolution. What Darwin found along his travels, and what he would eventually declare to the scientific community and broader world, was that existing organisms better suited for adaptation to their environment survive, while those that are poorly suited to their environment do not.', 1859),
('The Great Gatsby', 'F Scott Fitzgerald', 'Realistic', 'The Great Gatsby was written in 1925 by an American writer F. Scott Fitzgerald and set in the Jazz Age on Long Island. The novel depicts narrator Nick Carraway''s interactions with mysteriously wealthy Jay Gatsby, his obsession to reunite with his former lover, Daisy Buchanan and his love of lavish parties at a time when The New York Times noted ''gin was the national drink and sex the national obsession''. Generations of readers have imagined, speculated, debated and thoroughly enjoyed the story and has become a true classic of twentieth-century literature. This was Fitzgerald''s third book, as well as the highest achievement of his career, however It wasn''t until F Scott Fitzgerald passed away that his work really began to gain interest. Eventually his book became a core part of most high school curricula in western cultures, especially American, with the strong focus of American popular culture. Numerous stage and film adaptations followed in the subsequent decades.', 1925),
('The Catcher in the Rye', 'J.D. Sallinger', 'Realistic', 'The Catcher in the Rye, was written by J.D. Salinger and published in 1951. The novel is set around the 1950s and is narrated by a young man named Holden Caulfield, who while telling the story, makes it clear that he is undergoing treatment in a mental hospital or sanatorium. The novel details two days in the life of the 16-year-old after he''s been expelled from school and touches on the emotions, instability and confusion that he''s experiencing with the ''phoniness'' of the adult world. This novel has become a fundamental part of western curriculum as its themes of angst, alienation, and critique on superficiality in society is an important read for adolescents perhaps experiencing similar emotions. The novel also deals with complex issues of innocence, identity, belonging, loss, connection, sex, and depression.', 1951),
('War and Peace', 'Leo Tolstoy', 'War', 'War and Peace is a novel by the Russian author Leo Tolstoy and published in its entirety in 1869. Often called the greatest novel ever written, and regarded as one of Tolstoy''s finest literary achievements, this historical war epic is a celebration of Russian spirit. The novel chronicles the French invasion of Russia, or the Napoleonic wars, and follows the transformation of five aristocratic families against the backdrop of living through tragedy and war. Tolstoy''s memorable and well described characters seek fulfillment, fall in love, make mistakes, and become scarred by war in different ways. Offering a profound look into a human''s experience during a hugely impactful historical event.', 1869),
('Great Expectations', 'Charles Dickens', 'Gothic', 'Great Expectations is the thirteenth novel by Charles Dickens and was first published as a series of stories in Dickens''s weekly periodical from 1 December 1860 to August 1861. The story follows Pip who leads a simple life until a bitter gentlewoman employs him as a sometime companion to herself and her adopted daughter. The novel is set in Kent and London in the 1900''s and is full of themes include wealth and poverty, love and rejection, and the eventual triumph of good over evil. Great Expectations, has been translated into many languages and adapted into a film and various other forms of media. Upon its release, the novel received near universal acclaim.', 1861),
('Hamlet', 'William Shakespeare', 'Play', 'Hamlet is a tragedy by William Shakespeare, believed to have been written between 1599 and 1601. It is Shakespeare''s longest play, with 29,551 words. The play is set in Denmark and depicts Prince Hamlet, his revenge against his uncle, Claudius, who has murdered Hamlet''s father in order to seize his throne and marry Hamlet''s mother. The play vividly charts the course of real and feigned madness. Hamlet is considered one of the most profound and influential works of world literature, and was one of Shakespeare''s most popular works during his lifetime.', 1623),
('To Kill a Mockingbird', 'Harper Lee', 'Thriller', 'To Kill a Mockingbird is a novel by Harper Lee, an American that found instant success when he published in 1960. A coming-of-age story, set in the Great Depression, the whole book is narrated by a six-year-old girl called Jean Louise “Scout” Finch over the timeframe of three years. She lives with her brother, Jem, and their widowed father and lawyer, Atticus. Throughout the book, Harper Lee explores with exuberant humour the irrationality of adult attitudes to race, violence, and class in the Deep South of the 1930s. The historian Joseph Crespino explains, "In the twentieth century, To Kill a Mockingbird is probably the most widely read book dealing with race in America, and its main character, Atticus Finch, the most enduring fictional image of racial heroism."', 1960),
('Moby Dick', 'Herman Melville', 'Adventure', 'Moby-Dick is an adventure novel written by American writer Herman Melville in 1851. Commonly known as a ''challenging read'', the book follows a sailor Ishmael''s and his obsessive quest of Ahab, a captain of a whaling ship. Ahab wishes to find revenge upon the whale Moby-Dick, who destroyed his last ship and took his leg. Throughout their voyage Ishmael questions all aspects of life. The story features gay marriage, and is thought to be one of the first modern novels to showcase a gay couple. It hits out at slavery and predicts the climate crisis - 200 years after it was written, it''s never been more important and is often considered the exemplar of American Romanticism.', 1851),
('The Alchemist', 'Paulo Coelho', 'Adventure', 'The Alchemist was written by brazilian author Paulo Coelho and was published in 1988. The book follows a young Andalusian shepherd, Santiago, on his journey to the Egyptian pyramids, after experiencing recurring dreams of finding a treasure there. The story delicately combines a mix of magic, mysticism, wisdom and wonder into an inspiring tale of self-discovery. Paulo Coelho spent years and years trying to turn his art into the modern classic we see today and has achieved success by selling millions of copies around the world.', 1988),
('Brave New World', 'Aldous Huxley', 'Dystopian', 'Brave New World is a dystopian social science fiction novel by English author Aldous Huxley and was published in 1932. The story tells of a terrifying vision of the future, where a perfect society achieves peace and stability through the prohibition of monogamy, privacy, money, and accepts complete surveillance. Just like 1984, the novel that came before it, this is a spine-chilling peak into a dystopian world that leaves you speculating, asking questions, and looking for ways to live off grid.', 1932),
('Don Quixote', 'Miguel de Cervantes', 'Parody', 'Don Quixote was written by Miguel de Cervantes Don Quixote in the 16th century. Brimming with romance and adventure, his book is considered to be the greatest work in the Spanish literary world. It''s the tale of Quixote, who''s been driven mad by reading too many chivalric romances that he determines to become a knight-errant himself.', 1605),
('Crime and Punishment', 'Fyodor Dostoevsky', 'Crime', 'Crime and Punishment was first published in twelve monthly installments during 1866 by Russian writer Fyodor Dostoyevsky. The book was written on the return from ten years of exile in Siberia and is considered first great novel from the period of writing. The book follows Raskolnikov through the slums of St Petersburg and commits a random murder of a a pawnbroker women that no only loves, nor will mourn. The desperate former student murders without remorse or regret and imagines himself to be a great man. Crime and Punishment is commonly seen as one of the greatest novels ever written and is a powerful psychological thriller with added philosophical conversation, and religious and social commentary.', 1866),
('The Adventures of Huckleberry Finn', 'Mark Twain', 'Adventure', 'The Adventures of Huckleberry Finn was published in the United Kingdom in December 1884, by American Mark Twain. The story begins in fictional St. Petersburg, Missouri, and with a nineteenth-century boy from a River town. Throughout the book he recounts his adventures as he travels down the Mississippi river with another boy who is a runaway slave. Adventures of Huckleberry Finn explores themes of race and identity and demand for the book was immediate and spread throughout the United States and further abroad into Canada and the United Kingdom.', 1885),
('All Quiet on the Western Front', 'Erich Maria Remarque', 'War', 'All Quiet on the Western Front was written by a German veteran of World War I called Erich Maria Remarque in 1928. The book follows an incredible story of a young unknown soldier that patriotically signed up to the ''glorious war'', and his experience pre, present and post war. This story is a powerful insight into the effects that modern warfare has on the human psyche and a must-read for not only the power of the story, but also, for offering the reader a glimpse into the horror of life in the trenches.', 1928),
('Pride and Prejudice', 'Jane Austen', 'Romance', 'Pride and Prejudice was written by Jane Austen in 1813. Mainly falling into the romance novel category, the book follows the character Elizabeth Bennet, and her four sisters, and touches on manners, education, marriage, and money during the Regency era in Great Britain. Back then the importance of marrying for love rather than money or social prestige, wasn''t necessarily valued, which leads to an insightful look into cultural expectations.', 1813),
('The Very Hungry Caterpillar', 'Eric Carle', 'Children''s', 'The Very Hungry Caterpillar is a 1969 children''s picture book designed, illustrated, and written by American children''s author and illustrator Eric Carle. The plot follows a very hungry caterpillar that consumes a variety of foods before pupating and becoming a butterfly. It incorporates elements that contribute to early childhood education, including counting, days of the week, and food. It also incorporates a butterfly''s life cycle.', 1969),
('Nineteen Eighty Four', 'George Orwell', 'Dystopian', 'Set in a dystopian future, the novel presents a society under the total control of a totalitarian regime, led by the omnipresent Big Brother. The protagonist, a low-ranking member of ''the Party'', begins to question the regime and falls in love with a woman, an act of rebellion in a world where independent thought, dissent, and love are prohibited. The novel explores themes of surveillance, censorship, and the manipulation of truth.', 1949);

INSERT INTO Member (first_name, last_name, email)
VALUES
('Michael', 'Michaelson', 'michaelmic@gmail.com'),
('Chris', 'Thompson', 'cthomp@gmail.com'),
('Dave', 'Barr', 'DBarr@yahoo.com'),
('Max', 'Waller', 'maxywally@gmail.com'),
('Jeff', 'Tomas', 'tomas.j@gmail.com'),
('Andrea', 'Castelione', 'castellyA@gmail.com'),
('Felix', 'Ramirez', 'RamiFeli@yahoo.com'),
('Alexa', 'Finke', 'xafinke@yahoo.com'),
('Jeffrey', 'Baxx', 'jeffybaxx@gmail.com'),
('Paul', 'Gutierrez', 'paulgutierrez@gmail.com'),
('Wendy', 'Chen', 'wendychen242@gmail.com'),
('Walter', 'White', 'heisenberg111@gmail.com'),
('Francine', 'Molea', 'francimolea@yahoo.com'),
('Jessabelle', 'Mortensen', 'jessy5554@gmail.com'),
('Ben', 'Kalin', 'BennyKalin@gmail.com');

INSERT INTO Rental (member, book)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);
