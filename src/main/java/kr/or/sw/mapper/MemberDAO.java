package kr.or.sw.mapper;

import kr.or.sw.model.MemberDTO;

import java.util.List;

public interface MemberDAO {

    List<MemberDTO> selectAllMembers();

    List<MemberDTO> selectMemberById(int memberID);

    List<MemberDTO> selectMemberByName(String name);

    List<MemberDTO> selectMemberByEmail(String email);

    List<MemberDTO> selectMemberByContact(String contact);

    MemberDTO selectMember(int memberID);

    int updateMember(MemberDTO memberDTO);

    int deleteMember(int memberID);

    int updateMemberSelf(MemberDTO memberDTO);

    int updateMemberSelfWOPassword(MemberDTO memberDTO);
}
