package com.bitcamp.onemoaproject;

import com.bitcamp.onemoaproject.service.MemberService;
import com.bitcamp.onemoaproject.service.productService.ProductReviewService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.onemoaproject.service.ContestService;
import com.bitcamp.onemoaproject.vo.Member;
import com.bitcamp.onemoaproject.vo.contest.Contest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
@EnableTransactionManagement
@SpringBootApplication

public class App {

  @Autowired
  ContestService contestService;
  
  @Autowired
  ProductReviewService productReviewService;
  
  @Autowired
  MemberService memberService;

  public static void main(String[] args) {
    System.out.println("비트캠프 프로젝트!");
    SpringApplication.run(App.class, args);
  }

  @GetMapping("/managePage")
  public String managePage() {
    return "managePage";
  }

  @GetMapping("/")
  public String welcome(@CookieValue(name = "email", defaultValue = "") String email, Model model) {
    model.addAttribute("email", email);
    model.addAttribute("contests", contestService.listMain());
    model.addAttribute("reviews", productReviewService.listMainReview());
    System.out.println("re = " + productReviewService.listMainReview());
    return "index";
  }
  
  @GetMapping("young")
  public void young() {
  }

  @GetMapping("test")
  public void test() {
  }

//  @PostMapping("test1")
//  @ResponseBody
//  public Map test1() {
//    Member member = new Member();
//    member.setNo(1);
//    member.setNickname("test");
//
//    Contest contest = new Contest();
//    contest.setCtstNo(5);
//    contest.setTitle("TESTETSETTET");
//    Map<String, Object> map = new HashMap();
//    map.put("member", member);
//    map.put("contest", contest);
//
//    return map;
//  }
//
//  @RequestMapping("snsKakologin")
//  public String snsKakologin(String code, HttpServletRequest request) throws Exception {
//    System.out.println("#########" + code);
//    String access_Token = getAccessToken(code);
//    HashMap<String, Object> userInfo = getUserInfo(access_Token);
//    System.out.println("###access_Token#### : " + access_Token);
//    System.out.println("###userInfo#### : " + userInfo.get("email"));
//    System.out.println("###nickname#### : " + userInfo.get("nickname"));
//    System.out.println("###profile_image#### : " + userInfo.get("profile_image"));
//
//    return "snsKakologin";
//  }
//
//  public String getAccessToken (String authorize_code) {
//    String access_Token = "";
//    String refresh_Token = "";
//    String reqURL = "https://kauth.kakao.com/oauth/token";
//
//    try {
//      URL url = new URL(reqURL);
//
//      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//      //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
//
//      conn.setRequestMethod("POST");
//      conn.setDoOutput(true);
//
//      //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
//      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//      StringBuilder sb = new StringBuilder();
//      sb.append("grant_type=authorization_code");
//      sb.append("&client_id=e7ad2c8401c0bd7addc89d031b7a478d");  //본인이 발급받은 key
//      sb.append("&redirect_uri=http://localhost:3333/onemoa/snsKakologin");     // 본인이 설정해 놓은 경로
//      sb.append("&code=" + authorize_code);
//      bw.write(sb.toString());
//      bw.flush();
//
//      //    결과 코드가 200이라면 성공
//      int responseCode = conn.getResponseCode();
//      System.out.println("responseCode : " + responseCode);
//
//      //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//      String line = "";
//      String result = "";
//
//      while ((line = br.readLine()) != null) {
//        result += line;
//      }
//      System.out.println("response body : " + result);
//
//      //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//      JsonParser parser = new JsonParser();
//      JsonElement element = parser.parse(result);
//
//      access_Token = element.getAsJsonObject().get("access_token").getAsString();
//      refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
//
//      System.out.println("access_token : " + access_Token);
//      System.out.println("refresh_token : " + refresh_Token);
//
//      br.close();
//      bw.close();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    return access_Token;
//  }
//
//
//  public HashMap<String, Object> getUserInfo (String access_Token) {
//
//    //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
//    HashMap<String, Object> userInfo = new HashMap<>();
//    String reqURL = "https://kapi.kakao.com/v2/user/me";
//    try {
//      URL url = new URL(reqURL);
//      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//      conn.setRequestMethod("GET");
//
//      //    요청에 필요한 Header에 포함될 내용
//      conn.setRequestProperty("Authorization", "Bearer " + access_Token);
//
//      int responseCode = conn.getResponseCode();
//      System.out.println("getUserInfo responseCode : " + responseCode);
//
//      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//      String line = "";
//      String result = "";
//
//      while ((line = br.readLine()) != null) {
//        result += line;
//      }
//      System.out.println("getUserInfo response body : " + result);
//      //
//      //      JsonParser parser = new JsonParser();
//      //      JsonElement element = parser.parse(result);
//      //
//      //      JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//      //      JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//      //
//      //      String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//      //      String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
//      //      String email = kakao_account.getAsJsonObject().get("email").getAsString();
//
//      //      userInfo.put("getUserInfo nickname", nickname);
//      //      userInfo.put("getUserInfo email", email);
//      //      userInfo.put("getUserInfo profile_image", profile_image);
//
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    return userInfo;
//  }


}


