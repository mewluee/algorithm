package 깊이우선탐색_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 바이러스_2606 {

    static int N; //컴퓨터 수 1~100
    static int C; //컴퓨터 쌍의 수

    static boolean[][] map;
    static boolean[] visited;
    static int result;

    static ArrayList<Integer>[] list;
    // 인접행렬, 인접리스트 둘다 메모리에 큰 차이가 없음. -> 왜..?
    // 속도도 큰 차이가 없었음. 이건 뭐 속도는 비슷할 거 예상했고, 메모리는 왜지...흠..
    // 연결된 선을 리스트에 담아서 검색하는 건, 매번 선 n개를 검색해서 속도에 영향을 많이 주려나.
    // 입력하는 선이 많을 수록 속도가 나빠지긴 하겠다.

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        list=new ArrayList[N+1];
        for (int n = 0; n < N + 1; n++) {
            list[n]=new ArrayList<>();
        }

        visited=new boolean[N+1];

        StringTokenizer st;
        for (int c = 0; c < C; c++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
            //양방향
        }
        result=0;
        dfs_list(1);
        System.out.println(result-1);
    }

    static void dfs_list(int n){
        visited[n]=true;
        result++;
        for (int i:list[n]) {
            if(!visited[i]){
                dfs_list(i);
            }
        }
    }

    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        map=new boolean[N+1][N+1];
        visited=new boolean[N+1];

        StringTokenizer st;
        for (int c = 0; c < C; c++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b]=true;
            map[b][a]=true;
            //양방향
        }
        result=0;
        //dfs(1);
        //bfs();
        dfs2();
        System.out.println(result-1);
    }

    static void dfs(int n){
        visited[n]=true;
        result++;
        for (int i = 0; i < N+1; i++) {
            if(map[n][i] && !visited[i]){
                dfs(i);
            }
        }
    }

    static void dfs2(){
        Stack<Integer> stack = new Stack();
        stack.push(1);

        while (!stack.isEmpty()) {
            int now=stack.pop();
            if(visited[now]) continue;
            visited[now]=true;
            result++;

            for (int i = 1; i < N+1; i++) {
                if(map[now][i]){
                    stack.add(i);
                }
            }
        }
    }

    static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int now=queue.poll();
            if(visited[now]) continue;
            visited[now]=true;
            result++;

            for (int i = 1; i < N+1; i++) {
                if(map[now][i]){
                    queue.add(i);
                }
            }
        }
    }
}
