package com.bitcamp.onemoaproject.vo.order;

import com.bitcamp.onemoaproject.vo.Member;

import java.sql.Date;

public class Order {

    private int no;
    private Member buyer;
    private Date createdDate;
    private String payment;
    private Date paymentDate;
    private String status;

}
