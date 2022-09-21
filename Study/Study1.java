import java.util.Scanner;

public class Study {
  public static void main(String[] args) {
    String ID, AdminID = "admin";  // 사용자가 입력하는 ID와 처음부터 지정된 관리자 ID 변수
    String PW, AdminPW = "1234"; // 사용자가 입력하는 PW와 처음부터 지정된 관리자 PW 변수
    int WrongCount = 0; // 에러 카운트를 저장하는 변수
    boolean Start = false; // while문을 멈추는 변수
    Scanner sc = new Scanner(System.in); // 스캐너 변수 이름은 sc

    while(Start == true) { // while(true)로 계속해서 돌아가도록 설정

    // while문 내부에 if문을 사용하여 사용자가 관리자 ID,PW를 전부 입력하면 sysout:"로그인 성공"이 뜨도록 설정
    // 반드시 ID입력이 성공해야지 PW입력으로 넘어가는 조건이 되어야 함!!!!!
    // ID가 틀리면 sysout"ID가 틀렸습니다.", PW가 틀리면 sysout"PW가 틀렸습니다." 출력
    // 에러 카운트가 ID, PW 입력 중 총합 3번이 넘어가면 sysout:"오류가%d번 누적됐습니다. 다음에 이용해주세요"가 뜨도록 설정
    // 브레이크 조건은 boolean값인 Start가 false. break;은 ID,PW입력이 전부 끝났을 때만
    }
    sc.close();
  }
}
