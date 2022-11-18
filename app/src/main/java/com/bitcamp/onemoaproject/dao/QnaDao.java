package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.qna.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.qna.Qna;
import com.bitcamp.onemoaproject.vo.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaDao {

    int insert(Qna qna);

    Qna findByNo(int no);

    int update(Qna qna);

    boolean answerUpdate(Qna qna);

    int delete(int no);

    List<Qna> findAll();

    List<Qna> findAll(int no);

    int insertFiles(Qna qna);

    QnaAttachedFile findFileByNo(int fileNo);

    List<QnaAttachedFile> findFilesByQna(int qnaNo);

    int deleteFile(int fileNo);

    int deleteFiles(int qnaNo);

    // Qna 페이징
    List<Map<String, Object>> findAllList(Criteria cri);

    List<Map<String, Object>> findAllList(Criteria cri, int no);
    // Qna 글의 개수 조회
    int findAllCount();

    int findAllCount(int no);


//  int deleteFilesByMemberBoards(int memberNo);

}














