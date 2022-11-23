package com.bitcamp.onemoaproject.controller.AdminController;

import com.bitcamp.onemoaproject.service.DefaultWishService;
import com.bitcamp.onemoaproject.service.order.OrderReviewService;
import com.bitcamp.onemoaproject.service.productService.ProductCategoryService;
import com.bitcamp.onemoaproject.service.productService.ProductService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.order.OrderReview;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import com.bitcamp.onemoaproject.vo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/product/")
public class AdminProductController {

    @Autowired
    ServletContext sc;

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    OrderReviewService orderReviewService;

    @Autowired
    DefaultWishService wishService;

    @RequestMapping("productList")
    public ModelAndView list(Criteria cri, String code) throws Exception {

        ModelAndView mav = new ModelAndView("admin/product/productList");

        List<Product> products = productService.listByAdmin();

        mav.addObject("products", products);

        return mav;
    }

    @RequestMapping("productDetail")
    public Map detail(int no, Model model, HttpSession session) throws Exception {

        Map map = new HashMap();

        Product product = productService.get(no);
        int count = orderReviewService.count(no);

        int wishCheck = wishService.get((Member) session.getAttribute("loginMember"), product);
        System.out.println("wishCheck = " + wishCheck);
        int wishCount = wishService.getCount(no);
        System.out.println("wishCount = " + wishCount);

        if (count != 0) { // 후기글의 개수가 0이 아니면
            double average = orderReviewService.getReviewAverage(no);
            map.put("average", average);

            List<OrderReview> productReviews = orderReviewService.list(no);
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

    @PostMapping("productInvalid")
    @ResponseBody
    public String productInvalid(@RequestParam("no") int no) throws Exception {

        productService.invalid(no);

        return "redirect:productDetail?no=" + no;
    }


    @PostMapping("productValid")
    @ResponseBody
    public String productValid(@RequestParam("no") int no) throws Exception {

        System.out.println("no = " + no);
        productService.valid(no);

        return "redirect:productDetail?no=" + no;
    }


    @GetMapping("detail")
    public Map detail(int no, HttpSession session) throws Exception {

        Map map = new HashMap();

        Product product = productService.get(no);
        int count = orderReviewService.count(no);
        System.out.println("count = " + count);

        int wishCheck = wishService.get((Member) session.getAttribute("loginMember"), product);

        System.out.println("wishCheck = " + wishCheck);
        int wishCount = wishService.getCount(no);
        System.out.println("wishCount = " + wishCount);


        if (count != 0) { // 후기글의 개수가 0이 아니면
            double average = orderReviewService.getReviewAverage(no);
            map.put("average", average);

            List<OrderReview> orderReviews = orderReviewService.listByPno(no);
            map.put("reviews", orderReviews);
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

    @ResponseBody
    @GetMapping("getSubCategories")
    public List<Map> getSubCategories(String code) {
        System.out.println(code);
        return productCategoryService.getSubCategories(code);
    }
}
