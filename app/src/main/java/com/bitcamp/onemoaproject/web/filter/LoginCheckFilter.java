package com.bitcamp.onemoaproject.web.filter;

import com.bitcamp.onemoaproject.vo.Member;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class LoginCheckFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("LoginCheckFilter.init() 실행");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    System.out.println("LoginCheckFilter.doFilter() 실행!");
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    // 응답 기능에 대해서도 HTTP 관련 메서드를 사용하고 싶다면 형변환 하라!
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    //    String getServletPath = httpRequest.getServletPath();
    //    System.out.println("getServletPath : " + getServletPath);
    //String servletPath = httpRequest.getPathInfo();
    //System.out.println("servletPath : " + servletPath);

    String servletPath = httpRequest.getServletPath();
    System.out.println("httpRequest.getContextPath()" + httpRequest.getContextPath());
    System.out.println("httpRequest.getServletPath()" + httpRequest.getServletPath());

    // 콘텐트를 틍록, 변경, 삭제하는 경우 로그인 여부를 확인한다.
    if (servletPath.toLowerCase().endsWith("add") || servletPath.toLowerCase().endsWith("update")
        || servletPath.toLowerCase().endsWith("delete")) {

      Member loginMember = (Member) httpRequest.getSession().getAttribute("loginMember");
      if (loginMember == null) { // 로그인 하지 않았다
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/onemoa/");
        return;
        //getContextPath 웹애플리케이션인 경우 애플리케이션 이름이 바뀌면 자동으로 변경된다.
      }
    }

    // 현재 필터, 다음에 실행할 필터를 지정한다.
    // 다음 필터를 실행한다.
    // 다음으로 실행할 필터가 없다면 원래 목적지인 서블릿이 실행될 것이다.
    chain.doFilter(request, response);
  }
}
