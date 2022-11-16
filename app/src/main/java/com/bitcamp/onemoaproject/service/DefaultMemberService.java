package com.bitcamp.onemoaproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.onemoaproject.dao.ContestDao;
import com.bitcamp.onemoaproject.dao.MemberDao;
import com.bitcamp.onemoaproject.vo.Member;

@Service 
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Autowired
  ContestDao contestDao;


  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }

  @Override
  public int updateStatus(String email) throws Exception {
    return memberDao.updateStatus(email);
  }

  @Override
  public boolean myinfoUpdate(Member member) throws Exception {
    return memberDao.myinfoUpdate(member) > 0;
  }

  @Override
  public boolean myinfoUpdate2(Member member) throws Exception {
    return memberDao.myinfoUpdate2(member) > 0;
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
  public boolean delete(int no) throws Exception {
    return false;
  }

  //  @Transactional
  //  @Override
  //  public boolean delete(int no) throws Exception {
  //    contestDao.deleteFilesByMemberContests(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
  //    contestDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
  //    return memberDao.delete(no) > 0; // 회원 삭제
  //  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  // 팀원모집분야 지원자 리스트
  @Override
  public List<Member> getFieldMemberPortfolio(int fieldMemberNo) throws Exception {
    return memberDao.findByMemberPortfolio(fieldMemberNo);
  }

  @Override
  public void modifyPasswd(String email, String newPassword) {
    memberDao.modifyPasswd(email, newPassword);
  }

  @Override
  public Member getNickName(String nickname) {
    return null;
  }

  @Override
  public Member getIdEmail(String name, String email) {
    return null;
  }

  @Override
  public boolean updatePwd(Member member) {
    return false;
  }
  
  // 사용자 패스워드 체크
  
  @Override
  public int getPasswordCheck(String password, int mno) {
    return memberDao.passwordCheck(password, mno);
  }
}








