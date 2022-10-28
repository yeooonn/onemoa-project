package com.bitcamp.onemoaproject.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Member;

@Controller
@RequestMapping("/member/")
//- 애노테이션을 붙일 때 객체 이름을 명시하면 그 이름으로 저장한다.
//- 프론트 컨트롤러는 페이지 컨트로러를 찾을 때 이 이름으로 찾을 것이다.
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    System.out.println("MemberController() 호출됨!");
    System.out.println("memberService.toString()" + memberService.toString());
    this.memberService = memberService;
  }

  @GetMapping("form")
  public void form() throws Exception {}

  @PostMapping("add")
  public String add(Member member) throws Exception {
    memberService.add(member);
    System.out.println(member);
    return "redirect:../";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    // 프론트 컨트롤러가 건네준 Model 객체에 작업 결과를 담아 두면
    // 핸들러 호출이 끝났을 때 JSP를 실행하기 전에
    // 먼저 Model 객체에 담아둔 값을 ServletRequest 보관소로 옮긴다.
    model.addAttribute("members", memberService.list());
  }

  @GetMapping("detail")
  public void detail(int no, Map map) throws Exception {

    Member member = memberService.get(no);

    if (member == null) {
      throw new Exception("사용자 조회 실패!");
    }

    map.put("member", member);
  }

  @PostMapping("update")
  public String update(Member member) throws Exception {

    if (!memberService.update(member)) {
      throw new Exception("사용자 변경 실패!");
    }

    return "redirect:list";
  }

  //  @GetMapping("delete")
  //  public String delete(int no) throws Exception {
  //    if (!memberService.delete(no)) {
  //      throw new Exception("회원 삭제 오류입니다!");
  //    }
  //    
  //    return "redirect:list";
  //  }
}
