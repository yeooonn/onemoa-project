package com.bitcamp.onemoaproject;

import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.order.OrderReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import com.bitcamp.onemoaproject.service.ContestService;

@Controller
@EnableTransactionManagement
@SpringBootApplication

public class App {

  @Autowired
  ContestService contestService;
  
  @Autowired
  OrderReviewService orderReviewService;
  
  @Autowired
  MemberService memberService;

  public static void main(String[] args) {
    System.out.println("비트캠프 프로젝트!");
    SpringApplication.run(App.class, args);
  }

  @GetMapping("/managePage")
  public String managePage() {
    return "managePage";
  }

  @GetMapping("/")
  public String welcome(@CookieValue(name = "email", defaultValue = "") String email, Model model) {
    model.addAttribute("email", email);
    model.addAttribute("contests", contestService.listMain());
    model.addAttribute("reviews", orderReviewService.listMainReview());
    System.out.println("reviews = " + orderReviewService.listMainReview());
    return "index";
  }
  
  @GetMapping("young")
  public void young() {
  }

  @GetMapping("test")
  public void test(Model model) throws Exception {
    model.addAttribute("members", memberService.list());
  }
  
  @GetMapping("testAjax")
  public void testAjax(Model model) throws Exception {
    model.addAttribute("members", memberService.listTest());
  }
}


