package com.bitcamp.onemoaproject.vo.order;

import com.bitcamp.onemoaproject.vo.Member;
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
    private double score;
    private Order order;
    private int status;
    // 첨부파일을 저장할 필드
    private List<OrderReviewAttachedFile> orderReviewAttachedFiles;
}

