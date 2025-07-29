package ch07.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Sample {
    public static void main(String[] args) {
        int[] data = {5,6,4,2,3,1,1,2,2,4,8};

        // 짝수만
        ArrayList<Integer> dataList = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            if (data[i] % 2 == 0) {
                dataList.add(data[i]);
            }
        }

        // 중복을 제거
        HashSet<Integer> dataSet = new HashSet<>(dataList);

        // 셋을 리스트로
        ArrayList<Integer> distinctList = new ArrayList<>(dataSet);

        // 역순으정렬정렬
        distinctList.sort(Comparator.reverseOrder());

        // 리슽틀릿정릿를 정수 배로로로배솬솬변여
        int[] result = new int[distinctList.size()];
        for(int i=0; i<distinctList.size(); i++) {
            result[i] = distinctList.get(i);
        }

        int[] data2 = {5,6,4,2,3,1,1,2,2,4,8};
        int[] result2 = Arrays.stream(data)
            .boxed()
            .filter((a) -> a%2 == 0)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
