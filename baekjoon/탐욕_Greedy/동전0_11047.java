package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동전0_11047 {

    static int N;
    static int K;
    static int[] coins;
    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputStr = br.readLine().split(" ");
        N = Integer.parseInt(inputStr[0]);
        K = Integer.parseInt(inputStr[1]);

        coins = new int[N];
        result=0;

        for (int n = 0; n < N; n++) {
            coins[n] = Integer.parseInt(br.readLine());
        }

        for (int n = N-1; n >=0; n--) {
            if(K>=coins[n]){
                result+=K/coins[n];
                K=K%coins[n];
            }
        }
        System.out.println(result);
    }
}
