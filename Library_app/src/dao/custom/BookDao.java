package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.BookEntity;

public interface BookDao extends CrudDao<BookEntity, String> {

    boolean create(BookEntity entity)throws Exception;

    boolean delete(String bookID)throws Exception;

    BookEntity get(String bookID)throws Exception;

    boolean update(BookEntity entity)throws Exception;

    ArrayList<BookEntity> getAll()throws Exception;
    
}
