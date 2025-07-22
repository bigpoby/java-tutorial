
import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("수학", 90);
        scores.put("영어", 85);
        scores.put("과학", 95);

        System.out.println("영어 점수: " + scores.get("영어"));
        scores.remove("과학");
        System.out.println("전체 과목: " + scores.keySet());
    }
}
