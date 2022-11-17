package com.bitcamp.onemoaproject.vo.paging;

public class Criteria {

    private String code;

    private int page;
    private int perPageNum;
    private String categoryCode;

    public int getPageStart() {
        return (this.page-1)*perPageNum;
    }

    public Criteria() {
        this.page = 1;
        this.perPageNum = 16;
        this.categoryCode = null;
    }
    
    public Criteria(int page, int perPageNum) {
        this.page = page;
        this.perPageNum = perPageNum;
    }
    
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    
    // 게시판별로 한페이지에 보여줄 개수 정하기위해서 주석처리
    //    public void setPerPageNum(int pageCount) {
//        int cnt = this.perPageNum;
//        if(pageCount != cnt) {
//            this.perPageNum = cnt;
//        } else {
//            this.perPageNum = pageCount;
//        }
//    }
    // 게시판별로 한페이지에 보여줄 개수 정하기위해서 설정
    public void setPerPageNum(int pageCount) {
        this.perPageNum = pageCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "code='" + code + '\'' +
                ", page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}

