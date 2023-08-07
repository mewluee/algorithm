package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FourSquares_17626_실패 {
    //dp1=1
    //dp2=1+1=dp1+dp1=2
    //dp3=1+1+1=dp2+dp1=3
    //dp4=2*2=1
    //dp5=2*2+1=dp4+dp1=2
    //dp6=2*2+1+1=dp4+dp2=3
    //dp7=2*2+1+1+1=dp4+dp3=4
    //dp8=2*2+2*2=dp4+dp4=2
    //dp9=3*3=1
    //dp10=dp3+dp1=2

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //dp이면서 다이나믹 프로그래밍....흠..
        int[] arr=new int[N+1];
        arr[1]=1;
        arr[2]=2;
        arr[3]=3;
    }
    static void cal(int n, int[] arr){
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j < i; j++) {

            }
        }
    }
}
