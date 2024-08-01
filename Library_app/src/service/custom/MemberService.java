package service.custom;

import java.util.ArrayList;

import dto.MemberDto;
import service.SuperService;

public interface MemberService extends SuperService {
    String create(MemberDto memberDto)throws Exception;

    String delete(String memberID)throws Exception;

    MemberDto get(String memeberID)throws Exception;

    String update(MemberDto memberDto)throws Exception;

    ArrayList<MemberDto> getAll()throws Exception;
}
