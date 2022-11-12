package com.bitcamp.onemoaproject.controller.AdminController;

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
@RequestMapping("/admin/faq/")
public class AdminFaqController {

        @Autowired
        ServletContext sc;
        @Autowired
        FaqService faqService;

    @GetMapping("form")
    public void form() throws Exception {
    }

    @PostMapping("add")
        public String add(
                Faq faq,
                HttpSession session) throws Exception {
            faq.setWriter((Member) session.getAttribute("loginMember"));
            faqService.add(faq);
            return "redirect:list";
        }

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

        @PostMapping("update")
        public String update(Faq faq) throws Exception {
            faqService.update(faq);

            return "redirect:list";
        }

//  private void checkOwner(int boardNo, HttpSession session) throws Exception {
//    Member loginMember = (Member) session.getAttribute("loginMember");
//    if (faqService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
//      throw new Exception("게시글 작성자가 아닙니다.");
//    }
//  }

        @GetMapping("delete")
        public String delete(int no) throws Exception {
            System.out.println("faq/delete 호출!");
            faqService.delete(no);

//    checkOwner(no, session);
//    if (!faqService.delete(no)) {
//      throw new Exception("게시글을 삭제할 수 없습니다.");
//    }

            return "redirect:list";
        }
    }

