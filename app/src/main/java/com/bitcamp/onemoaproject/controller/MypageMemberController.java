package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.order.OrderReviewService;
import com.bitcamp.onemoaproject.service.order.OrderService;
import com.bitcamp.onemoaproject.service.order.OrderStatusService;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.contest.ContestTeam;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.PortfolioService;
import com.bitcamp.onemoaproject.vo.Interest;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.portfolio.Portfolio;
import com.bitcamp.onemoaproject.vo.portfolio.PortfolioAttachedFile;

@Controller
@RequestMapping("mypage")
public class MypageMemberController {

  @Autowired
  ServletContext sc;
  
  @Autowired
  MemberService memberService;
  
  @Autowired
  PortfolioService portfolioService;
  
  @Autowired
  ContestService contestService;
  
  @Autowired
  ProductService productService;
  
  @Autowired
  ProductCategoryService productCategoryService;
  
  @Autowired
  DefaultWishService wishService;
  
  @Autowired
  OrderService orderService;
  
  @Autowired
  OrderStatusService orderStatusService;
  
  @Autowired
  OrderReviewService orderReviewService;

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

    // 2. 새 비밀번호, 새비밀번호 확인 맞는지 체크
    if (newPassword.equals(newPasswordConfirm) == false) { // 새 비밀번호와 새 비밀번호 확인이 일치하기않으면
      throw new Exception("새 비밀번호와 새 비밀번호 확인이 서로 일치하지 않습니다.");
    }
    // 3. DB 비밀번호 변경
    memberService.modifyPasswd(email, newPassword);
    return "redirect:changepwResult";
  }


  @ResponseBody
  @PostMapping("checkCurrentPassword")
  public String checkCurrentPassowrd(String password, HttpSession session) throws Exception {
    // 1. 현재 비밀번호 맞는지 체크
    Member loginMember = (Member) session.getAttribute("loginMember");

    int result = memberService.getPasswordCheck(password, loginMember.getNo());
    System.out.println("result = " + result);

    if (result > 0) {
      return "true";
    }
    return "false";

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

  @Transactional
  @PostMapping("myinfoUpdate")
  public String myinfoUpdate(Member member, MultipartFile files, HttpSession session,
      String design, String it, String video, String marketing, String translate,
      String write, String business) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember"); 
    member.setProfile(saveProfile(files));

    // 기존 관심사 삭제
    memberService.deleteInterest(loginMember.getNo());

    String[] interestName = {design, it, video, marketing, translate, write, business};

    for (int i = 0; i < 7; i++) {
      if (interestName[i] != null) {
        Interest interest = new Interest();
        interest.setMno(loginMember.getNo());
        interest.setPcno(interestName[i]);

        // 새 관심사 추가
        if (memberService.addInterest(interest) == 0) {
          throw new Exception("관심사 등록에 실패하였습니다!");
        }
      }
    }

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
    System.out.println("portfolio = " + portfolio);
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

  // 마이페이지 공모전 참여내역
  @GetMapping("contestList")
  public String myContestList(Model model, HttpSession session) throws Exception{
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.addAttribute("contests", contestService.myContestList(loginMember.getNo()));
    return "mypage/contest/contestList";
  }
  
  @GetMapping("contestDetail")
  public String myContestDetail(Model model, HttpSession session, int no) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.addAttribute("contest", contestService.get(no));
    System.out.println("no = " + no);
    return "mypage/contest/contestDetail";
  }
  
  @GetMapping("contestTeamDetail")
  public String myContestTeamDetail(Model model, HttpSession session, int no) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    model.addAttribute("member", memberService.get(loginMember.getNo()));
    model.addAttribute("leader", contestService.getTeamReader(no, loginMember.getNo()));
    ContestTeam team = contestService.getTeamReader(no, loginMember.getNo());
    System.out.println("team.getTno() = " + team.getTno());
    model.addAttribute("fields", contestService.getTeamField(team.getTno()));
    model.addAttribute("fieldMembers", contestService.getFieldMember(team.getTno()));
    return "mypage/contest/contestTeamDetail";
  }
  
  @RequestMapping("productList")
  public Model productList(Model model, HttpSession session) throws Exception {
    
    Member member = (Member) session.getAttribute("loginMember");
    List<Product> products = productService.list(member.getNo());
    
    System.out.println("products = " + products);
    
    model.addAttribute("products", products);
    
    return model;
  }
  
  @RequestMapping("salesList")
  public Model salesList(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    List<OrderStatus> orderStatuses = orderStatusService.list();
    List<Order> sales = orderService.salesList(member.getNo());
    model.addAttribute("orderStatuses", orderStatuses);
    model.addAttribute("sales", sales);
    return model;
  }
  
  @RequestMapping("buysList")
  public Model buyList(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    
    List<Order> buys = orderService.buysList(member.getNo());
    
    System.out.println("buys = " + buys);
    model.addAttribute("buys", buys);
    return model;
  }
  
  @ResponseBody
  @RequestMapping("updateStatus")
  public void OrderUpdate(@RequestParam("orderNo") int orderNo, @RequestParam("orderStatus") int orderStatus) throws Exception {
    // 조건 : 후기가 작성된 주문건에 대해서 상태 변경 못하게 하기
    
    if (!orderService.update(orderStatus, orderNo)) {
      throw new Exception("주문 상태를 변경할 수 없습니다!");
    }
  }
  
  @RequestMapping("productDetail")
  public Map detail(int no, Model model, HttpSession session) throws Exception {
    
    Map map = new HashMap();
    
    Product product = productService.get(no);
    int count = orderReviewService.count(no);
    
    int wishCheck = wishService.get((Member) session.getAttribute("loginMember"), product);
    System.out.println("wishCheck = " + wishCheck);
    int wishCount = wishService.getCount(no);
    System.out.println("wishCount = " + wishCount);
    
    
    if (count != 0) { // 후기글의 개수가 0이 아니면
      double average = orderReviewService.getReviewAverage(no);
      map.put("average", average);
      
      List<OrderReview> productReviews = orderReviewService.list(no);
      map.put("reviews", productReviews);
    }
//     double average = Math.round(productReviewService.getReviewAverage(no) * 100) / 100.0;
    
    if (product == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    
    map.put("count", count);
    map.put("product", product);
    map.put("wishCheck", wishCheck);
    map.put("wishCount", wishCount);
    
    return map;
  }
  
  @GetMapping("productUpdate")
  public Model update(int no, Model model, HttpSession session) throws Exception {
    
    Product product =  productService.get(no);
    String code = product.getProductCategory().getCode();
    
    model.addAttribute("upperCategoryName", productCategoryService.getUpperCategory(code));
    model.addAttribute("subCategoryName",productCategoryService.getCategoryName(code));
    model.addAttribute("productCategories", productCategoryService.list());
    System.out.println("productCategoryService = " + productCategoryService.list());
    model.addAttribute("product",product);
    
    return model;
  }
  
  @PostMapping("productFileDelete")
  @ResponseBody
  public String fileDelete(
      @RequestParam("fNo") int fNo,
      @RequestParam("pNo") int pNo,
      HttpSession session)
      throws Exception {
    
    AttachedFile attachedFile = productService.getAttachedFile(fNo);
    
    // 게시글의 작성자가 로그인 사용자인지 검사한다. (남의 것 삭제할 수 있으면 안되니까)
    Member loginMember = (Member) session.getAttribute("loginMember");
    Product product = productService.get(attachedFile.getProductNo());
    
    if (product.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
    
    // 첨부파일을 삭제한다.
    if (!productService.deleteAttachedFile(fNo)) {
      throw new Exception("게시글 첨부파일 삭제 실패!");
    }
    
    return "redirect:productUpdate?no=" + pNo;
  }
}






























