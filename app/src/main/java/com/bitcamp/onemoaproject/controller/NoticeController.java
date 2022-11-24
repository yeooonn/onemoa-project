package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bitcamp.onemoaproject.service.NoticeService;
import com.bitcamp.onemoaproject.vo.Notice;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다. 
@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/notice/")
public class NoticeController {

  ServletContext sc;
  NoticeService noticeService;

  public NoticeController(NoticeService noticeService, ServletContext sc) {
    System.out.println("NoticeController() 호출됨!");
    this.noticeService = noticeService;
    this.sc = sc;
  }
  
  // 페이징
  @GetMapping("list")
  public void list(Criteria cri, Model model) throws Exception {
    cri.setPerPageNum(10);
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(noticeService.listCount());
    List<Map<String, Object>> notices = noticeService.list(cri);
  
    model.addAttribute("notices", notices);
    model.addAttribute("pageMaker", pageMaker);
    System.out.println("pageMaker = " + pageMaker);
  }
  
  @GetMapping("detail")
  public Map detail(int no) throws Exception {

    Notice notice = noticeService.get(no);

    //    if (notice == null) {
    //      throw new Exception("해당 번호의 게시글이 없습니다!");
    //    }

    Map map = new HashMap(); 
    map.put("notice", notice);
    return map;
  }
}






