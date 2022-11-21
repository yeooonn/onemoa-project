package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.order.OrderReviewService;
import com.bitcamp.onemoaproject.service.order.OrderService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.order.OrderReviewAttachedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("order/review/")
public class OrderReviewController {

  @Autowired
  ServletContext sc;
  @Autowired
  OrderReviewService orderReviewService;

  @Autowired
  OrderService orderService;

  @GetMapping("reviewForm")
  public Map form(int no) throws Exception {
    System.out.println("reviewForm실행성공!");
    Order order = orderService.get(no);
    System.out.println("order = " + order);

    Map map = new HashMap();

    map.put("order", order);
    return map;
  }

  @PostMapping("reviewAdd")
  public String reviewAdd(
          int orderNo,
          OrderReview orderReview,
          @RequestParam(value = "files", required = false) Part[] files,
          HttpSession session
  ) throws Exception {

    Order order = orderService.get(orderNo);
    orderReview.setOrder(order);
    orderReview.setOrderReviewAttachedFiles(saveAttachedFiles(files));

    System.out.println("files = " + Arrays.toString(files));

    orderReview.setWriter((Member) session.getAttribute("loginMember"));

    System.out.println("orderReview = " + orderReview);
    orderReviewService.reviewAdd(orderReview);
    return "redirect:/";
  }

  private List<OrderReviewAttachedFile> saveAttachedFiles(Part[] files)
          throws IOException, ServletException {
    List<OrderReviewAttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/review/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }
      String originname = part.getSubmittedFileName();
      System.out.println(originname);
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new OrderReviewAttachedFile(originname, filename));
    }
    return attachedFiles;
  }

  @GetMapping("list")
  public void list(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    if (member != null) {
      model.addAttribute("orderReviews", orderReviewService.list(member.getNo()));
    } else {
      model.addAttribute("orderReviews", orderReviewService.list());
    }
  }

  @GetMapping("reviewDetail")
  public Map detail(int no) throws Exception {
    OrderReview orderReview = orderReviewService.get(no);
    Order order = orderService.get(no);

    Map map = new HashMap();

    map.put("order", order);
    map.put("orderReview", orderReview);
    return map;
  }

  @PostMapping("reviewUpdate")
  public String reviewUpdate(
          OrderReview orderReview,
          Part[] files,
          HttpSession session)
          throws Exception {
    orderReview.setWriter((Member) session.getAttribute("loginMember"));
    orderReview.setOrderReviewAttachedFiles(saveAttachedFiles(files));
//    checkOwner(orderReview.getNo(), session);

    System.out.println("orderReview = " + orderReview);

    if (!orderReviewService.reviewUpdate(orderReview)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:list";
  }

//  private void checkOwner(int OrderNo, HttpSession session) throws Exception {
//    Member loginMember = (Member) session.getAttribute("loginMember");
//    if (orderReviewService.get(OrderNo).getWriter().getNo() != loginMember.getNo()) {
//      throw new Exception("게시글 작성자가 아닙니다.");
//    }
//  }

//  @GetMapping("delete")
//  public String delete(
//      int no,
//      HttpSession session)
//          throws Exception {
//
////    checkOwner(no, session);
//    if (!qnaService.delete(no)) {
//      throw new Exception("게시글을 삭제할 수 없습니다.");
//    }
//
//    return "redirect:list";
//  }

  @GetMapping("fileDelete")
  public String fileDelete(
          int no,
          HttpSession session)
          throws Exception {

    OrderReviewAttachedFile attachedFile = orderReviewService.getAttachedFile(no);

    Member loginMember = (Member) session.getAttribute("loginMember");
    OrderReview orderReview = orderReviewService.get(attachedFile.getNo());

    if (orderReview.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }

    if (!orderReviewService.deleteAttachedFile(no)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }

    return "redirect:updateform?no=" + orderReview.getNo();
  }
}

