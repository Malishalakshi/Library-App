package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import dao.CrudUtil;
import dao.custom.BookDao;
import entity.BookEntity;




public class BookDaoImpl implements BookDao {

    @Override
    public boolean create(BookEntity entity) throws Exception {
         return CrudUtil.executeUpdate("INSERT INTO books VALUES(?,?,?,?,?,?,?)",
          entity.getBookID(),
          entity.getTitle(),
          entity.getAuthor(),
          entity.getCtgId(),
          entity.getCopies(),
          entity.getPublisher(),
          entity.getIsbn());      
    }

    @Override
    public boolean delete(String bookID) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM books WHERE book_id = ?",bookID);
    }

    @Override
    public BookEntity get(String bookID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM books WHERE book_id = ?", bookID);
        if (rst.next()) {
           
           BookEntity entity = new BookEntity(
            rst.getString("book_id"), 
            rst.getString("title"),
            rst.getString("author"), 
            rst.getString("category_id"), 
            rst.getInt("copies_available"), 
            rst.getString("publisher"),
            rst.getString("ISBN")); 
            
        
           return entity;
        }
            return null;
    }

    @Override
    public boolean update(BookEntity entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE books SET title =?, author =?,category_id =?,copies_available =?,publisher =?,ISBN =? WHERE book_id = ?", 
        entity.getTitle(),
        entity.getAuthor(),
        entity.getCtgId(),
        entity.getCopies(),
        entity.getPublisher(),
        entity.getIsbn(),        
        entity.getBookID());
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  books");
            while (rst.next()) {            
                BookEntity entity = new BookEntity(
            rst.getString("book_id"), 
            rst.getString("title"),
            rst.getString("author"), 
            rst.getString("category_id"), 
            rst.getInt("copies_available"), 
            rst.getString("publisher"),
            rst.getString("ISBN")); 
                bookEntities.add(entity);
            }
            
            return bookEntities;
        
    }
    
}
