package com.bitcamp.onemoaproject.vo.product;

import java.util.List;

public class ProductCategory {

    private String code;
    private int tier;
    private String name;
    private String parent;

    private List<ProductCategory> children;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "code='" + code + '\'' +
                ", tier=" + tier +
                ", name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", children=" + children +
                '}';
    }

    public String getCode() { return code;}

    public void setCode(String code) {this.code = code;}

    public int getTier() {return tier;}

    public void setTier(int tier) {this.tier = tier;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getParent() {return parent;}

    public void setParent(String parent) {this.parent = parent;}

    public List<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategory> children) {
        this.children = children;
    }
}
