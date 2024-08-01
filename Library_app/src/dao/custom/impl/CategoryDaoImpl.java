package dao.custom.impl;



import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.CategoryDao;
import entity.CategoryEntity;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean create(CategoryEntity entity) throws Exception {
        
        return CrudUtil.executeUpdate("INSERT INTO book_categories VALUES(?,?)", entity.getCtgID(), entity.getCtgName());
    }

    @Override
    public boolean delete(String ctgID) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM book_categories WHERE category_id = ?",ctgID);
    }

    @Override
    public CategoryEntity get(String ctgID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM book_categories WHERE category_id = ?", ctgID);
        if (rst.next()) {
            CategoryEntity entity = new CategoryEntity(rst.getString("category_id"), rst.getString("category_name"));
            return entity;
        }
            return null;
    }

    @Override
    public boolean update(CategoryEntity entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE book_categories SET category_name = ? WHERE category_id = ?", 
                entity.getCtgName(),entity.getCtgID());
    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = new ArrayList<>();
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  book_categories");
            while (rst.next()) {            
                CategoryEntity entity = new CategoryEntity(rst.getString("category_id"), 
                        rst.getString("category_name"));
                categoryEntities.add(entity);
            }
            
            return categoryEntities;
    }
    
    
    
}
