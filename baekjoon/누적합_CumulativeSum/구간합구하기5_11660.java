package 누적합_CumulativeSum;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간합구하기5_11660 {


    //문제 구간합.

    static int N;
    static int M;
    static int[][] map;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N+1][N+1];

        for(int n=1; n<=N; n++) {
            int[] dumps=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int m=1; m<=N; m++) {
                map[n][m]= Arrays.stream(dumps, 0, m).sum();
                //System.out.println("["+n+", "+m+"]->map:"+map[n][m]);
            }

            //System.out.println("-----");
        }

        for(int m=0; m<M; m++) {
            st=new StringTokenizer(br.readLine());
            int sx=Integer.parseInt(st.nextToken());
            int sy=Integer.parseInt(st.nextToken());
            int ex=Integer.parseInt(st.nextToken());
            int ey=Integer.parseInt(st.nextToken());
            //System.out.println(answer);
            bw.append(String.valueOf(cal(sx,sy,ex,ey))+"\n");
        }

        bw.flush();
        bw.close();
    }

    static int cal(int sx, int sy, int ex, int ey) {
        int answer=0;
        for(int s=sx; s<=ex; s++) {
            //System.out.println("["+s+", "+ey+"] - ["+s+", "+sy+"]");
            answer+=map[s][ey]-map[s][sy-1];
        }
        return answer;
    }
}
