package service.custom.impl;



import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.CategoryDao;
import dto.CategoryDto;
import entity.CategoryEntity;
import service.custom.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);

    @Override
    public String create(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getcategoryEntity(categoryDto);
        return categoryDao.create(entity) ? "Success" : "Fail";
        
    }

    

    @Override
    public String delete(String ctgID) throws Exception {
        return categoryDao.delete(ctgID) ? "Success" : "Fail"; 
    }

    @Override
    public CategoryDto get(String ctgID) throws Exception {
        CategoryEntity entity = categoryDao.get(ctgID);
        if(entity != null){
            return getCategoryDto(entity);
        }
        return null;
    }

    private CategoryEntity getcategoryEntity(CategoryDto categoryDto) {
        return  new CategoryEntity(categoryDto.getCtgID(),categoryDto.getCtgName());
    }

    private CategoryDto getCategoryDto(CategoryEntity entity) {
        return  new CategoryDto(entity.getCtgID(),entity.getCtgName());
            
    }



    @Override
    public String update(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getcategoryEntity(categoryDto);
        return categoryDao.update(entity) ? "Success" : "Fail";
        
    }



    @Override
    public ArrayList<CategoryDto> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = categoryDao.getAll();
        if(categoryEntities != null && !categoryEntities.isEmpty()){
            ArrayList<CategoryDto> categoryDtos = new ArrayList<>();
            
            for (CategoryEntity categoryEntity : categoryEntities) {
                categoryDtos.add(getCategoryDto(categoryEntity));
            }
            
            return categoryDtos;
        }
        return null;
    }

   

    
    
}
