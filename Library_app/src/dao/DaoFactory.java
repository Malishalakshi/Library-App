package dao;

import dao.custom.impl.BookDaoImpl;
import dao.custom.impl.BorrowingDaoImpl;
import dao.custom.impl.CategoryDaoImpl;
import dao.custom.impl.MemberDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case CATEGORY:
                return new CategoryDaoImpl();
            case BOOK:
              return new BookDaoImpl();
            case MEMBER:
                 return new MemberDaoImpl();
            case BORROWINGS:
                 return new BorrowingDaoImpl();
            
            default:
                return null;
        }
    }

    public enum DaoTypes {
        CATEGORY,BOOK,MEMBER,BORROWINGS;
    }
}
