package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_음식물피하기_1743 {
    //문제: 실버1 음식물 피하기
    //요약:
    //통로에 음식물
    //근처에있는것끼리 뭉쳐서 큰 음식물
    //큰 음식물의 크기 구하기
    //N통로의 세로길이
    //M통로의 가로길이
    //음식물 쓰레기 개수 K
    //r행 c열
    //k개의 줄에 음식물쓰레기 좌표
    //좌표는 중복이 아니다
    //알고리즘 BFS
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int N=Integer.parseInt(st.nextToken()); //행
        int M=Integer.parseInt(st.nextToken()); //열
        int K=Integer.parseInt(st.nextToken());
        int[][] map=new int[N][M];
        boolean[][] visited=new boolean[N][M];

        for(int k=0; k<K; k++) {
            st=new StringTokenizer(br.readLine()," ");
            int r=Integer.parseInt(st.nextToken())-1;
            int c=Integer.parseInt(st.nextToken())-1;
            map[r][c]=1;
        }
        int[] dx= {-1, 1, 0, 0};
        int[] dy= {0, 0, -1, 1};
        int result=1;

        for(int n=0; n<N; n++) {
            for(int m=0; m<M; m++) {
                if(map[n][m]==1 && !visited[n][m]) {
                    Queue<int[]> queue=new LinkedList<>();
                    queue.add(new int[] {n,m});
                    visited[n][m]=true;
                    int count=1;
                    while(!queue.isEmpty()) {
                        int[] now=queue.poll();

                        for(int d=0; d<4; d++) {
                            int x=now[0]+dx[d];
                            int y=now[1]+dy[d];
                            if(x<0 || N<=x || y<0 || M<=y) continue;
                            if(visited[x][y]||map[x][y]==0) continue;
                            queue.add(new int[] {x,y});
                            visited[x][y]=true;
                            count++;
                        }
                    }
                    if(count>result) result=count;
                }
            }
        }
        System.out.println(result);
    }
}
