package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_토마토_7569 {
    //문제: 골드5 토마토 7569
    //요약:
    //3차원 맵
    //토마토는 밑의 상자부터 위의 상자로 입력된다.
    //대각선으로는 영향을 주지 않는다.
    //며칠이 지나야 다 익는지 최소 일수 구하기
    //1은 익은토마토
    //0은 안익은 토마토
    //-1 빈공간

    //풀이:BFS

    static int N;
    static int M;
    static int H;

    static int[] dx= {0,0,-1,1,0,0};
    static int[] dy= {0,0,0,0,-1,1};
    static int[] dh= {1,-1,0,0,0,0};
    static int[][][] map;
    //앞,뒤,위,아래,왼쪽,오른쪽
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        M=Integer.parseInt(st.nextToken()); //맵 열 2~100
        N=Integer.parseInt(st.nextToken()); //맵 행 2~100
        H=Integer.parseInt(st.nextToken()); //높이 1~100

        map=new int[H][N][M];
        List<int[]> tomatos=new ArrayList<>();
        boolean[][][] visited=new boolean[H][N][M];

        for(int h=0; h<H; h++) {
            for(int n=0; n<N; n++) {
                st=new StringTokenizer(br.readLine(), " ");
                for(int m=0; m<M; m++) {
                    map[h][n][m]=Integer.parseInt(st.nextToken());
                    if(map[h][n][m]==1) tomatos.add(new int[] {h,n,m});
                    if(map[h][n][m]!=0) visited[h][n][m]=true;
                }
            }
        }

        Queue<int[]> queue=new LinkedList<>();
        for(int[] tomato:tomatos) {
            queue.add(tomato);
        }

        while(!queue.isEmpty()) {
            int[] now=queue.poll();
            for(int d=0; d<6; d++) {
                int h=now[0]+dh[d];
                int x=now[1]+dx[d];
                int y=now[2]+dy[d];
                if(x<0 || N<=x || y<0 || M<=y || h<0|| H<=h) continue;
                if(visited[h][x][y]) continue;
                map[h][x][y]=map[now[0]][now[1]][now[2]]+1;
                queue.add(new int[] {h,x,y});
                visited[h][x][y]=true;
            }
        }
        int result=0;
        for(int h=0; h<H; h++) {
            for(int n=0; n<N; n++) {
                for(int m=0; m<M; m++) {
                    if(map[h][n][m]==0) {
                        System.out.println("-1");
                        return;
                    }
                    if(map[h][n][m]>result) result=map[h][n][m];
                }
            }
        }
        System.out.println(result-1);
    }
}
