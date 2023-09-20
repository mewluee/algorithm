package 너비우선탐색_BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기2_14442_실패 {

    //문제
    //끝에서 끝으로
    //최단거리. 양의 수
    //BFS, DFS는 안댐.

    static int N;
    static int M;
    static int K;
    static int[][] map;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        map=new int[N+1][M+1];

        for(int n=1; n<=N; n++) {
            String str=br.readLine();
            for(int m=1; m<=M; m++) {
                map[n][m]=str.charAt(m-1)-'0';
            }
            //System.out.println(Arrays.toString(map[n]));
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int[] dx= {-1,1,0,0};
        int[] dy= {0,0,-1,1};

        boolean[][] visited=new boolean[N+1][M+1];
        Queue<int[]> queue=new LinkedList<>();

        int answer=-1;
        queue.add(new int[]{1,1,1,K});
        visited[1][1]=true;


        while(!queue.isEmpty()) {

            int[] now=queue.poll();
            //System.out.println("now["+now[0]+", "+now[1]+"]:"+now[2]);
            if(now[0]==N && now[1]==M) {
                answer=now[2];
                break;
            }

            for(int i=0; i<4; i++) {
                int x=now[0]+dx[i];
                int y=now[1]+dy[i];

                if(x<1 || N<x || y<1 || M<y) continue;
                if(visited[x][y]) continue;
                if(map[x][y]==1&&now[3]<1) continue; //벽이지만, 부실수없을 때 패스

                //System.out.println("["+x+", "+y+"]");
                if(map[x][y]==1) { //부셔!
                    queue.add(new int[] {x,y,now[2]+1, now[3]-1});
                }else {
                    queue.add(new int[] {x,y,now[2]+1, now[3]});
                }
                visited[x][y]=true;
                //해당 위치에 도달할 때, 벽을 1개 부실 수 있고 n개 부실 수 있다.
                //3차원배열 3배열 개수..
            }
        }

        return answer;
    }
}
