package coding_test;
/**
 * 1차 특강 모의고사 1번 문제 - 문자열 내 p와 y의 개수
 */
public class exam06 {

    boolean solution(String s) {
        boolean answer = true;
        int countP = 0;
        int countY = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'P' || c == 'p') countP++;
            if (c == 'Y' || c == 'y') countY++;
        }

        if (countP == countY) answer = true;
        else answer = countP == 0 && countY == 0;

        return answer;
    }

}
