package library;

public class LoanDTO {
    private Integer loanId;     // Server-generated
    private Integer userId;     // user that loan the book
    private Integer bookId;     // The borrowed book
    private String borrowDate;  // REQUIRED
    private String dueDate;     // REQUIRED
    private String returnDate;  // OPTIONAL
    private String status;      // REQUIRED: NEW, ACTIVE, RETURNED, OVERDUE
}
