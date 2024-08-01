package service;

import service.custom.impl.BookServiceImpl;
import service.custom.impl.CategoryServiceImpl;
import service.custom.impl.MemberServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    
    private ServiceFactory(){}
    
    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }
    
    public SuperService getService(ServiceType serviceType){
        switch (serviceType) {
            case CATEGORY:
                return new CategoryServiceImpl();
            case BOOK:
                return new BookServiceImpl();
            case MEMBER:
                return new MemberServiceImpl();
            // case BORROWINGS:
            //     return new BorrowingServiceImpl();
            default:
                return null;
        }
        
    }
    
    public enum ServiceType{
        CATEGORY,BOOK,MEMBER,BORROWINGS;
    }
}
