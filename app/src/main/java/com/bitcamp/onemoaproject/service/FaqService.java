package com.bitcamp.onemoaproject.service;

import com.bitcamp.onemoaproject.vo.Faq;

import java.util.List;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
public interface FaqService {

    void add(Faq faq) throws Exception;

    boolean update(Faq faq) throws Exception;

    Faq get(int no) throws Exception;

    boolean delete(int no) throws Exception;

    List<Faq> list() throws Exception;
}








