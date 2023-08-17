package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 정수삼각형_1932 {
    static int N;
    static int max;
    static int value;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        max = 0;
        value = 0;
        for (int n = 1; n < N + 1; n++) {
            Arrays.fill(map[n], -1);
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int m = 1; m < input.length + 1; m++) {
                map[n][m] = input[m - 1];
            }
        }

        //dfs(1, 1);
        dp();
        max=Arrays.stream(dp[N]).max().getAsInt();
        System.out.println(max);
    }

    //시간초과
    static void dfs(int floor, int space) {
        //System.out.println("------------------");
        //System.out.println("floor:"+floor+", space:"+space);
        //System.out.println("value:"+value);
        if (floor > N) {
            if (max < value) {
                max = value;
            }
            return;
        }
        for (int i = space; i <= space + 1; i++) {
            if(i<=N && map[floor][i]!=-1){
                //System.out.println("space:"+space);
                //System.out.println(map[floor][i]);
                value += map[floor][i];
                dfs(floor + 1, i);
                value -= map[floor][i];
            }
        }
    }

    static void dp() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (j == i) { // 맨 오른쪽
                    dp[i][j] = map[i][j] + dp[i - 1][j - 1];
                } else if (j == 1) { // 맨 왼쪽
                    dp[i][j] = map[i][j] + dp[i - 1][j];
                } else { // 중간
                    dp[i][j] = Math.max(map[i][j] + dp[i - 1][j - 1], map[i][j] + dp[i - 1][j]);
                }
            }
        }
    }
}
