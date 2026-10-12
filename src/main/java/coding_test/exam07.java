package coding_test;
import java.util.ArrayList;
/**
 * 1차 특강 모의고사 2번 문제 - 같은 숫자는 싫어
 */
public class exam07 {

    public int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : arr) {
            if (list.isEmpty() || list.get(list.size() - 1) != i) {
                list.add(i);
            }
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;
    }

}
