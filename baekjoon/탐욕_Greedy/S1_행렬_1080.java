package 탐욕_Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class S1_행렬_1080 {
    //문제: 실버1 행렬
    //요약:
    //행렬A를 행렬B로 바꾸는 데 필요한 연산 횟수 최솟값 구하기

    //풀이: 그리디

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        int[][] A=new int[N][M];
        int[][] B=new int[N][M];

        for(int n=0; n<N; n++) {
            String input=br.readLine();
            for(int m=0; m<M; m++) {
                A[n][m]=Character.getNumericValue(input.charAt(m));
            }
        }
        for(int n=0; n<N; n++) {
            String input=br.readLine();
            for(int m=0; m<M; m++) {
                B[n][m]=Character.getNumericValue(input.charAt(m));
            }
        }

        if(N<3 ||M<3) {
            for(int n=0; n<N; n++) {
                for(int m=0; m<M; m++) {
                    if(A[n][m]!=B[n][m]) {
                        System.out.println("-1");
                        return;
                    }
                }
            }
        }
        int count=0;
        for(int n=0; n<N; n++) {
            for(int m=0; m<M; m++) {
                //System.out.println("-------------");
                //System.out.println("["+n+", "+m+"]");
                //System.out.println("A:"+A[n][m]+", B:"+B[n][m]);
                if(A[n][m]!=B[n][m]) {
                    //3거리가 되는지 검사
                    if(n+3>N || m+3>M) {
                        System.out.println("-1");
                        return;
                    }else {
                        count++;
                        for(int i=n; i<n+3; i++) {
                            for(int j=m; j<m+3; j++) {
                                A[i][j]=A[i][j]==0? 1:0;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
