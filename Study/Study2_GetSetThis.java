
/* Get, Set, This를 이용한 프로그래밍
 * 1. Get과 Set은 함수가 아니다.
 *    -> 보기 편하라도 변수 이름을 Get,Set으로 만드는 것이지,
 *       Get,Set을 붙인다고 문법이 활성화 되진 않는다.
 *
 * 2. Set은 변수 값을 지정하는 메소드
 *    Get은 Set이 지정한 변수 값을 가져오는 메소드
 * */

public class Study2_GetSetThis {

  // 1. 스태틱 클래스를 가장 먼저 실행.
  public static void main(String[] args) {
    // 2. Name 객체 인스턴스 변수를 생성
    Name name = new Name("홍길동","임꺽정");
    // 2-1. 객체 인스턴스 변수 생성.
    //      파라미터에서 지정한 값을 Name 메소드에 넣어줘야 함.


    // 5. 출력
    System.out.println(name.getMyName() + name.getYourName());
    // 5-1. getMyName과 GetYourName 메소드가 무엇인지 확인.
    // 6-2. 가져온 값 출력
  }
}

class Name {
  String myName;
  String yourName;

  // 3. Name 메소드에 2개의 파라미터 값 학인.
  //    그런데 변수로 선언된 myName과 yourName은 어디서 확인할 수 있지?
  public Name(String myName, String yourName) {
    // 3-1. setName에서 확인할 수 있음.
    setName(myName, yourName);
  }
  public String getMyName() {
    // 6. "홍길동" = public Name(String myName) = class Name의 String myName
    //     을 저장하는 Get메소드
    return myName;
  }
  public String getYourName() {
    // 6. "임꺽정" = public Name(String yourName) = class Name의 String yourName
    //     을 저장하는 Get메소드
    return yourName;
  }
  // 4. 여기가 setName 메소드이고 Name 메소드처럼 파라미터 값이 똑같이 2개.
  public void setName(String myName, String yourName) {
    // 4-1. Class Name에서 지정한 myName과 
    //      pucliC Name메소드에서 지정한 myName을 동일화
    this.myName = myName;
    this.yourName = yourName;
    // 4-2. class내부의 메소드에서 사용하기 때문에
    //      class Name의 myName을 this.를 통해 가져옴.
    //      이것으로 "홍길동" = public Name(String myName) = class Name의 String myName
  }
}
