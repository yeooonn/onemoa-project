package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.QnaService;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import com.bitcamp.onemoaproject.vo.qna.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.qna.Qna;
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
@RequestMapping("/qna/")
public class QnaController {
  
  @Autowired
  ServletContext sc;
  @Autowired
  QnaService qnaService;
  
  @GetMapping("form")
  public void form() throws Exception {
  }
  
  @PostMapping("add")
  public String add(
      Qna qna,
      @RequestParam("files") Part[] files,
      HttpSession session
  ) throws Exception {
    qna.setQnaAttachedFiles(saveAttachedFiles(files));
    qna.setWriter((Member) session.getAttribute("loginMember"));
    
    qnaService.add(qna);
    return "redirect:/mypage/qnaList";
  }
  
  private List<QnaAttachedFile> saveAttachedFiles(Part[] files)
      throws IOException, ServletException {
    List<QnaAttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/qna/files");
    
    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }
      String originname = part.getSubmittedFileName();
      System.out.println(originname);
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new QnaAttachedFile(originname, filename));
    }
    return attachedFiles;
  }
}