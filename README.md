## Docker postgresql container

```bash
docker run --name innowise -e POSTGRES_USER=innowise -e POSTGRES_PASSWORD=innowise -e POSTGRES_DB=innowise -p 5432:5432 -d postgres
```

### Create tables

```sql
CREATE TABLE Authors (
    author_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birthdate DATE
);

CREATE TABLE Books (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    publication_date DATE,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES Authors(author_id) ON DELETE CASCADE
);

CREATE TABLE Orders (
    order_id SERIAL PRIMARY KEY, 
    book_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    order_date DATE NOT NULL,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Books(book_id) ON DELETE CASCADE
);
```

### Data to test on

```sql
INSERT INTO Authors (author_id, name, birthdate) VALUES
(1, 'Author1', '1965-07-31'),
(2, 'Author2', '1996-09-20'),
(3, 'Author2', '1824-01-03'),
(4, 'Author3', '1947-09-21');

INSERT INTO Books (book_id, title, author_id, price, publication_date) VALUES
(1, 'Book1', 1, 19.99, '1997-06-26'),
(2, 'Book2', 2, 22.50, '1996-08-06'),
(3, 'Book3', 3, 25.00, '2025-01-21'),
(4, 'Book4', 4, 15.99, '2024-01-28'),
(5, 'Book5', 1, 29.99, '2007-07-21'),
(6, 'Book6', 3, 30.00, '2021-11-20');

INSERT INTO Orders (order_id, book_id, quantity, order_date) VALUES
(1, 1, 5, '2024-01-15'),
(2, 2, 3, '2024-01-20'),
(3, 5, 8, '2024-01-25'),
(4, 3, 10, '2024-02-10'),
(5, 6, 2, '2024-02-15'),
(7, 4, 4, '2024-03-15'),
(9, 2, 12, '2024-04-10'),
(10, 1, 9, '2024-04-15');
```

### Task #1 List all books with their authors

```sql
SELECT b.title AS book_title, a.name AS author_name
FROM Books b 
JOIN Authors a ON b.author_id = a.author_id;
```

### Task #2 Find books published after January 1, 2020

```sql
SELECT book_id, title, publication_date
FROM Books
WHERE publication_date > '2020-01-01';
```

### Task #3 Count the total number of books sold

```sql
SELECT SUM(o.quantity) AS total_books_sold
FROM Orders o;
```

### Task #4 Display the 3 most expensive books

```sql
SELECT book_id, title, price
FROM Books
ORDER BY price DESC LIMIT 3;
```

### Task #5 Find authors whose books were ordered more than 10 times

```sql
SELECT a.author_id, 
    a.name AS author_name, 
    SUM(o.quantity) AS total_books_ordered
FROM Authors a
JOIN Books b ON a.author_id = b.author_id
JOIN Orders o ON b.book_id = o.book_id
GROUP BY a.author_id, a.name
HAVING SUM(o.quantity) > 10;
```

