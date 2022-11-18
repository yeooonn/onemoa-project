package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.order.OrderService;
import com.bitcamp.onemoaproject.service.order.OrderStatusService;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductReviewService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.*;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller // 페이지 컨트롤러에 붙이는 애노테이션
@RequestMapping("/mypage/")
public class MypageController {

  @Autowired
  ServletContext sc;
  @Autowired
  ProductService productService;
  @Autowired
  ProductCategoryService productCategoryService;
  @Autowired
  ProductReviewService productReviewService;
  @Autowired
  DefaultWishService wishService;
  @Autowired
  OrderService orderService;
  @Autowired
  OrderStatusService orderStatusService;

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
}

//int orderNo = Integer.parseInt(no);
//    System.out.println("orderNo = " + orderNo);
//OrderStatus orderStatus = orderStatusService.get(statusNo);
//    if (!orderService.update(orderStatus, orderNo)) {
//      throw new Exception("주문 상태를 변경할 수 없습니다!");
//    }
