
import java.util.HashSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        Set<String> fruits = new HashSet<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("사과");
        fruits.add("사과");
        fruits.add("사과");
        fruits.add("사과");

        System.out.println(fruits);
    }
}
