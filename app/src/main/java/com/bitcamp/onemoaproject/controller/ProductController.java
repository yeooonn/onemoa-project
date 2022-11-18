package com.bitcamp.onemoaproject.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.productService.ProductReviewService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.paging.PageMaker;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import com.bitcamp.onemoaproject.vo.product.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller // 페이지 컨트롤러에 붙이는 애노테이션
@RequestMapping("/product/")
public class ProductController {

  @Autowired
  ServletContext sc;
  @Autowired
  ProductService productService;
  @Autowired
  ProductCategoryService productCategoryService;
  @Autowired
  ProductReviewService productReviewService;
  @Autowired
  DefaultWishService wishService;

  @GetMapping("form")
  public void form(Model model) throws Exception {
    model.addAttribute("productCategories", productCategoryService.list());
    System.out.println(productCategoryService.list());
  }

  @PostMapping("add") // 재능판매 게시판 : 게시글 등록
  public String add(
      Product product,
      @RequestParam("files") Part[] files,
      HttpSession session, HttpServletRequest request, @RequestParam("sendReferrer") String sendReferrer) throws Exception {
    System.out.println("sendReferrer = " + sendReferrer);
    System.out.println("product = " + product);

    product.setAttachedFiles(saveAttachedFiles(files));
    product.setWriter((Member) session.getAttribute("loginMember"));

    product.setThumbnail("defaultgoods.png");

    if (product.getAttachedFiles().size() > 0) {

      List<AttachedFile> attachedFiles;
      attachedFiles = product.getAttachedFiles();
      product.setThumbnail(attachedFiles.get(0).getFilepath());
      System.out.println(attachedFiles.get(0).getFilepath());

    }

    String referer2 = request.getHeader("Referer");
    System.out.println("referer2 = " + referer2);

    System.out.println("product.getWriter() = " + product.getWriter());
    productService.add(product);
    return "redirect:list";
  }

  private List<AttachedFile> saveAttachedFiles(Part[] files)
          throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/product/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }

      String originname = part.getSubmittedFileName();
      System.out.println(originname);
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new AttachedFile(originname, filename));

    }
    return attachedFiles;
  }

  @RequestMapping("list")
  public ModelAndView list(Criteria cri, String code) {

    ModelAndView mav = new ModelAndView("product/list");

    PageMaker pageMaker = new PageMaker();
    cri.setCategoryCode(code);
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(productService.countProductListTotal(code));
    List<Map<String,Object>> products = productService.selectProductList(cri);

    mav.addObject("products", products);
    mav.addObject("pageMaker", pageMaker);
    mav.addObject("productCategories", productCategoryService.list());

    return mav;
  }

  @GetMapping("detail")
  public Map detail(int no, HttpSession session) throws Exception {

    Map map = new HashMap();

    Product product = productService.get(no);
    int count = productReviewService.count(no);

    int wishCheck = wishService.get((Member) session.getAttribute("loginMember"), product);
    System.out.println("wishCheck = " + wishCheck);
    int wishCount = wishService.getCount(no);
    System.out.println("wishCount = " + wishCount);
    

    if (count != 0) { // 후기글의 개수가 0이 아니면
      double average = productReviewService.getReviewAverage(no);
      map.put("average", average);

      List<ProductReview> productReviews = productReviewService.list(no);
      map.put("reviews", productReviews);
    }
//     double average = Math.round(productReviewService.getReviewAverage(no) * 100) / 100.0;

    if (product == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    map.put("count", count);
    map.put("product", product);
    map.put("wishCheck", wishCheck);
    map.put("wishCount", wishCount);


  //  map.put("average", average);
  //  System.out.println(average);

    return map;
  }

  @PostMapping("like")
  @ResponseBody
  public Map<String, Integer> like(@RequestParam("productNo") int productNo, HttpSession session) throws Exception {

    Map<String, Integer> map = new HashMap<>();

    Member member = (Member) session.getAttribute("loginMember");
    Product product = productService.get(productNo);

    if (wishService.get(member, product) == 0) {
      wishService.add(member, productNo);
      map.put("result", 1);
      System.out.println("-> 좋아요");
    } else {
      wishService.delete(member, product);
      map.put("result", 0);
      System.out.println("-> 좋아요 취소");
    }

    map.put("count",wishService.getCount(productNo));

    return map;
  }

  @PostMapping("update")
  public String update(
          Product product,
          Part[] files,
          HttpSession session) throws Exception {

    product.setAttachedFiles(saveAttachedFiles(files));

    int pNo = product.getNo();

    checkOwner(pNo, session);

    if (!productService.update(product)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }

    return "redirect:/mypage/productDetail?no=" + pNo;
  }

  private void checkOwner(int boardNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (productService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  @GetMapping("delete")
  public String delete(
          @RequestParam("no") int no,
          HttpSession session)
          throws Exception {

    checkOwner(no, session);
    if (!productService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다!");
    }

    return "redirect:list";
  }

//  @GetMapping("fileDelete")
//  public String fileDelete(
//          @RequestParam("no") int no,
//          HttpSession session)
//          throws Exception {
//
//    AttachedFile attachedFile = productService.getAttachedFile(no);
//
//    // 게시글의 작성자가 로그인 사용자인지 검사한다. (남의 것 삭제할 수 있으면 안되니까)
//    Member loginMember = (Member) session.getAttribute("loginMember");
//    Product product = productService.get(attachedFile.getProductNo());
//
//    if (product.getWriter().getNo() != loginMember.getNo()) {
//      throw new Exception("게시글 작성자가 아닙니다.");
//    }
//
//    // 첨부파일을 삭제한다.
//    if (!productService.deleteAttachedFile(no)) {
//      throw new Exception("게시글 첨부파일 삭제 실패!");
//    }
//
//    return "redirect:detail?no=" + product.getNo();
//  }

  @ResponseBody
  @GetMapping("getSubCategories")
  public List<Map> getSubCategories(String code) {
    System.out.println(code);
   return productCategoryService.getSubCategories(code);
  }
}


