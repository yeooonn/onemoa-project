package com.bitcamp.onemoaproject.vo.order;

import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Order {
    private int no;
    private Product product;
    private Member buyer;
    private LocalDateTime createdDate;
    private String postNo;
    private String baseAddr;
    private String detailAddr;
    private OrderStatus orderStatus;
}
