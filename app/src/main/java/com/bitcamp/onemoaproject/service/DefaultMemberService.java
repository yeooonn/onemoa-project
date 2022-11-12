package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.dao.MemberDao;
import com.bitcamp.onemoaproject.vo.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 서비스 역할을 수행하는 객체에 붙이는 애노테이션
//- 이 애노테이션을 붙이면 Spring IoC 컨테이너가 객체를 자동으로 생성할 것이다.
//- 생성자에 파라미터가 있다면 해당 타입의 객체를 찾아 생성자를 호출할 때 주입할 것이다.
//- 만약 생성자가 원하는 파라미터 값이 없다면 생성 예외가 발생한다.
public class DefaultMemberService implements MemberService {
  
  @Autowired
  MemberDao memberDao;
  
//  @Autowired
//  BoardDao boardDao;
  
  // 회원 등록
  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }
  
  // 회원 이름과 이메일 검색
  @Override
  public Member getIdEmail(String name, String email) throws Exception {
    return memberDao.findByIdEmail(name, email);
  }
  
  // 회원 패스워드 초기화
  @Override
  public boolean updatePwd(Member member) throws Exception {
    return memberDao.updatePwd(member) > 0;
  }
  
  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }
  
  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }
  
  @Override
  public Member get(String email, String password) throws Exception {
    return memberDao.findByEmailPassword(email, password);
  }
  
  @Override
  public Member get(String email) throws Exception {
    return memberDao.findByEmail(email);
  }
  
  @Override
  public Member getNickName(String nickname) throws Exception {
    return memberDao.findByNickName(nickname);
  }
  
  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
//    boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
//    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
//    return memberDao.delete(no) > 0; // 회원 삭제
    return true;
  }
  
  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }
  
  @Override
  public List<Member> getFieldMemberPortfolio(int fieldMemberNo) throws Exception {
    return memberDao.findByMemberPortfolio(fieldMemberNo);
  }
}