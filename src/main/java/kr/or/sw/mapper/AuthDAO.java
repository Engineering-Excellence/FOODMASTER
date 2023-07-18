package kr.or.sw.mapper;

import kr.or.sw.model.EmpDTO;
import kr.or.sw.model.MemberDTO;

public interface AuthDAO {

    MemberDTO selectCredentials(String email);

    int checkEmail(String email);

    MemberDTO getQuestion(String email);

    MemberDTO getMemberInfo(String email);

    int insertMember(MemberDTO memberDTO);

    int resetPassword(MemberDTO memberDTO);

    EmpDTO selectAdminCredentials(String account);

    EmpDTO getAdminInfo(String account);
    
    
}
