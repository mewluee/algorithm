package 동적계획법_DynamicProgramming;

import java.io.*;

public class G5_LCS_9251 {

    //문제: 골드5 9251 LCS
    //알고리즘: LCS

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String str1=br.readLine();
        String str2=br.readLine();

        int N=str1.length();
        int M=str2.length();

        int[][] dp=new int[N+1][M+1];

        for(int n=1; n<=N; n++) {
            for(int m=1; m<=M; m++) {
                //System.out.println(str1.charAt(n-1)+" ? "+str2.charAt(m-1));
                if(str1.charAt(n-1)==str2.charAt(m-1)) dp[n][m]=dp[n-1][m-1]+1;
                else dp[n][m]=Math.max(dp[n-1][m], dp[n][m-1]);
            }

        }
        System.out.println(dp[N][M]);

    }
}
