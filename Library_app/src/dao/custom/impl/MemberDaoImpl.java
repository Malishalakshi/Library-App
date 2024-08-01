package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.MemberDao;
import entity.MemberEntity;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean create(MemberEntity entity) throws Exception {
         return CrudUtil.executeUpdate("INSERT INTO members VALUES(?,?,?,?,?,?)",
          entity.getMemberID(),
          entity.getMemberName(),
          entity.getDob(),
          entity.getAdrress(),
          entity.getContact(),
          entity.getEmail());      
    }

    @Override
    public boolean delete(String memberID) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM members WHERE member_id = ?",memberID);
    }

    @Override
    public MemberEntity get(String memberID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM members WHERE member_id = ?", memberID);
        if (rst.next()) {
           
           MemberEntity entity = new MemberEntity(
            rst.getString("member_id"), 
            rst.getString("name"),
            rst.getString("DOB"), 
            rst.getString("email"), 
            rst.getString("contact_number"),
            rst.getString("address")); 
            
        
           return entity;
        }
            return null;
    }
    @Override
    public boolean update(MemberEntity entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE members SET name =?, DOB =?,email =?,contact_number =?,address =? WHERE member_id = ?", 
        entity.getMemberName(),
        entity.getDob(),
        entity.getEmail(),
        entity.getContact(),
        entity.getAdrress(),
        entity.getMemberID());
    }

    @Override
    public ArrayList<MemberEntity> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = new ArrayList<>();
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  members");
            while (rst.next()) {            
                MemberEntity entity = new MemberEntity(
                rst.getString("member_id"), 
                rst.getString("name"),
                rst.getString("DOB"), 
                rst.getString("email"), 
                rst.getString("contact_number"), 
                rst.getString("address")); 
                memberEntities.add(entity);
            }
            
            return memberEntities;
        
    }


}
