package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_2638 {


    static int[][] map;

    static int second = 0;

    static int N;
    static int M;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int n = 0; n < N; n++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int m = 0; m < M; m++) {
                map[n][m] = input[m];
            }
        }

        do {
            BFS();
        } while (meltingCheese());

        System.out.println(second);

    }

    //공통적인 기능 : 외부 공간에서 DFS를 돌려기
    //1번째 방법 -> DFS돌리면서 외부공기이면 -1로 표시하기, 1을 만날경우 외부공기에 다은 치즈이므로 큐에 넣기
    // -> 큐에서 꺼내면서 해당 치즈를 기준으로 상하좌우 검사해서 -1 있으면 카운트 세서 2개이상이면 녹여버리기.
    //2번째 방법 -> 외부에 있는 치즈인지 확인하고 map에 카운트 올리기
    public static void BFS() {

        Queue<Point> externalSpace = new LinkedList<>();
        externalSpace.add(Point.of(0, 0));

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[N][M];

        while (!externalSpace.isEmpty()) {

            Point p = externalSpace.poll();

            for (int i = 0; i < 4; i++) {
                int tx = p.x + dx[i];
                int ty = p.y + dy[i];

                if (0 <= tx && tx < N && 0 <= ty && ty < M) {
                    if (!visited[tx][ty]) {
                        if (map[tx][ty] >= 1) {
                            map[tx][ty] += 1;
                        } else {
                            externalSpace.add(Point.of(tx, ty));
                            visited[tx][ty] = true;
                        }
                    }
                }

            }
        }

    }

    public static boolean meltingCheese() {
        boolean flag = false;

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (map[n][m] >= 3) {
                    map[n][m] = 0;
                } else if (map[n][m] == 2) {
                    map[n][m] = 1;
                    flag = true;
                } else if (map[n][m] == 1) {
                    flag = true;
                }
            }

        }
        second++;
        return flag;
    }

    static void printMap() {

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static class Point {
        int x;
        int y;

        static Point of(int x, int y) {
            Point point = new Point();
            point.x = x;
            point.y = y;

            return point;
        }

        void print() {
            System.out.println("point[" + x + "][" + y + "]=" + map[x][y]);
        }
    }
}
