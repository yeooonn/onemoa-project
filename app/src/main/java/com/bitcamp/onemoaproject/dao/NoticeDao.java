package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.paging.Criteria;
import java.util.List;
import java.util.Map;
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
  
  // 공지사항 페이징
  List<Map<String, Object>> findAllList(Criteria cri);
  
  // 공지사항의 글의 개수 조회
  int findAllCount();

  // int insertFiles(Notice notice);

  //  AttachedFile findFileByNo(int fileNo);

  //  List<AttachedFile> findFilesByBoard(int boardNo);

  //  int deleteFile(int fileNo);
  //
  //  int deleteFiles(int boardNo);
  //
  //  int deleteFilesByMemberBoards(int memberNo);

}














