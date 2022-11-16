package com.bitcamp.onemoaproject;

import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableTransactionManagement
@SpringBootApplication

public class App {
  
  @Autowired
  ContestService contestService;
  
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
    return "index";
  }
  
  @GetMapping("test")
  public void test() {
  }
  
  @PostMapping("test1")
  @ResponseBody
  public Map test1() {
    Member member = new Member();
    member.setNo(1);
    member.setNickname("test");
  
    Contest contest = new Contest();
    contest.setCtstNo(5);
    contest.setTitle("TESTETSETTET");
    Map<String, Object> map = new HashMap();
    map.put("member", member);
    map.put("contest", contest);
    
    return map;
  }
}


