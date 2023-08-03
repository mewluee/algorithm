package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 미로탐색_2178 {

    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = inputs[0];
        M = inputs[1];
        map = new int[N + 1][M + 1];

        for (int n = 1; n < N + 1; n++) {
            String str = br.readLine();
            for (int m = 1; m < M + 1; m++) {
                map[n][m] = Character.getNumericValue(str.charAt(m - 1));
            }
        }

        BFS(new Point(1, 1));
        System.out.println(map[N][M]);
    }


    static void BFS(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[start.x][start.y] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int tx = now.x + dx[i];
                int ty = now.y + dy[i];

                if (1 <= tx && tx <= N && 1 <= ty && ty <= M) {
                    if (map[tx][ty] >= 1 && !visited[tx][ty]) {
                        queue.add(new Point(tx, ty));
                        visited[tx][ty] = true;
                        map[tx][ty] = map[now.x][now.y] + 1;
                    }
                }
            }
        }

    }


    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }

}
