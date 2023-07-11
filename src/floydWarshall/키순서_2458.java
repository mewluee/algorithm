package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 키순서_2458 {

    static int N;
    static int M;

    static List<ArrayList<Integer>> list;
    static int[][] result;
    static int[][] map;

    static int INF = 987654321; //좋아...

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int n = 0; n < N + 1; n++) {
            Arrays.fill(map[n], INF);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
        }

        floyd();

        //printArr(map);
        int answer=0;
        for (int n = 1; n <= N; n++) {
            int linkedCount=countLinked(n);
            if(linkedCount==N-1) answer++;
        }
        System.out.println(answer);
    }

    public static void floyd() {

        for (int k = 1; k <= N; k++) {
            for (int n = 1; n <= N; n++) {
                for (int m = 1; m <= N; m++) {
                    if (n == m) continue;
                    map[n][m] = Math.min(map[n][m], map[n][k] + map[k][m]);
                }
            }
        }
        //printArr(map);
    }

    public static int countLinked(int num){
        int small=0;
        int big=0;
        for (int n = 1; n <= N; n++) {
            if(map[n][num]!=INF) small++;
            if(map[num][n]!=INF) big++;
        }
        return small+big;
    }

    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int n = 0; n < N + 1; n++) {
            list.add(new ArrayList<>());
        }
        result = new int[N + 1][2];
        //0번 인덱스는 나보다 작은 사람 > 도착할때마다 도착한 노드의 0번 인덱스값 증가
        //1번 인덱스는 나보다 큰 사람 > 도착할때마다 내 노드의 1번 인덱스값 증가

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
        }

        for (int n = 1; n <= N; n++) {
            dfs(n);
        }

        printArr(result);
    }

    public static void dfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);

        while (!queue.isEmpty()) {

            int now = queue.poll();

            if (!visited[now]) {
                for (int n = 0; n < list.get(now).size(); n++) {
                    int next = list.get(now).get(n);
                    result[next][0]++;
                    result[now][1]++;
                    queue.add(next);
                }
            }

        }
    }


    public static void printArr(int[][] arr) {
        for (int n = 0; n < N + 1; n++) {
            System.out.println(Arrays.toString(arr[n]));
        }
    }
}
