package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.MemberEntity;

public interface MemberDao extends CrudDao<MemberEntity, String> {
    boolean create(MemberEntity entity)throws Exception;

    boolean delete(String memberID)throws Exception;

    MemberEntity get(String memberID)throws Exception;

    boolean update(MemberEntity entity)throws Exception;

    ArrayList<MemberEntity> getAll()throws Exception;
}
