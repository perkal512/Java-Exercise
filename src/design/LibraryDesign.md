# מערכת ספרייה – תכנון REST API

---

## 1. Endpoints

###  Books
- `GET /api/books` – קבלת רשימת כל הספרים  
- `GET /api/books/{id}` – קבלת ספר לפי ID  
- `POST /api/books` – הוספת ספר חדש  
- `PUT /api/books/{id}` – עדכון ספר  
- `DELETE /api/books/{id}` – מחיקת ספר  
- `POST /api/books/{id}/borrow?memberId={mid}` – השאלת ספר  לפי חבר  
- `POST /api/books/{id}/return` – החזרת ספר   

---

### Members
- `GET /api/members` – רשימת כל הקוראים  
- `GET /api/members/{id}` – קבלת קורא לפי ID  
- `POST /api/members` – הוספת קורא חדש  
- `PUT /api/members/{id}` – עדכון קורא  
- `DELETE /api/members/{id}` – מחיקת קורא  

---

### Loans
- `GET /api/loans` – רשימת כל ההשאלות הפעילות  
- `GET /api/loans/{id}` – קבלת השאלה לפי ID  
- `POST /api/loans` – יצירת השאלה 
- `PUT /api/loans/{id}/return` – סימון השאלה כמוחזרת  
- `DELETE /api/loans/{id}` – מחיקת השאלה (מנהלים בלבד)  

---

## 2. Operations / Business Logic

###  Books
1. יצירת ספר חדש  
2. שליפת ספר לפי ID  
3. הצגת כל הספרים  
4. עדכון פרטי ספר  
5. מחיקת ספר  
6. בדיקת זמינות עותקים  
7. השאלת ספר (אם זמין)  
8. החזרת ספר  
9. עדכון מספר עותקים או סטטוס הזמינות  

---

### Members
1. רישום קורא חדש  
2. שליפת פרטי קורא  
3. הצגת כל הקוראים  
4. בדיקה אם הקורא יכול לשאול (לפי חוקי השאלה)  
5. עדכון פרטי קורא  
6. מחיקת קורא  

---

###  Loans
1. יצירת  השאלה   
2. שליפת השאלה לפי ID  
3. הצגת כל ההשאלות הפעילות  
4. סימון השאלה כמוחזרת  
5. בדיקה של כללי השאלה:  
   - הספר קיים  
   - יש עותקים זמינים  
   - החבר קיים  
   - לא חרג ממספר ההשאלות המותר  

---

## 3. DTOs – מבני נתונים

### BookDTO

```java
public class BookDTO {
    private Long id;		
    private String title;
    private String author;
    private int year;
    private int totalCopies;
    private int availableCopies; 
}

public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
}


public class LoanDTO {
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
```
## 3. שדות חובה

### Books
- title - חובה
- author - חובה
- year - חובה
- totalCopies - חובה
- availableCopies - לא חובה, המערכת מחשבת אוטומטית לפי השאלות

### Members
- name - חובה
- email - חובה
- phone - אופציונלי

### Loans
- bookId - חובה
- memberId - חובה
- borrowDate - חובה
- returnDate - אופציונלי -מתמלא בהחזרה
