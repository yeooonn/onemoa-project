package com.bitcamp.onemoaproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.onemoaproject.dao.MemberDao;
import com.bitcamp.onemoaproject.vo.Member;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  //  @Autowired
  //  BoardDao boardDao;


  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
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

  //  @Transactional
  //  @Override
  //  public boolean delete(int no) throws Exception {
  //    boardDao.deleteFilesByMemberBoards(no); // 회원이 작성한 게시글의 모든 첨부파일 삭제
  //    boardDao.deleteByMember(no); // 회원이 작성한 게시글 삭제
  //    return memberDao.delete(no) > 0; // 회원 삭제
  //  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }
}


