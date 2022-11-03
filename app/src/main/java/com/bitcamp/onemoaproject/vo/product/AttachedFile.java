package com.bitcamp.onemoaproject.vo.product;

public class AttachedFile {

    private int no;
    private String filename;
    private String filepath;
    private int productNo;

    public AttachedFile() {}

    public AttachedFile(String filename, String originname) { this.filepath = filename; this.filename = originname;}

    public AttachedFile(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "AttachedFile{" +
                "no=" + no +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", productNo=" + productNo +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}