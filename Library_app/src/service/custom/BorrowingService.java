package service.custom;

import java.util.ArrayList;

import dto.BookDto;
import dto.BorrowingDto;
import service.SuperService;

public interface BorrowingService extends SuperService {
    String create(BorrowingDto dto)throws Exception;

    String delete(String borrowID)throws Exception;

    BorrowingDto get(String borrowID)throws Exception;

    String update(BorrowingDto borrowDto)throws Exception;

    ArrayList<BorrowingDto> getAll()throws Exception;
}
