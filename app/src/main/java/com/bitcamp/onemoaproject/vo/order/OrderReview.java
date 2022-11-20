package com.bitcamp.onemoaproject.vo.order;

import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderReview {

    private int no;
    private String title;
    private String content;
    private Date createdDate;
    private Member writer;
    private int score;
    private Order order;
    // 첨부파일을 저장할 필드
    private List<OrderReviewAttachedFile> orderReviewAttachedFiles;
}

