package coding_test;

/**
 * 1차 특강 모의고사 3번 문제 - 공원 산책
 */
public class exam08 {

    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};

        int h = park.length;
        int w = park[0].length();

        int x = 0;
        int y = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (park[i].charAt(j) == 'S') {
                    x = i;
                    y = j;
                }
            }
        }

        for (String s : routes) {
            String[] arr = s.split(" ");

            String dir = arr[0];
            int distance = Integer.parseInt(arr[1]);
            boolean move = true;

            int xx = x;
            int yy = y;

            for (int i = 0; i < distance; i++) {
                switch (dir) {
                    case "N":
                        xx--;
                        break;

                    case "S":
                        xx++;
                        break;

                    case "W":
                        yy--;
                        break;

                    case "E":
                        yy++;
                        break;
                }

                if (xx < 0 || xx >= h || yy < 0 || yy >= w || park[xx].charAt(yy) == 'X') {
                    move = false;
                    break;
                }
            }

            if (move) {
                x = xx;
                y = yy;
            }
        }

        answer = new int[]{x, y};

        return answer;
    }

}
