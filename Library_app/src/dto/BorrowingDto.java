package dto;


import java.time.LocalDate;

public class BorrowingDto {
    private String borrowingId;
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    private Double fine;

    public BorrowingDto() {
    }

    public BorrowingDto(String borrowingId, String bookId, String memberId, LocalDate borrowDate, LocalDate dueDate,
            LocalDate returnedDate, Double fine) {
        this.borrowingId = borrowingId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
        this.fine = fine;
    }

    public String getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingID(String borrowingId) {
        this.borrowingId = borrowingId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "BorrowingDto [borrowingId=" + borrowingId + ", bookId=" + bookId + ", memberId=" + memberId
                + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + ", returnedDate=" + returnedDate + ", fine="
                + fine + "]";
    }
    
    
    
       
}
