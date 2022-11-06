package com.bitcamp.onemoaproject.dao;

import com.bitcamp.onemoaproject.vo.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqDao {
//
//  int insert(Board board);
//
    Faq findByNo(int no);
//
//  int update(Board board);
//
//  int delete(int no);
//
//  int deleteByMember(int memberNo);
//
     List<Faq> findAll();
//
//  int insertFiles(Board board);
//
//  AttachedFile findFileByNo(int fileNo);
//
//  List<AttachedFile> findFilesByBoard(int boardNo);
//
//  int deleteFile(int fileNo);
//
//  int deleteFiles(int boardNo);
//
//  int deleteFilesByMemberBoards(int memberNo);
}














