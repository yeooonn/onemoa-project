package com.bitcamp.onemoaproject.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageAttachedFile {

    private int msgFno;
    private String filename;
    private String filepath;
    private int msgNo;

    public MessageAttachedFile() {}

    public MessageAttachedFile(String filename, String filepath) { this.filename = filename; this.filepath = filepath;}

    public MessageAttachedFile(String filepath) {
        this.filepath = filepath;
    }

}