package com.bitcamp.onemoaproject.controller.AdminController;

import com.bitcamp.onemoaproject.service.QnaService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.Qna;
import com.bitcamp.onemoaproject.vo.QnaAttachedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
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

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("qnas", qnaService.list());
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






