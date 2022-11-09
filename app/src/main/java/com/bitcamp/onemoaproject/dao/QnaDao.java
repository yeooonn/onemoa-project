package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.AttachedFile;
import com.bitcamp.onemoaproject.vo.Qna;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaDao {

    int insert(Qna qna);

    Qna findByNo(int no);

    int update(Qna qna);

    int delete(int no);

//  int deleteByMember(int memberNo);

     List<Qna> findAll();

    int insertFiles(Qna qna);

   AttachedFile findFileByNo(int fileNo);

   List<AttachedFile> findFilesByQna(int qnaNo);

  int deleteFile(int fileNo);

  int deleteFiles(int qnaNo);

  int deleteFilesByMemberBoards(int memberNo);
}














