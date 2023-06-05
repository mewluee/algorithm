package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HelloFineDust17144 {


    static int[] dx = {-1, 0, 1, 0}; //상,좌,하,우
    static int[] dy = {0, -1, 0, 1};

    static int R;
    static int C;
    static int T;

    static int[][] map;
    static int[][] map2;
    //큐.....사용해보기...ㅇ... > 내 머리는 쉬워지지만...
    //그렇지만 배열이..시간이랑 공간을 적게 먹는다....

    static int[] airCleanerPos = new int[2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputFirstStr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        R = inputFirstStr[0];
        C = inputFirstStr[1];
        T = inputFirstStr[2];

        map = new int[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            int[] inputMap = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int c = 1; c <= C; c++) {
                if (inputMap[c - 1] == -1 && airCleanerPos[0] == 0) {
                    airCleanerPos[0] = r;
                    airCleanerPos[1] = c;
                }
                map[r][c] = inputMap[c - 1];
            }
        }

        for (int t = 0; t < T; t++) {
            moveBigMeonji();
            airCleaning();
        }


        int result = 0;
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {

                result += map[r][c];
            }
        }

        System.out.println(result + 2);


    }

    public static void moveBigMeonji() {
        map2 = new int[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                moveMeonji(r, c);
            }
        }

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                map[r][c] += map2[r][c];
            }
        }
    }

    public static void airCleaning() {

        down(1, airCleanerPos[0], 1);
        left(1, C, 1);
        up(1, airCleanerPos[0], C);
        right(1, C, airCleanerPos[0]);

        up(airCleanerPos[0] + 1, R, 1);
        left(1, C, R);
        down(airCleanerPos[0] + 1, R, C);
        right(1, C, airCleanerPos[0] + 1);
    }


    public static void up(int sx, int ex, int y) {

        for (int x = sx + 1; x <= ex; x++) {
            if (map[x - 1][y] != -1)
                map[x - 1][y] = map[x][y];
        }
    }

    public static void down(int sx, int ex, int y) {

        for (int x = ex - 1; x >= sx; x--) {
            if (map[x + 1][y] != -1)
                map[x + 1][y] = map[x][y];
        }
    }

    public static void left(int sy, int ey, int x) {

        for (int y = sy + 1; y <= ey; y++) {
            if (map[x][y - 1] != -1)
                map[x][y - 1] = map[x][y];
        }
    }

    public static void right(int sy, int ey, int x) {

        for (int y = ey - 1; y > sy; y--) {
            map[x][y + 1] = map[x][y];
        }
        map[x][sy + 1] = 0;
    }

    public static void moveMeonji(int x, int y) {

        int count = 0;
        int babyMeonji = map[x][y] / 5;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (0 < tx && tx <= R && 0 < ty && ty <= C) {
                if (map[tx][ty] != -1) {
                    count++;
                    map2[tx][ty] = map2[tx][ty] + babyMeonji;
                }
            }

        }

        map[x][y] = map[x][y] - count * babyMeonji;

    }
}
