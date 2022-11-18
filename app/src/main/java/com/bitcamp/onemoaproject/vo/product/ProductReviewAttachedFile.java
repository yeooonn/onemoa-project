package com.bitcamp.onemoaproject.vo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductReviewAttachedFile {

    private int no;
    private String filename;
    private String filepath;
    private int reviewNo;

    public ProductReviewAttachedFile() {}

    public ProductReviewAttachedFile(String filename, String filepath) { this.filename = filename; this.filepath = filepath;}

    public ProductReviewAttachedFile(String filepath) {
        this.filepath = filepath;
    }

}