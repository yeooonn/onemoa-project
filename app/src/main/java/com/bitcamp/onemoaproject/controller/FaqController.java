package com.bitcamp.onemoaproject.controller;


import com.bitcamp.onemoaproject.service.FaqService;
import com.bitcamp.onemoaproject.vo.Faq;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/faq/")
public class FaqController {

  @Autowired
  ServletContext sc;
  @Autowired
  FaqService faqService;

//  @GetMapping("list")
//  public void list(Criteria cri, String code) throws Exception {
//    model.addAttribute("faqs", faqService.list());
//  }

  // 페이징 적용
  @GetMapping("list")
  public void list(Criteria cri, Model model) throws Exception {
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(faqService.listCount());
    List<Map<String, Object>> faqs = faqService.list(cri);

    model.addAttribute("faqs", faqs);
    model.addAttribute("pageMaker", pageMaker);
    System.out.println("pageMaker = " + pageMaker);
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






