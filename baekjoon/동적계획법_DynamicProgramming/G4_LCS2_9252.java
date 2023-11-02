package 동적계획법_DynamicProgramming;

import java.io.*;

public class G4_LCS2_9252 {

    //문제: 골드4 9252 LCS2
    //알고리즘: LCS

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String str1=br.readLine();
        String str2=br.readLine();
        StringBuffer sb=new StringBuffer();
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

        int n=N, m=M;

        while(n>0 && m>0) {
            //System.out.println("["+n+", "+m+"]");
            //System.out.println(str1.charAt(n-1)+" ? "+str2.charAt(m-1));
            if(str1.charAt(n-1)==str2.charAt(m-1)) {
                sb.append(str1.charAt(n-1));
                n--;
                m--;
                //System.out.println(result);
            }else {
                if(dp[n-1][m] >= dp[n][m-1]) n--;
                else if(dp[n][m-1] > dp[n-1][m]) m--;
            }
        }
        System.out.println(sb.reverse().toString());

    }
}
