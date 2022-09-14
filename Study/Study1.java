import java.util.Scanner;

public class Study {
  public static void main(String[] args) {
    // ID : admin, PW : 1234 를 입력하면 '관리자님 환영합니다.'라고 출력하며 게시판 활성화
    String ID, AdminID = "admin";
    String PW, AdminPW = "1234";
    int WrongCount = 0;
    Scanner sc = new Scanner(System.in);

    while(true) {
      if(WrongCount < 5) { // 오류 카운트가 5보다 작으면
        System.out.printf("아이디를 입력하세요 : ");
        ID = sc.nextLine();
        if(ID.equals(AdminID)) { // 입력한 ID와 관리자 ID를 비교
          // ID = AdminID일시 PW 입력으로 넘어감
          System.out.printf("비밀번호를 입력하세요 : ");
          PW = sc.nextLine(); // PW입력
          if(PW.equals(AdminPW)) { // 입력한 PW와 관리자 PW를 비교
            System.out.printf("관리자님 환영합니다.\n");
            break;
          } else if(!PW.equals(AdminPW)) { // 입력한 PW와 관리자 PW가 다르면 처음으로
            System.out.printf("비밀번호가 틀렸습니다.\n");
            WrongCount++; // 오류 카운트 +1
          }
        } else if(!ID.equals(AdminID)) { // 입력한 ID와 관리자 ID가 다르면 처음으로
          System.out.printf("아이디가 틀렸습니다.\n");
          WrongCount++; // 오류 카운트 +1
        }
      } else if(WrongCount >= 5){ // 오류 카운트가 5 이상이면
        System.out.printf("입력 오류가 5번 누적됐습니다.\n다음에 다시 이용해주세요.\n");
        break;
      }
    }
    sc.close();
  }

  public static void displayHeadline() {
    System.out.println("------------------------");
  }
  public static void displayBlankLine() {
    System.out.println();
  }

  public static void welcome() {
    displayHeadline();
    System.out.println("[게시판 애플리케이션]");
    displayBlankLine();
    System.out.println("환영합니다!");
    displayBlankLine();
    displayHeadline();
  }
}
