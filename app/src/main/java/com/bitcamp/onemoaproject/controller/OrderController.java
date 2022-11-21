package com.bitcamp.onemoaproject.controller;


import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.order.OrderService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller // 페이지 컨트롤러에 붙이는 애노테이션
@RequestMapping("/order/")
public class OrderController {

  @Autowired
  ServletContext sc;

  @Autowired
  ProductService productService;

  @Autowired
  MemberService memberService;

  @Autowired
  OrderService orderService;

  @PostMapping("form")
  public void form(Model model, int no, HttpSession session) throws Exception {

    Product product = productService.get(no);
    Member member = (Member) session.getAttribute("loginMember");

    Member buyer = memberService.get(member.getNo());

    model.addAttribute("buyer", buyer);
    model.addAttribute("product", product);
  }

//  @ResponseBody
  @PostMapping("add") // 재능판매 게시판 : 게시글 등록
  public String add(@RequestParam("no") int no, Order order, HttpSession session, Model model) throws Exception {

    order.setProduct(productService.get(no));
    order.setBuyer((Member) session.getAttribute("loginMember"));

    orderService.add(order);
    System.out.println("order = " + order);

    int orderNo = order.getNo();

    return "redirect:/order/add?no=" + orderNo;
  }

  @GetMapping("add")
  public Model add(int no, HttpSession session, Model model) throws Exception {

    model.addAttribute("order", orderService.get(no));

    return model;
  }

//
////  @GetMapping("list")
////  public void list(Model model) throws Exception {
////    model.addAttribute("products", productService.list());
////    model.addAttribute("productCategories", productCategoryService.list());
////    // System.out.println(productCategoryService.list());
////  }
//
//  @RequestMapping("list")
//  public ModelAndView list(String code, Criteria cri) {
//
//    ModelAndView mav = new ModelAndView("product/list");
//
//    PageMaker pageMaker = new PageMaker();
//    cri.setCode(code);
//    pageMaker.setCri(cri);
//    pageMaker.setTotalCount(productService.countProductListTotal());
//
//    System.out.println("code = " + code + ", cri = " + cri);
//
//    List<Map<String,Object>> products = productService.selectProductList(cri);
//    mav.addObject("products", products);
//    mav.addObject("pageMaker", pageMaker);
//    mav.addObject("productCategories", productCategoryService.list());
//
//
//    return mav;
//  }
//
//  @GetMapping("detail")
//  public Map detail(int no, HttpSession session) throws Exception {
//
//    Map map = new HashMap();
//
//    Product product = productService.get(no);
//    int count = productReviewService.count(no);
//
//    int wishCheck = wishService.get((Member) session.getAttribute("loginMember"), product);
//    System.out.println("wishCheck = " + wishCheck);
//    int wishCount = wishService.getCount(no);
//    System.out.println("wishCount = " + wishCount);
//
//
//    if (count != 0) { // 후기글의 개수가 0이 아니면
//      double average = productReviewService.getReviewAverage(no);
//      map.put("average", average);
//
//      List<ProductReview> productReviews = productReviewService.list(no);
//      map.put("reviews", productReviews);
//    }
////     double average = Math.round(productReviewService.getReviewAverage(no) * 100) / 100.0;
//
//    if (product == null) {
//      throw new Exception("해당 번호의 게시글이 없습니다!");
//    }
//
//    map.put("count", count);
//    map.put("product", product);
//    map.put("wishCheck", wishCheck);
//    map.put("wishCount", wishCount);
//
//
//  //  map.put("average", average);
//  //  System.out.println(average);
//
//    return map;
//  }
//
//  @PostMapping("like")
//  @ResponseBody
//  public Map<String, Integer> like(@RequestParam("productNo") int productNo, HttpSession session) throws Exception {
//
//    Map<String, Integer> map = new HashMap<>();
//
//    Member member = (Member) session.getAttribute("loginMember");
//    Product product = productService.get(productNo);
//
//    if (wishService.get(member, product) == 0) {
//      wishService.add(member, productNo);
//      map.put("result", 1);
//      System.out.println("-> 좋아요");
//    } else {
//      wishService.delete(member, product);
//      map.put("result", 0);
//      System.out.println("-> 좋아요 취소");
//    }
//
//    map.put("count",wishService.getCount(productNo));
//
//    return map;
//  }
//
//  @PostMapping("update")
//  public String update(
//          Product product,
//          Part[] files,
//          HttpSession session) throws Exception {
//
//    product.setAttachedFiles(saveAttachedFiles(files));
//
//    checkOwner(product.getNo(), session);
//
//    if (!productService.update(product)) {
//      throw new Exception("게시글을 변경할 수 없습니다.");
//    }
//
//    return "redirect:list";
//  }
//
//  private void checkOwner(int boardNo, HttpSession session) throws Exception {
//    Member loginMember = (Member) session.getAttribute("loginMember");
//    if (productService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
//      throw new Exception("게시글 작성자가 아닙니다.");
//    }
//  }
//
//  @GetMapping("delete")
//  public String delete(
//          @RequestParam("no") int no,
//          HttpSession session)
//          throws Exception {
//
//    checkOwner(no, session);
//    if (!productService.delete(no)) {
//      throw new Exception("게시글을 삭제할 수 없습니다!");
//    }
//
//    return "redirect:list";
//  }
//
//  @GetMapping("fileDelete")
//  public String fileDelete(
//          @RequestParam("no") int no,
//          HttpSession session)
//          throws Exception {
//
//    AttachedFile attachedFile = productService.getAttachedFile(no);
//
//    // 게시글의 작성자가 로그인 사용자인지 검사한다. (남의 것 삭제할 수 있으면 안되니까)
//    Member loginMember = (Member) session.getAttribute("loginMember");
//    Product product = productService.get(attachedFile.getProductNo());
//
//    if (product.getWriter().getNo() != loginMember.getNo()) {
//      throw new Exception("게시글 작성자가 아닙니다.");
//    }
//
//    // 첨부파일을 삭제한다.
//    if (!productService.deleteAttachedFile(no)) {
//      throw new Exception("게시글 첨부파일 삭제 실패!");
//    }
//
//    return "redirect:detail?no=" + product.getNo();
//  }
//
//  @ResponseBody
//  @GetMapping("getSubCategories")
//  public List<Map> getSubCategories(String code) {
//    System.out.println(code);
//   return productCategoryService.getSubCategories(code);
//  }
}


