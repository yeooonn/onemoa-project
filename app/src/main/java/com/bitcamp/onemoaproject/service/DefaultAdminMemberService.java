package com.bitcamp.onemoaproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.onemoaproject.dao.AdminMemberDao;
import com.bitcamp.onemoaproject.dao.MemberDao;
import com.bitcamp.onemoaproject.vo.AdminMember;
import com.bitcamp.onemoaproject.vo.Member;

@Service
public class DefaultAdminMemberService implements AdminMemberService {

  @Autowired
  AdminMemberDao adminMemberDao;

  @Autowired
  MemberDao memberDao;

  @Override
  public AdminMember get(String email, String password) throws Exception {
    return adminMemberDao.findByEmailPassword(email, password);
  }

  @Override
  public List<AdminMember> list() throws Exception {
    return adminMemberDao.findAll();
  }

  @Override
  public List<Member> memberList() throws Exception {
    return memberDao.findAll();
  }
}
