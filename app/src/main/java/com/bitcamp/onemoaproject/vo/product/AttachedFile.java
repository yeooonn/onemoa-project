package com.bitcamp.onemoaproject.vo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachedFile {

    private int no;
    private String filename;
    private String filepath;
    private int productNo;

    public AttachedFile() {}

    public AttachedFile(String filename, String filepath) { this.filename = filename; this.filepath = filepath;}

    public AttachedFile(String filepath) {
        this.filepath = filepath;
    }

}