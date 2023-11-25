package 깊이우선탐색_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S3_바이러스_2606 {
//문제: 실버3 바이러스 2606
    //요약:
    //연결되어있는 노드들은 다 바이러스에 감염처리
    //감염된 노드의 수 출력
    //알고리즘:그래프
    //풀이:
    //그래프 표현을 뭐로? 한 노드에 여러개의 노드가 연결되어 있다.
    //양방향 중요하다.

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int N=Integer.parseInt(br.readLine()); // 컴퓨터 수
        int M=Integer.parseInt(br.readLine()); // 연결쌍의 수
        ArrayList<Integer>[] nodes = new ArrayList[N+1];
        for(int n=0; n<N+1; n++) {
            nodes[n]=new ArrayList<>();
        }
        StringTokenizer st;
        for(int m=0; m<M; m++) {
            st=new StringTokenizer(br.readLine(), " ");
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

//		for(int n=0; n<N; n++) {
//			System.out.println(Arrays.toString(nodes[n].toArray()));
//		}

        Queue<Integer> queue=new LinkedList<>();
        boolean[] visited=new boolean[N+1];
        visited[1]=true;
        queue.add(1);
        int result=0;

        while(!queue.isEmpty()) {
            int now=queue.poll();

            for(int c=0; c<nodes[now].size(); c++) {
                if(visited[nodes[now].get(c)]) continue;
                queue.add(nodes[now].get(c));
                visited[nodes[now].get(c)]=true;
                result++;
            }
        }

        System.out.println(result);

    }


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

    public static void main3(String[] args) throws IOException {

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
