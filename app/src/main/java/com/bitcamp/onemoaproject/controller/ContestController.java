package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamField;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamFieldMember;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamFieldMemberPortfolio;
import com.bitcamp.onemoaproject.vo.contest.ContestTeamPortfolio;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.lang.model.SourceVersion;
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
  public void contestTeamList(Criteria cri, Model model, String no, String ono, String sortCd, String sortEd,
      String sortVw, String sortRw, HttpSession session) throws Exception {
    if (no == null) {
      no = "all";
    }
    System.out.println("no = " + no);
  
    cri.setPerPageNum(5);
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(contestService.listCount());
  
    Map<String, Object> map = new HashMap<>();
    map.put("cri", cri);
    map.put("no", no);
    map.put("ono", ono);
    map.put("sortCd", sortCd);
    map.put("sortEd", sortEd);
    map.put("sortVw", sortVw);
    map.put("sortRw", sortRw);
    
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("contests", contestService.list(map));
    model.addAttribute("no", no);
    model.addAttribute("ono", ono);
    model.addAttribute("sortCd", sortCd);
    model.addAttribute("sortEd", sortEd);
    model.addAttribute("sortVw", sortVw);
    model.addAttribute("sortRw", sortRw);
    model.addAttribute("pageMaker", pageMaker);
    model.addAttribute("member", loginMember);
  }
  
  // 공모전 디테일
  @PostMapping("contestTeam/detail")
  @ResponseBody
  public Contest contestTeamDetail(int contestNumber) throws Exception {
    Contest contest = contestService.get(contestNumber);
    contestService.addViewCount(contestNumber);
    return contest;
  }
  
  // 공모전 팀원구해요
  @PostMapping("contestTeam/teamList")
  @ResponseBody
  public Object contestTeamTeamList(Model model, int contestNumber) throws Exception {
    model.addAttribute("teams", contestService.getTeamList(contestNumber));
    return model.getAttribute("teams");
  }
  
  // 공모전 팀원 모집하기 폼(회원정보)
  @PostMapping("contestTeam/teamRecruitForm")
  @ResponseBody
  public Member contestTeamTeamRecruit(HttpSession session) throws Exception {
    System.out.println("session.getAttribute(\"loginMember\") = " + session.getAttribute("loginMember"));
    Member loginMember = (Member) session.getAttribute("loginMember");
  
    System.out.println("loginMember = " + loginMember);
    if (loginMember == null) {
      // loginMember.setStatus(0);
      return null;
    }
    
    else  {
      Member member = memberService.get(loginMember.getNo());
      return member;
    }
  }
  
  // 공모전 팀원 모집하기 폼(회원 포트폴리오)
  @PostMapping("contestTeam/teamRecruitForm2")
  @ResponseBody
  public Object contestTeamTeamRecruit2(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("portfolio", portfolioService.list2(loginMember.getNo()));
    System.out.println("model.getAttribute(\"portfolio\") = " + model.getAttribute("portfolio"));
    return model.getAttribute("portfolio");
  }
  
  // 공모전 팀원구해요 등록
  @PostMapping("contestTeam/teamRecruit")
  @ResponseBody
  public String contestTeamRecruit(HttpSession session,
      Model model, int contestNumber, int memberNo, String textArea,
      @RequestParam(value = "portfolios[]") List<String> portfolios,
      @RequestParam(value = "recruitments[]") List<String> recruitments) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    List<ContestTeamPortfolio> contestTeamPortfolios = new ArrayList<>();
    List<ContestTeamField> contestTeamFields = new ArrayList<>();
    ContestTeam contestTeam = new ContestTeam();
    
    if (loginMember != null) {
      contestTeam.setCtstno(contestNumber);
      contestTeam.setMno(memberNo);
      contestTeam.setCont(textArea);
      
      for (String portfolio : portfolios) {
        contestTeamPortfolios.add(new ContestTeamPortfolio(portfolio));
      }
      contestTeam.setContestTeamPortfolios(contestTeamPortfolios);
      
      for (int i = 0; i < recruitments.size(); i += 2) {
        contestTeamFields.add(new ContestTeamField(recruitments.get(i), recruitments.get(i + 1)));
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
  
  // 공모전 팀전 팀장 상세보기
  @PostMapping("contestTeam/readerDetail")
  @ResponseBody
  public ContestTeam contestReaderDetail(int contestNumber, int memberNumber) throws Exception {
    ContestTeam contestTeam = contestService.getTeamReader(contestNumber, memberNumber);
    return contestTeam;
  }
  
  // 공모전 팀장 모집분야 조회
  @PostMapping("contestTeam/readerField")
  @ResponseBody
  public List<ContestTeamField> contestReaderField(int teamNumber) throws Exception {
    System.out.println("teamNumber = " + teamNumber);
    List<ContestTeamField> contestTeamField = contestService.getTeamField(teamNumber);
    return contestTeamField;
  }
  
  // 공모전 팀원모집분야 지원자 조회
  @PostMapping("contestTeam/fieldList")
  @ResponseBody
  public List<ContestTeamField> contestTeamFieldList(int teamNumber, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    List<ContestTeamField> contestTeamFieldList = contestService.getFieldMember(
        teamNumber);
    
    System.out.println("contestTeamFieldList = " + contestTeamFieldList);
    return contestTeamFieldList;
  }
  
  // 공모전 팀원모집분야 지원자 상세정보
  @PostMapping("contestTeam/fieldMemberDetail")
  @ResponseBody
  public List<Member> fieldMemberDetail(HttpSession session, int readerNumber) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember.getNo() == readerNumber) {
      return null;
    }
    else if (loginMember != null) {
      List<Member> member = memberService.getFieldMemberPortfolio(loginMember.getNo());
      return member;
    }
    return null;
  }
  
  // 공모전 팀원모집분야 지원자 지원하기
  @PostMapping("contestTeam/fieldMemberAdd")
  @ResponseBody
  public String fieldMemberAdd(HttpSession session, String textArea,
      @RequestParam(value = "portfolios1[]") List<String> portfolios1,
      @RequestParam(value = "selectObj[]") List<Integer> selectObj) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (selectObj.size() > 0) {
      
      for (int i = 0; i < selectObj.size(); i++) {
        ContestTeamFieldMember contestTeamFieldMember = new ContestTeamFieldMember();
        List<ContestTeamFieldMemberPortfolio>  contestTeamFieldMemberPortfolios = new ArrayList<>();
        
        contestTeamFieldMember.setTfno(selectObj.get(i)); // 팀원 모집분야 번호 0번째
        contestTeamFieldMember.setMno(loginMember.getNo()); // 지원자 회원 번호
        contestTeamFieldMember.setCont(textArea);
        contestTeamFieldMember.setType("false");
  
        for (String portfolio1 : portfolios1) {
          contestTeamFieldMemberPortfolios.add(new ContestTeamFieldMemberPortfolio(portfolio1));
        }
        contestTeamFieldMember.setContestTeamFieldMemberPortfolioList(contestTeamFieldMemberPortfolios);
        contestService.addFieldMember(contestTeamFieldMember);
        System.out.println("ContestTeamFieldMember = " + contestTeamFieldMember);
      }
    }
    return "true";
  }
  
  // 공모전 팀원 상세보기(지원자보기)
  @PostMapping("contestTeam/fieldMemberDetailView")
  @ResponseBody
  public ContestTeamFieldMember fieldMemberDetailView(HttpSession session, int fmNumber) throws Exception{
    Member loginMember = (Member) session.getAttribute("loginMember");
    System.out.println("fmNumber = " + fmNumber);
    return contestService.getFieldMemberDetail(fmNumber);
  }
  
  // 공모전 팀원 선택하기
  @PostMapping("contestTeam/fieldMemberChoice")
  @ResponseBody
  public String fieldMemberChoice(int fmNo, String cType, int reNumber, HttpSession session) throws Exception{
    Member loginMember = (Member) session.getAttribute("loginMember");
    System.out.println("reNumber = " + reNumber);
    System.out.println("loginMember.getNo() = " + loginMember.getNo());
    if (loginMember.getNo() == reNumber) {
      System.out.println("fmNo = " + fmNo);
      System.out.println("cType = " + cType);
      String booleanValue = "미승인";
      if (cType == null) {
        cType = "지원";
      }
      if (cType.contains("취소")) {
        contestService.updateFieldMemberType(fmNo, booleanValue);
      } else if (cType.contains("지원")) {
        booleanValue = "승인";
        contestService.updateFieldMemberType(fmNo, booleanValue);
      }
      return "성공";
    }
    return "실패";
  }
}