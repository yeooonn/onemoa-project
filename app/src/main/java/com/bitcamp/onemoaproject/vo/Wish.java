package com.bitcamp.onemoaproject.vo;

import com.bitcamp.onemoaproject.vo.product.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Wish {
    private Member member;
    private Product product;
}
