package com.bitcamp.onemoaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@EnableTransactionManagement
@SpringBootApplication
public class App {

  public static void main(String[] args) {
    System.out.println("비트캠프 프로젝트!");
    SpringApplication.run(App.class, args);
  }


  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("index")
  public String welcome2(@CookieValue(name = "email", defaultValue = "") String email,
      Model model) {
    model.addAttribute("email", email);
    return "index";
  }

  @GetMapping("/contestTeam")
  public String contestTeam() {
    return "contestTeam";
  }

  @GetMapping("/joinform")
  public String joinForm() {
    return "joinForm";
  }

  @GetMapping("/productForm")
  public String productForm() {
    return "productForm";
  }


  //  @GetMapping("/notice/list")
  //  public String noticeList() {
  //    return "notice/list";
  //  }


}


