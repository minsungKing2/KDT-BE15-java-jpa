package coding_test;
/**
 * 1차 특강 - 그림 확대
 */
public class exam03 {

    public String[] solution(String[] picture, int k) {
        int len = picture.length * k;
        String[] answer = new String[len];

        int idx = 0;

        for (int i = 0; i < picture.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < picture[i].length(); j++) {
                for (int m = 0; m < k; m++) {
                    sb.append(picture[i].charAt(j));
                }
            }

            for (int n = 0; n < k; n++) {
                answer[idx++] = sb.toString();
            }
        }


        return answer;
    }

}
