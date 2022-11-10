package com.bitcamp.onemoaproject.vo.order;

import com.bitcamp.onemoaproject.vo.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Order {

    private int no;
    private Member buyer;
    private Date createdDate;
    private String payment;
    private Date paymentDate;
    private String status;

}
