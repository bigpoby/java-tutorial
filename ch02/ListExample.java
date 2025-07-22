import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("사과");
        fruits.add("바나나");
        fruits.add("딸기");
        
        System.out.println("첫 번째 과일: " + fruits.get(0));
        fruits.remove("바나나");
        System.out.println("과일 목록: " + fruits);
    }
}
