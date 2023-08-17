package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 파도반수열_9461 {

    //1 2 3 4 5 6 7 8 9 10
    //1 1 1 2 2 3 4 5 7 9
    //1=1
    //2=1
    //3=1
    //4=1+3
    //5=2
    //6=5+1
    //7=6+2
    //8=7+3
    //9=8+4
    //10=9+5
    //N=(N-1)+(N-5)

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        long[] P=new long[101];
        P[1]=1;
        P[2]=1;
        P[3]=1;
        P[4]=2;
        P[5]=2;
        cal(P);

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(P[N]);
        }
    }

    static void cal(long[] P){
        for (int i = 6; i <=100 ; i++) {
            P[i]=P[i-1]+P[i-5];
        }
    }
}
