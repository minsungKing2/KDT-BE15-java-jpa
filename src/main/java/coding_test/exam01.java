package coding_test;
/**
 * 1차 특강 - 배열의 유사도 문제
 */
public class exam01 {

    public int solution(String[] s1, String[] s2) {
        int answer = 0;

        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                if (s1[i].equals(s2[j])) answer++;
            }
        }

        return answer;
    }

}
