package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.CategoryEntity;

public interface CategoryDao extends CrudDao<CategoryEntity, String>{

    boolean create(CategoryEntity entity)throws Exception;
    boolean delete(String ctgID)throws Exception;
    CategoryEntity get(String ctgID)throws Exception;
    boolean update(CategoryEntity entity)throws Exception;
    ArrayList<CategoryEntity> getAll()throws Exception;

     
    
    
    
    
}
