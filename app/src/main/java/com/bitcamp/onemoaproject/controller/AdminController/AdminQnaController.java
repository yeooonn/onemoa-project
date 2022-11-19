package com.bitcamp.onemoaproject.controller.AdminController;

import com.bitcamp.onemoaproject.service.QnaService;
import com.bitcamp.onemoaproject.vo.qna.Qna;
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
import java.util.*;

@Controller
@RequestMapping("admin/qna/")
public class AdminQnaController {
  
  @Autowired
  ServletContext sc;
  @Autowired
  QnaService qnaService;
  
  @GetMapping("answerform")
  public Map answerform(int no) throws Exception {
    Qna qna = qnaService.get(no);
    if (qna == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    
    Map map = new HashMap();
    map.put("qna", qna);
    return map;
  }
  
  // 페이징 적용
  @GetMapping("list")
  public void list(Criteria cri, Model model) throws Exception {
    cri.setPerPageNum(10);
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(qnaService.listCount());
    
    Map<String, Object> map = new HashMap<>();
    map.put("cri", cri);
    
    List<Map<String, Object>> qnas = qnaService.list(map);
    
    model.addAttribute("qnas", qnas);
    model.addAttribute("pageMaker", pageMaker);
    System.out.println("pageMaker = " + pageMaker);
  }
  
  @GetMapping("answerdetail")
  public Map answerdetail(int no) throws Exception {
    Qna qna = qnaService.get(no);
    if (qna == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    Map map = new HashMap();
    map.put("qna", qna);
    return map;
  }
  
  @PostMapping("answerupdate")
  public String answerUpdate(
      Qna qna,
      HttpSession session)
      throws Exception {
    
    qnaService.answerUpdate(qna);
//    qna.setAttachedFiles(saveAttachedFiles(files));
//
//    checkOwner(qna.getNo(), session);

//    if (!qnaService.update(qna)) {
//      throw new Exception("게시글을 변경할 수 없습니다!");
//    }
    
    return "redirect:list";
  }
  
  @GetMapping("delete")
  public String delete(
      int no,
      HttpSession session)
      throws Exception {
    
    if (!qnaService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }
    
    return "redirect:list";
  }
}