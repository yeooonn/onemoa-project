package com.bitcamp.onemoaproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.PortfolioService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import com.bitcamp.onemoaproject.vo.portfolio.PortfolioAttachedFile;

@Controller
@RequestMapping("mypage")
public class MypageMemberController {

  ServletContext sc;
  MemberService memberService;
  PortfolioService portfolioService;

  public MypageMemberController(MemberService memberService, ServletContext sc, PortfolioService portfolioService) {
    System.out.println("MemberController() 호출됨!");
    this.memberService = memberService;
    this.sc = sc;
    this.portfolioService = portfolioService;
  }

  @GetMapping("changepw")
  public void changepw(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
  }

  @PostMapping("checkpassword")
  public String checkpassword(String password, String newPassword, 
      String newPasswordConfirm, HttpSession session) throws Exception {

    // 1. 현재 비밀번호 맞는지 체크
    Member loginMember = (Member) session.getAttribute("loginMember");
    String email = loginMember.getEmail();
    Member member = memberService.get(loginMember.getNo());

    // boolean isPasswdRight = BCrypt.checkpw(password, member.getPassword());

    // System.out.println(isPasswdRight);

    //    if(!password.equals(member.getPassword())) { // 현제 비밀번호가 일치하기않으면
    //      throw new Exception("현제 패스워드가 일치하지 않습니다.");
    //    }

    // 2. 새 비밀번호, 새비밀번호 확인 맞는지 체크
    if (newPassword.equals(newPasswordConfirm) == false) { // 새 비밀번호와 새 비밀번호 확인이 일치하기않으면
      throw new Exception("새 비밀번호와 새 비밀번호 확인이 서로 일치하지 않습니다.");
    }

    // 3. DB 비밀번호 변경
    memberService.modifyPasswd(email, newPassword);

    return "redirect:changepwResult"; 

  }

  @GetMapping("changepwResult")
  public void changepwResult(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    session.invalidate(); // 현재 세션을 무효화시킨다.
  }

  @GetMapping("myinfoResult")
  public void myinfoResult(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
  }

  @GetMapping("leave")
  public void leave(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
  }

  @GetMapping("leaveHere")
  public String leaveHere(HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    memberService.updateStatus(loginMember.getEmail());

    return "redirect:leaveResult";
  }

  @GetMapping("leaveResult")
  public void leaveResult(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    session.invalidate(); // 현재 세션을 무효화시킨다.
  }



  @GetMapping("myinfo")
  public void myinfo(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.getAttribute("member");
    System.out.println(model.getAttribute("member"));

  }

  @PostMapping("myinfoUpdate")
  public String myinfoUpdate(Member member, MultipartFile files, HttpSession session) throws Exception {

    System.out.println(files.getSize());
    member.setProfile(saveProfile(files));
    System.out.println(member.getProfile());

    if (files.getSize() != 0) {
      memberService.myinfoUpdate(member);
      System.out.println(memberService.myinfoUpdate(member));
    }

    if (!memberService.myinfoUpdate2(member)) {
      System.out.println(memberService.myinfoUpdate2(member));
      throw new Exception("회원 변경 오류입니다!");
    }

    return "redirect:myinfoResult";
  }

  private String saveProfile(MultipartFile files)
      throws IOException, ServletException {
    String dirPath = sc.getRealPath("/member/files");

    if(files.getSize() != 0) {
      String filename = UUID.randomUUID().toString();
      files.transferTo(new File(dirPath + "/" + filename));

      return filename;
    }
    return null;
  }

  // mypage portfolio 

  @GetMapping("portfolioForm")
  public void form(HttpSession session, Model model) throws Exception{
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
  }

  @GetMapping("portfolioList")
  public String list(Model model, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("portfolios", portfolioService.list(loginMember.getNo()));
    model.addAttribute("member", memberService.get(loginMember.getNo()));

    return "mypage/portfolioList";
  }

  @PostMapping("portfolioAdd") 
  public String add(
      Portfolio portfolio,
      MultipartFile[] files,
      HttpSession session) throws Exception {

    portfolio.setAttachedFiles(saveAttachedFiles(files));
    portfolio.setMember((Member) session.getAttribute("loginMember"));

    portfolioService.add(portfolio);
    System.out.println("인서트 성공!");
    return "redirect:portfolioList";
  }

  @GetMapping("portfolioDetail")
  public Map portfolioDetail(int ptNo, HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    Portfolio portfolio = portfolioService.get(ptNo);
    if (portfolio == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    Map map = new HashMap();
    map.put("portfolio", portfolio);
    //    map.get("portfolio");
    //    System.out.println(map.get("portfolio"));
    return map;
  }

  @GetMapping("firstportfolio")
  public void firstportfolio(Model model, int ptNo) throws Exception {
    System.out.println("ptNo = " + ptNo);
    // 포트폴리오 번호로 조회한 포트폴리오
    List<Portfolio> portfolio = portfolioService.getPortfolio(ptNo);
    System.out.println("portfolio = " + portfolio);
    model.addAttribute("portfolio", portfolio);
    // 포트폴리오 번호로 조회한 포트폴리오에서 사용자 번호로
    // 해당 사용자의 모든 포트폴리오 리스트 가져오기
    model.addAttribute("portfolios", portfolioService.list(portfolio.get(0).getMno()));
    //    Member loginMember = (Member) session.getAttribute("loginMember");
    //    model.addAttribute("portfolios", portfolioService.list(loginMember.getNo()));
    //    Portfolio portfolio = portfolioService.get(ptNo);
    //
    //    if (portfolio == null) {
    //      throw new Exception("해당 번호의 게시글이 없습니다!");
    //    }
    //
    //    Map map = new HashMap();
    //    map.put("portfolio", portfolio);
    //    //    map.get("portfolio");
    //    //    System.out.println(map.get("portfolio"));
    //    return map;
  }


  @PostMapping("portfolioUpdate")
  public String update(
      Portfolio portfolio,
      MultipartFile[] files,
      HttpSession session) 
          throws Exception {

    portfolio.setAttachedFiles(saveAttachedFiles(files));
    // checkOwner(contest.getNo(), session);



    System.out.println("portfolio: "+ portfolio);

    if (!portfolioService.update(portfolio)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:portfolioList";
  }

  @GetMapping("portfolioDelete")
  public String delete(
      int ptNo, 
      HttpSession session) 
          throws Exception {

    // checkOwner(no, session);
    if (!portfolioService.delete(ptNo)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:portfolioList";
  }

  private List<PortfolioAttachedFile> saveAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<PortfolioAttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/portfolio/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      String filepath = UUID.randomUUID().toString();
      String filename = part.getOriginalFilename();
      part.transferTo(new File(dirPath + "/" + filepath));
      attachedFiles.add(new PortfolioAttachedFile(filename, filepath));

    }
    return attachedFiles;
  }

  @GetMapping("fileDelete")
  public String fileDelete(
      int ptfNo,
      HttpSession session) 
          throws Exception {

    PortfolioAttachedFile attachedFile = portfolioService.getAttachedFile(ptfNo); 

    // Member loginMember = (Member) session.getAttribute("loginMember");
    Portfolio portfolio = portfolioService.get(attachedFile.getPtNo()); 

    //    if (contest.getWriter().getNo() != loginMember.getNo()) {
    //      throw new Exception("게시글 작성자가 아닙니다.");
    //    }

    if (!portfolioService.deleteAttachedFile(ptfNo)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }

    return "redirect:portfolioDetail?ptNo=" + portfolio.getPtNo();
  }

}






























