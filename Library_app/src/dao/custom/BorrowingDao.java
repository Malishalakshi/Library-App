package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.BorrowingEntity;

public interface BorrowingDao extends CrudDao<BorrowingEntity, String> {
    boolean create(BorrowingEntity entity)throws Exception;

    boolean delete(String borrowingID)throws Exception;

    BorrowingEntity get(String borrowingID)throws Exception;

    boolean update(BorrowingEntity entity)throws Exception;

    ArrayList<BorrowingEntity> getAll()throws Exception;
}
