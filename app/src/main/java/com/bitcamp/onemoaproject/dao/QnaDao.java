package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.QnaAttachedFile;
import com.bitcamp.onemoaproject.vo.Qna;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaDao {

    int insert(Qna qna);

    Qna findByNo(int no);

    int update(Qna qna);

    boolean answerUpdate(Qna qna);

    int delete(int no);

    List<Qna> findAll();

    int insertFiles(Qna qna);

   QnaAttachedFile findFileByNo(int fileNo);

   List<QnaAttachedFile> findFilesByQna(int qnaNo);

  int deleteFile(int fileNo);

  int deleteFiles(int qnaNo);

//  int deleteFilesByMemberBoards(int memberNo);

}














