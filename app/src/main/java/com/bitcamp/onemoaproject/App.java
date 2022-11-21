package com.bitcamp.onemoaproject;

import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.productService.ProductReviewService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
@EnableTransactionManagement
@SpringBootApplication

public class App {

  @Autowired
  ContestService contestService;
  
  @Autowired
  ProductReviewService productReviewService;
  
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
    model.addAttribute("reviews", productReviewService.listMainReview());
    System.out.println("re = " + productReviewService.listMainReview());
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


