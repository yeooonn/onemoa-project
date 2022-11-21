package com.bitcamp.onemoaproject.vo.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderReviewAttachedFile {

    private int no;
    private String filename;
    private String filepath;
    private int prNo;

    public OrderReviewAttachedFile() {
    }

    public OrderReviewAttachedFile(String filename, String filepath) {
        this.filename = filename; this.filepath = filepath;}

    public OrderReviewAttachedFile(String filepath) {
        this.filepath = filepath;
    }
}