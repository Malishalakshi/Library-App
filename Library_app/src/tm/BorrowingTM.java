package tm;

public class BorrowingTM {
    private String borrowID;
    private String memberID;
    private String bookID;
    private String borrowDate;
    private String dueDate;
    private String rtnDate;
    private Double fine;
    public BorrowingTM() {
    }
    public BorrowingTM(String borrowID, String memberID, String bookID, String borrowDate, String dueDate,
            String rtnDate, Double fine) {
        this.borrowID = borrowID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.rtnDate = rtnDate;
        this.fine = fine;
    }
    public String getBorrowID() {
        return borrowID;
    }
    public void setBorrowID(String borrowID) {
        this.borrowID = borrowID;
    }
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getRtnDate() {
        return rtnDate;
    }
    public void setRtnDate(String rtnDate) {
        this.rtnDate = rtnDate;
    }
    public Double getFine() {
        return fine;
    }
    public void setFine(Double fine) {
        this.fine = fine;
    }
    @Override
    public String toString() {
        return "BorrowingTM [borrowID=" + borrowID + ", memberID=" + memberID + ", bookID=" + bookID + ", borrowDate="
                + borrowDate + ", dueDate=" + dueDate + ", rtnDate=" + rtnDate + ", fine=" + fine + "]";
    }
    
    

}