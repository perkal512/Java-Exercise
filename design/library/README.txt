# Library REST API Design

## Endpoints

### Books

1. GET api/books
Retrieve all books.
   
2. GET api/books/{id}
Retrieve a single book by ID.

3. POST api/books
Create a new book.
  
4. PUT api/books/{id}
Update an existing book

5. DELETE api/books/{id}
Delete a book.

### Users

6. GET api/users
Retrieve all users

7. POST api/users
Register a new user

8. PUT api/users/{id}
Update user information by ID.

9. DELETE api/users/{id}
Delete a user

### Borrow / Return

10. POST api/borrow
Borrow a book

11. POST api/return
Return a borrowed book

12.GET /api/borrowed
List all active borrow records

## JSON Examples

1. Create Book

{
  "title": "Clean Code",
  "author": "Yona Sapir",
  "totalCopies": 5,
  "availableCopies": 5
}

2. Create User
{
  "name": "Michal Segal",
  "email": "michal@example.com"
}

3. Borrow a Book
{
  "userId": 12,
  "bookId": 45
}

4. Loan Response
{
  "loanId": 1001,
  "userId": 12,
  "bookId": 45,
  "borrowDate": "2025-12-01",
  "dueDate": "2025-12-10",
  "returnDate": null,
  "status": "ACTIVE"
}

## Operations
1. Books: create, read (list/get by ID), update, delete.

2. Users: create, read (list), update, delete.

3. Borrowing: borrow book, return book, list active loans.

4. Validation: check available copies before borrowing.

## Business Rules

- Borrowing and returning books:

	* availableCopies decreases on borrow, increases on return.
	* Borrow is denied if availableCopies == 0.
	* availableCopies cannot exceed totalCopies.
	
- Required fields:
	* Creating or updating books/users must include all required fields.

- IDs:
	* System generates unique IDs (id, loanId) automatically for books, users, and loans.
	