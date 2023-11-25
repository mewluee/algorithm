package 백트래킹_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_NQueen_9663 {
    //문제: 골드4 N-Queen
    //알고리즘:
    //크기가 NxN인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제
    //퀸을 놓는 방법의 수 출력
    //풀이:
    //브루트포스인가? dfs
    //퀸의 이동 범위 상하좌우, 대각선 모두 다, 맵의 끝까지.
    //중요한건,,,한 행에, 한 열에 하나의 퀸만 있을 수 있따는 점.
    //그래서 퀸이 N개 구나.
    static int N;
    static int result=0;
    static boolean[] visited; //열의 방문 표현
    static int[] dx= {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy= {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        N=Integer.parseInt(br.readLine()); //N

        map=new boolean[N][N];

        visited=new boolean[N];
        dfs(-1,0);

        System.out.println(result);

    }

    static void dfs(int row, int col) {
        //System.out.println("-------------");
        //System.out.println("row:"+row+", col:"+col);
        //System.out.println(Arrays.toString(visited));
        if(row>=0) {

            if(!checkQueen(row, col)) return; //더이상 검사 안함, 백트래킹
            if(row==N-1) {
                result++;
//				for(int n=0; n<N; n++) {
//					System.out.println(Arrays.toString(map[n]));
//				}
                return;
            }
        }

        for(int n=0; n<N; n++) { // 열을 입력
            if(visited[n]) continue;

            visited[n]=true;
            map[row+1][n]=true;

            dfs(row+1, n); //다음 행, 열

            visited[n]=false;
            map[row+1][n]=false;
        }
    }

    static boolean checkQueen(int r, int c) { //퀸이 있다 -> false
        for(int d=0; d<8; d++) {
            //System.out.println("방향 d:"+d);
            int x=r;
            int y=c;
            for(int n=0; n<N; n++) {
                x+=dx[d];
                y+=dy[d];
                //System.out.println("["+x+", "+y+"]");
                if(x<0 || y<0 || N<=x || N<=y) continue;
                if(map[x][y]) {
                    //System.out.println("검사:"+map[x][y]);
                    return false;
                }
            }
        }
        return true;
    }
}
