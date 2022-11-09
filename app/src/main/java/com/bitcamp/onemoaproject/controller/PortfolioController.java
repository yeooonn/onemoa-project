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
import com.bitcamp.onemoaproject.service.PortfolioService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import com.bitcamp.onemoaproject.vo.portfolio.PortfolioAttachedFile;

@Controller
@RequestMapping("portfolio")
public class PortfolioController {

  ServletContext sc;
  PortfolioService portfolioService;

  public PortfolioController(PortfolioService portfolioService, ServletContext sc) {
    System.out.println("PortfolioController() 호출됨!");
    this.portfolioService = portfolioService;
    this.sc = sc;
  }

  @GetMapping("portfolio1")
  public String portfolio1() {
    return "portfolio/portfolio1";
  }

  @GetMapping("portfolioList")
  public String list(Model model, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("portfolios", portfolioService.list(loginMember.getNo()));
    //    model.getAttribute("portfolios");
    //    System.out.println(model.getAttribute("portfolios"));
    return "portfolio/portfolioList";
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
  public Map portfolioDetail(int ptNo) throws Exception {
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
  public Map firstportfolio(Model model, HttpSession session, int ptNo) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("portfolios", portfolioService.list(loginMember.getNo()));
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






