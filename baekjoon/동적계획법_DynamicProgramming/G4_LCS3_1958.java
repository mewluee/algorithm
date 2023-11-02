package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_LCS3_1958 {
    //문제: 골드4 1958 LCS3
    //알고리즘: LCS

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str1=br.readLine();
        String str2=br.readLine();
        String str3=br.readLine();

        int N=str1.length();
        int M=str2.length();
        int P=str3.length();

        int[][][] dp=new int[N+1][M+1][P+1];

        for(int n=1; n<=N; n++) {
            for(int m=1; m<=M; m++) {
                for(int p=1; p<=P; p++) {
                    if(str1.charAt(n-1)==str2.charAt(m-1) && str2.charAt(m-1)==str3.charAt(p-1)) dp[n][m][p]=dp[n-1][m-1][p-1]+1;
                    else {
                        int max=Math.max(dp[n-1][m][p], dp[n][m-1][p]);
                        dp[n][m][p]=Math.max(max, dp[n][m][p-1]);
                    }
                }
            }

        }
        System.out.println(dp[N][M][P]);
    }
}
