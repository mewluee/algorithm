package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G4_가장긴바이노틱부분수열_11054 {

    //문제:골드4 11054 가장 긴 바이토닉 부분 수열
    //요약:1<2<3<5>3>1 이런 수열 만들기
    //풀이:앞으로 LCS구하고 뒤에서 LCS구해서 두개 값을 더했을때 제일 큰 값을 출력하면 된다.
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] dp = new int[N][2]; //인덱스 0:증가 1:감소 개수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (nums[j] < nums[i]) dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
            }
        }
        int result = 0;
        for (int n = 0; n < N; n++) {
            int count = dp[n][0] + dp[n][1] + 1;
            if (count > result) result = count;
        }
        //스트림으로 표현하는 방법 - chatgpt
        int result2 = Arrays.stream(dp)
                .mapToInt(row -> row[0] + row[1] + 1)
                .max()
                .orElse(0);
        int result3 = Arrays.stream(dp)
                .map(row -> row[0] + row[1] + 1)
                .max(Integer::compare)
                .orElse(0);
        System.out.println(result3);
    }
}
