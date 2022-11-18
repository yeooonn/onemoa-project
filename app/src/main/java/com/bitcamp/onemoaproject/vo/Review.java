package com.bitcamp.onemoaproject.vo;

import com.bitcamp.onemoaproject.vo.contest.ContestAttachedFile;
import com.bitcamp.onemoaproject.vo.contest.ContestCategory;
import com.bitcamp.onemoaproject.vo.contest.ContestOrg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Review {

    private int no;
    private String title;
    private String content;
    private Date cdt;
    private Member writer;
    private int score;
    private int poNo;

    // 첨부파일을 저장할 필드
    private List<ContestAttachedFile> contestAttachedFiles;
}
