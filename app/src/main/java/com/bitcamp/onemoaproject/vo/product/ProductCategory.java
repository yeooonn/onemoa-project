package com.bitcamp.onemoaproject.vo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductCategory {

    private String code;
    private int tier;
    private String name;
    private String parent;

    private List<ProductCategory> children;

}
