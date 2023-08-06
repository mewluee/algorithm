package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일링2xn2_11727 {
    static int count;
    static long result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int dp[] = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        cal(dp, N);
        System.out.println(dp[N]);
    }

    static void cal(int[] dp, int N) {
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 2] * 2 + dp[i - 1])%10007;
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        count = 0;
        result = 0;
        combi(N);
        System.out.println(result % 10007);
    }

    static void combi(int N) {
        System.out.println("N:" + N + ", count:" + count);
        if (N == 0) {
            if (count == 0) result += 1;
            else result += count * 2;
            System.out.println("result:" + result);
            return;
        } else if (N < 0) return;

        for (int i = 1; i <= 2; i++) {
            if (i == 2) {
                System.out.println("2 들어감");
                count++;
                combi(N - 2);
                count--;
            } else {
                combi(N - 1);
            }
        }
    }
}
