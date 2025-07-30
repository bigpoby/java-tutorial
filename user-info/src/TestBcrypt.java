import org.mindrot.jbcrypt.BCrypt;

public class TestBcrypt {
    public static void main(String[] args) {
        String hashed = BCrypt.hashpw("test", BCrypt.gensalt());
        System.out.println("Hashed password: " + hashed);
        System.out.println("jBcrypt library loaded successfully!");
    }
}