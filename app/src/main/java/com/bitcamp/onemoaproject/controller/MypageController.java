package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.DefaultOrderService;
import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductReviewService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import com.bitcamp.onemoaproject.vo.product.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
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
  DefaultOrderService orderService;

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
    List<Order> sales = orderService.salesList(member.getNo());
    System.out.println("sales = " + sales);
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
}


