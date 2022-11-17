package com.bitcamp.onemoaproject.controller.AdminController;


import com.bitcamp.onemoaproject.service.NoticeService;
import com.bitcamp.onemoaproject.vo.Notice;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/notice")
public class AdminNoticeController {
  
  @Autowired
  ServletContext sc;
  
  @Autowired
  NoticeService noticeService;
  
  // InternalResourceViewResolver 사용 후:
  @GetMapping("form")
  public void form() throws Exception {
  }
  
  @PostMapping("add")
  public String add(
      Notice notice,
      HttpSession session) throws Exception {
    
    noticeService.add(notice);
    return "redirect:list";
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
  
  @PostMapping("update")
  public String update(
      Notice notice,
      HttpSession session) throws Exception {
    
    //    checkOwner(board.getNo(), session);
    
    if (!noticeService.update(notice)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }
    
    return "redirect:list";
  }
  
  @GetMapping("delete")
  public String delete(
      @RequestParam("no") int no,
      HttpSession session)
      throws Exception {
    
    //    checkOwner(no, session);
    if (!noticeService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다!");
    }
    
    return "redirect:list";
  }
}
