package com.bitcamp.onemoaproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bitcamp.onemoaproject.vo.Interest;
import com.bitcamp.onemoaproject.vo.Member;

@Mapper
public interface MemberDao {

  int insert(Member member);

  Member findByNo(int no);

  int update(Member member);

  int updateStatus(String email);

  int myinfoUpdate(Member member);

  int myinfoUpdate2(Member member);

  int delete(int no);

  List<Member> findAll();

  //  Member findByEmailPassword(String email, String password);
  // 메서드의 파라미터가 여러 개일 때,
  // - SQL에서 참조할 파라미터라고 애노테이션으로 표시해야한다.
  Member findByEmailPassword(@Param("email") String email, @Param("password") String password);

  // 이메일로 쿼리 조회
  Member findByEmail(String email);

  // 닉네임으로 쿼리 조회
  Member findByNickName(String nickname);

  // 회원 포트폴리오 목록 조회
  List<Member> findByMemberPortfolio(int fieldMemberNo);

  int modifyPasswd(
      @Param("email") String email, 
      @Param("newPassword") String newPassword);

  // 사용자 패스워드 체크
  int passwordCheck(@Param("password") String password, @Param("mno") int mno);

  int addInterest(Interest interest);

  int deleteInterest(int no);
  
  // test
  List<Member> findAllTest();
  
  Member findByIdEmail(@Param("name") String name, @Param("email") String email);
  
  int updatePwd(@Param("name") String name, @Param("email") String email,
      @Param("password") String password);
}















