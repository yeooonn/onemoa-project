package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamField;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamPortfolio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.PortfolioService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;

@Controller
@RequestMapping("contest")
public class ContestController {

  @Autowired
  ServletContext sc;
  @Autowired
  ContestService contestService;
  @Autowired
  MemberService memberService;
  @Autowired
  PortfolioService portfolioService;

  // 공모전 목록 출력
  @GetMapping("contestTeam")
  public void contestTeamList(Model model, String no, String ono, String sortCd) throws Exception {
    model.addAttribute("contests", contestService.list(no, ono, sortCd));
    model.addAttribute("no", no);
    model.addAttribute("ono", ono);
    model.addAttribute("sortCd", sortCd);
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
  public Object contestTeamTeamRecruit(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if(loginMember != null){
      model.addAttribute("member", memberService.get(loginMember.getNo()));
      System.out.println("model.getAttribute(\"member\") = " + model.getAttribute("member"));
      return model.getAttribute("member");
    }
    loginMember.setStatus(false);
    return loginMember;
  }

  @PostMapping("contestTeam/teamRecruitForm2")
  @ResponseBody
  public Object contestTeamTeamRecruit2(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("portfolio", portfolioService.list2(loginMember.getNo()));
    System.out.println("model.getAttribute(\"portfolio\") = " + model.getAttribute("portfolio"));
    return model.getAttribute("portfolio");
  }
  
  @PostMapping("contestTeam/teamRecruit")
  @ResponseBody
  public String contestTeamRecruit(HttpSession session,
      Model model, int contestNumber, int memberNo, String textArea,
      @RequestParam(value="portfolios[]") List<String> portfolios,
      @RequestParam(value="recruitments[]") List<String> recruitments) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    List<ContestTeamPortfolio> contestTeamPortfolios = new ArrayList<>();
    List<ContestTeamField> contestTeamFields = new ArrayList<>();
    ContestTeam contestTeam = new ContestTeam();
    
    if(loginMember != null){
      contestTeam.setCtstno(contestNumber);
      contestTeam.setMno(memberNo);
      contestTeam.setCont(textArea);
  
      for (String portfolio : portfolios) {
        contestTeamPortfolios.add(new ContestTeamPortfolio(portfolio));
      }
      contestTeam.setContestTeamPortfolios(contestTeamPortfolios);
  
      for (int i = 0; i < recruitments.size(); i+=2) {
        contestTeamFields.add(new ContestTeamField(recruitments.get(i), recruitments.get(i+1)));
      }
      contestTeam.setContestTeamFields(contestTeamFields);
  
      System.out.println("contestTeamPortfolios = " + contestTeamPortfolios);
      System.out.println("contestTeamFields = " + contestTeamFields);
      System.out.println("contestTeam = " + contestTeam);
      contestService.addTeam(contestTeam);
      return "true";
    }
    return "false";
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
}
