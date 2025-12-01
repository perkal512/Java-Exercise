# Library REST API Design

## Endpoints

### Books

1. GET /books
קבלת רשימת הספרים
   
2. GET /books/{id}
קבלת ספר לפי ID

3. POST /books
הוספת ספר חדש
  
4. PUT /books/{id}
עדכון נתוני ספר קיים לפי ID

5. DELETE /books/{id}
מחיקת ספר מהמאגר

### Users

6. GET /users
קבלת כל המשאילים - משתמשים
   
7. GET /users/{id}
קבלת משתמש מסוים לפי ID

8. POST /users{id}
יצירת משתמש - משאיל חדש במערכת

### Borrow / Return

8. POST /borrow
השאלת ספר

9. POST /return
החזרת ספר  