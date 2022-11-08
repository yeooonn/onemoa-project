package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("contest")
public class ContestController {

  @Autowired
  ServletContext sc;
  @Autowired
  ContestService contestService;
  @Autowired
  MemberService memberService;
  
  // 공모전 목록 출력
  @GetMapping("contestTeam")
  public void contestTeamList(Model model, String no, String ono) throws Exception {
    model.addAttribute("contests", contestService.list(no, ono));
  }

  // 공모전 디테일
  @PostMapping("contestTeam/detail")
  @ResponseBody
  public Contest contestTeamDetail(int contestNumber) throws Exception {
    Contest contest = contestService.get(contestNumber);
    return contest;
  }
  
  // 공모전 팀원구해요
  @PostMapping("contestTeam/teamList")
  @ResponseBody
  public Object contestTeamTeamList(Model model, int contestNumber) throws Exception {
    model.addAttribute("teams",contestService.getTeamList(contestNumber));
    return model.getAttribute("teams");
  }
  
  // 공모전 팀원 모집하기 폼
  @PostMapping("contestTeam/teamRecruitForm")
  @ResponseBody
  public Member contestTeamTeamRecruit(int contestNumber, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    member = memberService.get(member.getNo());
    System.out.println("member = " + member);
    return member;
  }

  // 공모전 상세정보(관리자 페이지)
  @GetMapping("contestDetail")
  public Contest contestDetail(int ctstNo) throws Exception {
    Contest contest = contestService.get(ctstNo);
    return contest;
  }

  // 공모전 목록(관리자 페이지)
  @GetMapping("contestList")
  public String list(Model model) throws Exception {
    model.addAttribute("contests", contestService.list());
    return "contest/contestList";
  }
  
  // 공모전 글등록 폼(관리자 페이지)
  @GetMapping("contestForm")
  public void form() throws Exception {
    String dirPath2 = sc.getRealPath("/contest/files");
    System.out.println(dirPath2);
  }
  
  // 공모전 글수정(관리자 페이지)
  @PostMapping("contestUpdate")
  public String update(Contest contest, Part[] files, Part files2, HttpSession session) throws Exception {
    contest.setContestAttachedFiles(saveAttachedFiles(files));
    contest.setThumbNail(saveThumbNailFile(files2));
    System.out.println("contest = " + contest);
    
    if (!contestService.update(contest)) {
      throw new Exception("공모전 게시글을 변경할 수 없습니다.");
    }
    
    return "redirect:contestList";
  }

  // 공모전 글등록(관리자 페이지)
  @PostMapping("contestAdd")
  public String add(Contest contest, Part[] files, Part files2, HttpServletRequest request, HttpSession session)
      throws Exception {
    contest.setContestAttachedFiles(saveAttachedFiles(files));
    contest.setThumbNail(saveThumbNailFile(files2));
    contestService.add(contest);

    return "redirect:contestList";
  }

  // 공모전 썸네일 첨부파일 처리
  private String saveThumbNailFile(Part files2) throws IOException {
    String dirPath = sc.getRealPath("/contest/files");
    
    if (files2.getSize() != 0) { // 썸네일 파일 사이즈가 0이 아니라면
      String filename = UUID.randomUUID().toString(); // 첨부파일의 UUID
      files2.write(dirPath + "/" + filename);
  
      return filename;
    }
    return null;
  }

  // 공모전 일반 첨부파일 처리
  private List<ContestAttachedFile> saveAttachedFiles(Part[] files) throws IOException, ServletException {
    List<ContestAttachedFile> contestAttachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/contest/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }

      String filename = UUID.randomUUID().toString(); // 첨부파일의 UUID
      String realFilename = part.getSubmittedFileName(); // 첨부파일의 실제파일명 (KakaoTalk_Photo_2022-09-15-20-31-04.jpeg)
      part.write(dirPath + "/" + filename);
      contestAttachedFiles.add(new ContestAttachedFile(realFilename, filename));
    }
    return contestAttachedFiles;
  }
  
  // 공모전 게시글 삭제(관리자 페이지)
  @GetMapping("contestDelete")
  public String delete(int ctstno, HttpSession session) throws Exception {

    if (!contestService.delete(ctstno)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:contestList";
  }

  @GetMapping("contestFileDelete")
  public String fileDelete(int ctstfno, HttpSession session) throws Exception {
    // 첨부파일 정보를 가져온다.
    ContestAttachedFile contestAttachedFile = contestService.getContestAttachedFile(ctstfno);
    // 첨부파일의 정보에서 공모전 글 번호를 가져온다.
    Contest contest = contestService.get(contestAttachedFile.getCtstNo());
    
    // 첨부파일을 삭제한다.
    if (!contestService.contestDeleteAttachedFile(ctstfno)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }
    
    return "redirect:contestDetail?ctstNo=" + contest.getCtstNo(); // Board 객체를 여기서도 사용해야해서 Board 객체를 생
  }
}
