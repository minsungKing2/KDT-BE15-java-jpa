package coding_test;
/**
 * 1차 특강 - 카펫
 */
public class exam05 {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow; // 10 + 2

        for (int height = 3; height <= total; height++) {
            if (total % height == 0) {
                int width = total / height;

                if ((width - 2) * (height - 2) == yellow) {
                    answer[0] = width;
                    answer[1] = height;
                    break;
                }
            }
        }
        return answer;
    }

}
