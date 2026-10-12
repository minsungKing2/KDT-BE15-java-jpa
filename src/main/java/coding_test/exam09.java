package coding_test;
import java.util.ArrayList;
/**
 * 1차 특강 모의고사 4번 문제 - 기능 개발
 */
public class exam09 {

    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;

        int[] days = new int[n];

        for (int i = 0; i < n; i++) {
            int remain = 100 - progresses[i];
            days[i] = (remain + speeds[i] - 1) / speeds[i];
        }

        ArrayList<Integer> result = new ArrayList<>();

        int currentDay = days[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (days[i] <= currentDay) {
                count++;
            } else {
                result.add(count);
                currentDay = days[i];
                count = 1;
            }
        }

        result.add(count);

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

}
