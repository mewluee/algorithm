package 동적계획법_DynamicProgramming;

import java.io.*;
import java.util.StringTokenizer;

public class G4_최대정사각형_4095 {

    //문제: 4095 최대정사각형
    //알고리즘: dp
    //요약
    //판이 주어지고 거기에 1로 이뤄진 정사각형 중에 제일 큰 정사각형의 너비를 출력한다.

    //풀이1 (틀림, 반례 못 찾겠음)
    //map은 한 행씩 누적되서 더해진다.
    //dp는 [0][0] 부터 [a][b], 즉 직사각형 모양으로 map을 활용하여 1의 값을 저장한다.
    //케이스가 가질 수 있는 정사각형의 최대 너비는 두 변 중 작은 변의 길이다.
    //해당 길이(d)로 시작해서 맵 끝 인덱스부터 비교하기 시작한다.
    //N=4 M=5 일때 index [3,4]에서 d만큼 정사각형 공간의 1의 값을 sum에 담는다. (dp 배열 활용)
    //sum=d*d 이면 정사각형 안에 1이 다 차있는거니까. max값 갱신한다.

    //풀이2 (dp)
    //1로 정사각형이 구성되어 있으면 한 행씩 입력받을 때마다 알고리즘에 따라 값이 변한다.
    //   1  1  1      1  1  1
    //   1  1  1  ->  1  2  2
    //   1  1  1      1  2  3

    //1. 왼쪽 상단에서 시작해서 오른쪽 하단으로 방향을 잡는다.
    //2. (1) (2)
    //   (3) (4)
    //   4번의 값을 갱신할 때 고려해야하는 점이 있다.
    //   -> 1,2,3번의 값이 다 1일 때인 경우 : 4번이 1일 경우에는 다 1이니까 정사각형이 완성된다. 따라서 4번의 값이 증가한다.
    //   -> 1,2,3번의 값이 하나라도 0일 때인 경우 : 하나라도 0이면 4번의 값이 증가하지 않는다.

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());
            if(N==0 && M==0) break;
            int[][] dp=new int[N+1][M+1];
            int max=0;
            for(int n=1; n<=N; n++){
                st=new StringTokenizer(br.readLine(), " ");
                for(int m=1; m<=M; m++){
                    int input=Integer.parseInt(st.nextToken());
                    if(input==1){
                        dp[n][m]=Math.min(dp[n-1][m-1], Math.min(dp[n-1][m], dp[n][m-1]))+1;
                        //1,2,3번 위치의 값을 확인한다. 하나라도 0이 있으면 min값을 가져오기 때문에 +1해도 값이 그대로 유지된다.
                        //셋다 0이 아닐 경우 +1이 된다.
                    }
                    if(max<dp[n][m]) max=dp[n][m];
                }
            }
            bw.append(max+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            System.out.println("====================");
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());
            if(N==0 && M==0) break;
            int[][] map=new int[N][M];

            for(int n=0; n<N; n++) {
                st=new StringTokenizer(br.readLine()," ");
                map[n][0]=Integer.parseInt(st.nextToken());
                for(int m=1; m<M; m++) {
                    map[n][m]=map[n][m-1]+Integer.parseInt(st.nextToken());
                }
                //System.out.println(Arrays.toString(map[n]));
            }


            int[][] map_sum=new int[N][M];

            for(int m=0; m<M; m++) {
                map_sum[0][m]=map[0][m];
            }
            for(int n=1; n<N; n++) {
                for(int m=0; m<M; m++) {
                    map_sum[n][m]=map_sum[n-1][m]+map[n][m];
                }
            }
            int d=Math.min(N, M);

//			for(int n=0; n<N; n++) {
//				System.out.println(Arrays.toString(map_sum[n]));
//			}
            int max=0;

            while(d>0) {
                System.out.println("d:"+d);
                for(int n=N-1; n>=d-1; n--) {
                    for(int m=M-1; m>=d-1; m--) {
                        System.out.println("["+n+", "+m+"] ~ ["+(n-d+1)+", "+(m-d+1)+"]");
                        int sum=map_sum[n][m];
                        if(n-d>=0) sum-=map_sum[n-d][m];
                        if(m-d>=0) sum-=map_sum[n][m-d];
                        if(n-d>=0 && m-d>=0) sum+=map_sum[n-d][m-d];
                        System.out.println("sum:"+sum);
                        if(sum==d*d) max=d;
                    }
                }
                if(max==0) d--;
                else {
                    bw.append(String.valueOf(max)+"\n");
                    break;
                }
            }
            if(max==0) bw.append("0");

        }

        bw.flush();
        bw.close();

    }

    /* 입력
    4 5
0 0 0 0 1
0 1 1 1 0
0 1 0 1 0
0 1 1 1 1
4 5
0 0 0 0 0
0 1 1 1 0
0 1 0 1 0
0 1 1 1 1
4 5
0 0 0 0 0
0 1 1 1 0
0 1 0 1 1
0 1 1 1 1
4 5
0 0 0 0 0
0 1 1 1 0
0 1 1 1 1
0 1 1 1 1
4 5
0 1 1 1 1
0 1 1 1 1
0 1 1 1 1
0 1 1 1 1
4 5
1 1 1 1 0
1 1 1 1 0
1 1 1 1 0
1 1 1 1 0
4 5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
6 6
1 0 1 0 1 0
1 0 1 0 1 1
1 1 1 1 1 1
1 1 0 1 0 1
1 0 1 0 1 0
1 1 1 1 1 1
1 1
1
3 3
1 0 1
0 1 0
1 0 1
8 8
1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
0 1 1 1 0 1 1 1
0 1 1 1 1 1 1 1
0 0 0 1 1 1 1 1
0 0
     */
}
