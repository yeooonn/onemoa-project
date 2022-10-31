package com.bitcamp.onemoaproject.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.onemoaproject.vo.Notice;

@Mapper
public interface NoticeDao {

  int insert(Notice board);

  Notice findByNo(int no);

  int update(Notice notice);

  int delete(int no);

  //  int deleteByMember(int memberNo);

  List<Notice> findAll();

  // int insertFiles(Notice notice);

  //  AttachedFile findFileByNo(int fileNo);

  //  List<AttachedFile> findFilesByBoard(int boardNo);

  //  int deleteFile(int fileNo);
  //
  //  int deleteFiles(int boardNo);
  //
  //  int deleteFilesByMemberBoards(int memberNo);

}














