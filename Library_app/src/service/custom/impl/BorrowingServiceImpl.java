package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.BorrowingDao;
import dto.BorrowingDto;
import entity.BorrowingEntity;
import service.custom.BorrowingService;

public class BorrowingServiceImpl implements BorrowingService {
    private BorrowingDao borrowingDao = (BorrowingDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWINGS);

    @Override
    public String create(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getEntity(borrowingDto);
        return borrowingDao.create(entity) ? "Success" : "Fail";
    }

    private BorrowingEntity getEntity(BorrowingDto borrowingDto) {
        return  new BorrowingEntity(
            borrowingDto.getBorrowingId(), 
            borrowingDto.getBookId(),
            borrowingDto.getMemberId(), 
            borrowingDto.getBorrowDate(), 
            borrowingDto.getDueDate(), 
            borrowingDto.getReturnedDate(),
            borrowingDto.getFine()); 
            
    }

    @Override
    public String delete(String borrowingID) throws Exception {
        return borrowingDao.delete(borrowingID) ? "Success" : "Fail";
    }

    @Override
    public BorrowingDto get(String borrowingID) throws Exception {
        BorrowingEntity entity = borrowingDao.get(borrowingID);
        if(entity != null){
            return getDto(entity);
        }
        return null;
    }

    private BorrowingDto getDto(BorrowingEntity entity) {
        return  new BorrowingDto(
            entity.getBorrowingId(), 
            entity.getBookId(),
            entity.getMemberId(), 
            entity.getBorrowDate(), 
            entity.getDueDate(), 
            entity.getReturnedDate(),
            entity.getFine()); 
            
    }

    @Override
    public String update(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getEntity(borrowingDto);
        return borrowingDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public ArrayList<BorrowingDto> getAll() throws Exception {
       ArrayList<BorrowingEntity> borrowingEntities = borrowingDao.getAll();
        if(borrowingEntities != null && !borrowingEntities.isEmpty()){
            ArrayList<BorrowingDto> borrowingDtos = new ArrayList<>();
            
            for (BorrowingEntity borrowingEntity : borrowingEntities) {
                borrowingDtos.add(getDto(borrowingEntity));
            }
            
            return borrowingDtos;
        }
        return null;
    }
}
