package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.FaqService;
import com.bitcamp.onemoaproject.vo.Faq;
import com.bitcamp.onemoaproject.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/faq/")
public class FaqController {

  @Autowired
  ServletContext sc;
  @Autowired
  FaqService faqService;

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("faqs", faqService.list());
  }

  @GetMapping("detail")
  public Map detail(int no) throws Exception {
    Faq faq = faqService.get(no);
    if (faq == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    Map map = new HashMap();
    map.put("faq", faq);
    return map;
  }
}






