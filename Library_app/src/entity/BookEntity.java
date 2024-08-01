package entity;

public class BookEntity {
    private String bookID;
    private String title;
    private String author;
    private String ctgId;
    private Integer copies;
    private String publisher;
    private String isbn;
    public BookEntity() {
    }
    public BookEntity(String bookID, String title, String author, String ctgId, Integer copies, String publisher,
            String isbn) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.ctgId = ctgId;
        this.copies = copies;
        this.publisher = publisher;
        this.isbn = isbn;
    }
    public String getBookID() {
        return bookID;
    }
    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCtgId() {
        return ctgId;
    }
    public void setCtgId(String ctgId) {
        this.ctgId = ctgId;
    }
    public Integer getCopies() {
        return copies;
    }
    public void setCopies(Integer copies) {
        this.copies = copies;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    @Override
    public String toString() {
        return "BookEntity [bookID=" + bookID + ", title=" + title + ", author=" + author + ", ctgId=" + ctgId
                + ", copies=" + copies + ", publisher=" + publisher + ", isbn=" + isbn + "]";
    }

    
}
