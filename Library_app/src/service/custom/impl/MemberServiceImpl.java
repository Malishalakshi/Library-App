package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.MemberDao;
import dto.MemberDto;
import entity.MemberEntity;
import service.custom.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBER);

    @Override
    public String create(MemberDto memberDto) throws Exception {
        MemberEntity entity = getEntity(memberDto);
        return memberDao.create(entity) ? "Success" : "Fail";
    }

    private MemberEntity getEntity(MemberDto memberDto) {
        return  new MemberEntity(
            memberDto.getMemberID(), 
            memberDto.getMemberName(),
            memberDto.getDob(),
            memberDto.getAdrress(), 
            memberDto.getContact(), 
            memberDto.getEmail()); 
            
    }
    @Override
    public String delete(String memberID) throws Exception {
        return memberDao.delete(memberID) ? "Success" : "Fail";
    }
    @Override
    public MemberDto get(String memberID) throws Exception {
        MemberEntity entity = memberDao.get(memberID);
        if(entity != null){
            return getDto(entity);
        }
        return null;
    }
    private MemberDto getDto(MemberEntity entity) {
        return  new MemberDto(
            entity.getMemberID(), 
            entity.getMemberName(),
            entity.getDob(), 
            entity.getAdrress(), 
            entity.getContact(), 
            entity.getEmail()); 
            
    }
    @Override
    public String update(MemberDto memberDto) throws Exception {
        MemberEntity entity = getEntity(memberDto);
        return memberDao.update(entity) ? "Success" : "Fail";
    }
    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
       ArrayList<MemberEntity> memberEntities = memberDao.getAll();
        if(memberEntities != null && !memberEntities.isEmpty()){
            ArrayList<MemberDto> memberDtos = new ArrayList<>();
            
            for (MemberEntity memberEntity : memberEntities) {
                memberDtos.add(getDto(memberEntity));
            }
            
            return memberDtos;
        }
        return null;
    }


    
}
