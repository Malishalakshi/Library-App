package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BorrowingDao;
import entity.BorrowingEntity;

public class BorrowingDaoImpl implements BorrowingDao {
    @Override
    public boolean create(BorrowingEntity entity) throws Exception {
         return CrudUtil.executeUpdate("INSERT INTO borrowing VALUES(?,?,?,?,?,?,?)",
          entity.getBorrowDate(),
          entity.getBookId(),
          entity.getMemberId(),
          entity.getBorrowDate(),
          entity.getDueDate(),
          entity.getReturnedDate(),
          entity.getFine());      
    }

    @Override
    public boolean delete(String borrowinID) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM  borrowing WHERE borrowing_id= ?",borrowinID);
    }

    @Override
    public BorrowingEntity get(String borrowID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM borrowing WHERE borrowing_id = ?", borrowID);
        if (rst.next()) {

           
            BorrowingEntity entity = new BorrowingEntity(
            rst.getString("borrowing_id"), 
            rst.getString("book_id"),
            rst.getString("member_id"), 
            rst.getDate("borrow_date").toLocalDate(), 
            rst.getDate("due_date").toLocalDate(),
            rst.getDate("returned_date") != null ? rst.getDate("returned_date").toLocalDate() : null,
            rst.getDouble("fine")); 
            
        
           return entity;
        }
            return null;
    }

    @Override
    public boolean update(BorrowingEntity entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE borrowing SET book_id  =?, member_id =?,borrow_date =?,due_date  =?,returned_date =? fine =? WHERE borrowing_id = ?", 
        entity.getBookId(),
        entity.getMemberId(),
        entity.getBorrowDate(),
        entity.getDueDate(),
        entity.getReturnedDate(),
        entity.getFine(),        
        entity.getBorrowingId());
    }

    @Override
    public ArrayList<BorrowingEntity> getAll() throws Exception {
        ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<>();
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  borrowing");
            while (rst.next()) {            
                    BorrowingEntity entity = new BorrowingEntity(
                        rst.getString("borrowing_id"), 
                        rst.getString("book_id"),
                        rst.getString("member_id"), 
                        rst.getDate("borrow_date").toLocalDate(), 
                        rst.getDate("due_date").toLocalDate(),
                        rst.getDate("returned_date") != null ? rst.getDate("returned_date").toLocalDate() : null,
                        rst.getDouble("fine")); 
                        
                borrowingEntities.add(entity);
            }
            
            return borrowingEntities;
        
    }
    
}
