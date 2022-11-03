package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.AdminMember;
import com.bitcamp.onemoaproject.vo.Member;
import java.util.List;

public interface AdminMemberService {
  List<AdminMember> list() throws Exception;
  
  List<Member> memberList() throws Exception;
}
