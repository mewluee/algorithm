package 구현_implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마법사상어와파이어볼_20056 {

    static int N;
    static int M;
    static int K;
    static ArrayList<FireBall> ballLists;
    static ArrayList<FireBall>[][] map;

    static int[][] directions = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {0, 0}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //초기화
        map = new ArrayList[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        ballLists = new ArrayList<>();

        //입력 M 줄
        for (int m = 0; m < M; m++) {
            int[] nums = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            // 0 r , 1 c , 2 m , 3 s , 4 d
            FireBall ball = new FireBall(nums[0], nums[1], nums[2], nums[3], nums[4]);
            map[nums[0]][nums[1]].add(ball);
            ballLists.add(ball);
        }

        for (int k = 0; k < K; k++) {
            moveFireBall();
            fixFireBall();
        }

        int result = 0;
        for (int i = 0; i < ballLists.size(); i++) {
            result += ballLists.get(i).m;
        }
        System.out.println(result);
    }

    static void moveFireBall() {

        for (int i = 0; i < ballLists.size(); i++) {

            FireBall ball = ballLists.get(i);
            map[ball.x][ball.y].remove(ball);

            int dx = (ball.x + directions[ball.d][0] * ball.s) % N;
            if (dx == 0) dx = N;
            if (dx < 0) dx += N;
            int dy = (ball.y + directions[ball.d][1] * ball.s) % N;
            if (dy == 0) dy = N;
            if (dy < 0) dy += N;
            //[중요] 이동하는 위치로 바꿔주기.
            ball.x=dx;
            ball.y=dy;
            map[dx][dy].add(ball);
        }

    }

    static void fixFireBall() {
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= N; m++) {
                int ballCount = map[n][m].size();
                if (ballCount > 1) { // 파이어볼이 2개 이상일 경우
                    int all_m = 0;
                    int all_s = 0;
                    int count_odd_d = 0; //홀수 개수 세기

                    for (int i = 0; i < ballCount; i++) {
                        FireBall ball = map[n][m].get(i);
                        all_m += ball.m;
                        all_s += ball.s;
                        if (ball.d % 2 == 1) count_odd_d++;
                        ballLists.remove(ball);
                    }

                    map[n][m].clear();

                    int divided_m = all_m / 5;
                    if (divided_m == 0) continue; // 질량이 0이면 패스
                    int divided_s = all_s / ballCount;
                    int[] divided_d;
                    if (count_odd_d == 0 || count_odd_d == ballCount) //방향이 모두 짝수 or 모두 홀수
                        divided_d = new int[]{0, 2, 4, 6};
                    else
                        divided_d = new int[]{1, 3, 5, 7};

                    for (int i = 0; i < 4; i++) {
                        FireBall new_ball = new FireBall(n, m, divided_m, divided_s, divided_d[i]);
                        map[n][m].add(new_ball);
                        ballLists.add(new_ball);
                    }
                }
            }
        }
    }

    static class FireBall {
        int x;
        int y;
        int m;
        int s;
        int d;

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }
}
