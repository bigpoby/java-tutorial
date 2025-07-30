import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class CheckPwBcrypt {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("비밀번호 입력: ");
        String pw = scanner.nextLine();

        System.out.println("해시코드 입력: ");
        String hashed = scanner.nextLine();
        
        System.out.println(BCrypt.checkpw(pw, hashed));
    }
    
}
