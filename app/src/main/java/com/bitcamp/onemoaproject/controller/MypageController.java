package com.bitcamp.onemoaproject.controller;

import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.order.OrderReviewService;
import com.bitcamp.onemoaproject.service.order.OrderService;
import com.bitcamp.onemoaproject.service.order.OrderStatusService;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.Order;
import com.bitcamp.onemoaproject.vo.order.OrderStatus;
import com.bitcamp.onemoaproject.vo.product.AttachedFile;
import com.bitcamp.onemoaproject.vo.product.Product;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

// CRUD 요청을 처리하는 페이지 컨트롤러들을 한 개의 클래스로 합친다.
@Controller // 페이지 컨트롤러에 붙이는 애노테이션
@RequestMapping("/mypage/")
@Slf4j
public class MypageController {

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
  @Autowired
  OrderService orderService;
  @Autowired
  OrderStatusService orderStatusService;

  @Autowired
  OrderReviewService orderReviewService;

  @RequestMapping("productList")
  public Model productList(Model model, HttpSession session) throws Exception {

    Member member = (Member) session.getAttribute("loginMember");
    List<Product> products = productService.list(member.getNo());

    System.out.println("products = " + products);

    model.addAttribute("products", products);

    return model;
  }

  @RequestMapping("salesList")
  public Model salesList(Model model, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    List<OrderStatus> orderStatuses = orderStatusService.list();
    List<Order> sales = orderService.salesList(member.getNo());
    model.addAttribute("orderStatuses", orderStatuses);
    model.addAttribute("sales", sales);
    return model;
  }

  @RequestMapping("buysList")
  public Model buyList(Model model, HttpSession session) throws Exception {
    log.info("{}", "butsList 호출됨!");
    Member member = (Member) session.getAttribute("loginMember");

    List<Order> buys = orderService.buysList(member.getNo());

        System.out.println("buys = " + buys);
        model.addAttribute("buys", buys);
        return model;
    }

  @ResponseBody
  @RequestMapping("updateStatus")
  public void OrderUpdate(@RequestParam("orderNo") int orderNo, @RequestParam("orderStatus") int orderStatus) throws Exception {
    // 조건 : 후기가 작성된 주문건에 대해서 상태 변경 못하게 하기

    if (!orderService.update(orderStatus, orderNo)) {
      throw new Exception("주문 상태를 변경할 수 없습니다!");
    }
  }

  @RequestMapping("productDetail")
  public Map detail(int no, Model model, HttpSession session) throws Exception {

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

    return map;
  }

  @GetMapping("productUpdate")
  public Model update(int no, Model model, HttpSession session) throws Exception {

    Product product =  productService.get(no);
    String code = product.getProductCategory().getCode();

    model.addAttribute("upperCategoryName", productCategoryService.getUpperCategory(code));
    model.addAttribute("subCategoryName",productCategoryService.getCategoryName(code));
    model.addAttribute("productCategories", productCategoryService.list());
    System.out.println("productCategoryService = " + productCategoryService.list());
    model.addAttribute("product",product);

    return model;
  }

  @PostMapping("productFileDelete")
  @ResponseBody
  public String fileDelete(
          @RequestParam("fNo") int fNo,
          @RequestParam("pNo") int pNo,
          HttpSession session)
          throws Exception {

    AttachedFile attachedFile = productService.getAttachedFile(fNo);

    // 게시글의 작성자가 로그인 사용자인지 검사한다. (남의 것 삭제할 수 있으면 안되니까)
    Member loginMember = (Member) session.getAttribute("loginMember");
    Product product = productService.get(attachedFile.getProductNo());

    if (product.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }

    // 첨부파일을 삭제한다.
    if (!productService.deleteAttachedFile(fNo)) {
      throw new Exception("게시글 첨부파일 삭제 실패!");
    }

    return "redirect:productUpdate?no=" + pNo;
  }


}

//int orderNo = Integer.parseInt(no);
//    System.out.println("orderNo = " + orderNo);
//OrderStatus orderStatus = orderStatusService.get(statusNo);
//    if (!orderService.update(orderStatus, orderNo)) {
//      throw new Exception("주문 상태를 변경할 수 없습니다!");
//    }
