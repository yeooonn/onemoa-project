package com.bitcamp.onemoaproject.service;

import java.util.List;
import com.bitcamp.onemoaproject.vo.AdminMember;
import com.bitcamp.onemoaproject.vo.Member;

public interface AdminMemberService {

  AdminMember get(String email, String password) throws Exception;

  List<AdminMember> list() throws Exception;

  List<Member> memberList() throws Exception;
}
