package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_거스름돈_14916 {


    //문제: 14916 실버 - 거스름돈
    //적용한 알고리즘: 그리디, dp
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int[] dp=new int[100001];

        dp[1]=-1;
        dp[2]=1;
        dp[3]=-1;
        dp[4]=2;
        dp[5]=1;

        final int max=987654321;
        for(int i=6; i<100001; i++) {
            dp[i]=Math.min(dp[i-2]==-1? max: dp[i-2]+1, dp[i-5]==-1? max: dp[i-5]+1);
        }

        //System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }

    //틀림
    public void solve1(int n) {
        int[][] moneys=new int[][] {{5, 0},{2, 0}}; //5, 2원 0개씩
        int start=0;
        while(n>=2) {
            moneys[start][1]=n/moneys[start][0]; //동전 몇개 들어가는지
            n=n%moneys[start][0]; //남은 금액
            start++;
        }
        if(n>0) System.out.println(-1);
        else System.out.println(moneys[0][1]+moneys[1][1]);
    }

}
