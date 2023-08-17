package 다익스트라_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class 파티_1238 {

    static int N;
    static int M;
    static int X;
    static int[][] map;
    static int[][] result;

    static ArrayList<Node>[] lists;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        X = Integer.parseInt(inputs[2]);

        lists = new ArrayList[N + 1];
        for (int n = 0; n < N + 1; n++) {
            lists[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            int[] lines = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            lists[lines[0]].add(new Node(lines[1], lines[2]));
        }

        int max = 0;
        int[] xdist = dijkstar3(X);
        for (int n = 1; n < N + 1; n++) {
            int rtime=dijkstar3(n)[X]+xdist[n];
            if(rtime>max) max=rtime;
        }
        // 총 N+1 -> 600~
        // revered_map > 2번 -> 200~
        System.out.println(max);
    }

    // 내가 구해야하는 경로들 n->x , x->n
    // 메서드가 구하는 값 : 시작점 -> n
    static int[] dijkstar3(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321); //dist[] : start -> n개의 노드로 가는 경로 시간
        dist[start] = 0; // start->start 는 0이다.
        Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.time - o2.time;
            }
        });
        //(2)
        boolean[] visited=new boolean[N+1];

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();
            //경유지

            //(2) 어째서..시간증가..????????????????????????????????????왜..시간이..더..?
            if(visited[now.dest]) continue;
            visited[now.dest]=true;

            for (Node next : lists[now.dest]) {
                int ntime = now.time + next.time;
                if (ntime < dist[next.dest]) {
                    dist[next.dest] = ntime;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }

        //System.out.println("시작점:" + start);
        //System.out.println(Arrays.toString(dist));
        return dist;
    }

    static class Node {
        int dest;
        int time;

        public Node(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }
    }

    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        X = Integer.parseInt(inputs[2]);

        map = new int[N][N];
        IntStream.range(0, N)
                .forEach(e -> Arrays.fill(map[e], 987654321));
        result = new int[N][N];

        for (int m = 0; m < M; m++) {
            int[] lines = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[lines[0] - 1][lines[1] - 1] = lines[2];
        }

        for (int n = 0; n < N; n++) {
            map[n][n] = 0;
        }
        //printMap(map);

        //--------
        for (int i = 0; i < N; i++) {
            result[i] = dijkstra2(i);
        }
        printMap(result);

        int max = 0;
        for (int i = 0; i < N; i++) {
            int distance = result[i][X] + result[X][i];
            if (distance > max) {
                max = distance;
            }
        }
        System.out.println(max);
    }


    static int[] dijkstra2(int start) {
        int[] dist = map[start];
        //System.out.println("초기dist값:" + Arrays.toString(dist));
        boolean[] visited = new boolean[N];
        visited[start] = true;

        //int node = findMinValueNode(dist, visited);
        for (int i = 0; i < N - 2; i++) {
            int node = findMinValueNode(dist, visited);
            visited[node] = true;
            for (int j = 0; j < N; j++) {
                if (!visited[j]) {
                    if (dist[j] > map[node][j] + dist[node]) {
                        dist[j] = map[node][j] + dist[node];
                    }
                }
            }
        }
        //System.out.println("완료 후 dist:" + Arrays.toString(dist));
        return dist;
    }

    static int[] dijkstra(int start) {
        int[] dist = map[start];
        //System.out.println("초기dist값:" + Arrays.toString(dist));
        boolean[] visited = new boolean[N];
        visited[start] = true;

        int node = findMinValueNode(dist, visited);
        while (node >= 0) {
            for (int n = 0; n < N; n++) {

                if (!visited[n]) {
                    int ds = map[node][n] + dist[node];
                    if (ds < dist[n]) {
                        dist[n] = ds;
                    }
                }
            }
            visited[node] = true;
            node = findMinValueNode(dist, visited);
        }
        //System.out.println("완료 후 dist:" + Arrays.toString(dist));
        return dist;
    }

    static int findMinValueNode(int[] arr, boolean[] visited) {
        //System.out.println("arr:" + Arrays.toString(arr));
        //System.out.println("visit:" + Arrays.toString(visited));
        int min = Integer.MAX_VALUE;
        int result = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i] && arr[i] < min) {
                min = arr[i];
                result = i;
            }
        }
        //System.out.println("min node:" + result + ", min:" + min);
        return result;
    }

    static void printMap(int[][] arr) {
        IntStream.range(0, N)
                .forEach(e -> System.out.println(Arrays.toString(arr[e])));
        System.out.println("-----");
    }
}
