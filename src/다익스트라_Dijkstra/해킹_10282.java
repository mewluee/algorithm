package 다익스트라_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 해킹_10282 {

    static int N;
    static int D;
    static int C;
    static List<ArrayList<Node>> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            N = inputs[0];
            D = inputs[1];
            C = inputs[2];

            list = new ArrayList<>();
            for (int n = 0; n < N + 1; n++) {
                list.add(new ArrayList<>());
            }

            for (int d = 0; d < D; d++) {
                inputs = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                list.get(inputs[1]).add(new Node(inputs[0], inputs[2]));
            }
            int[] result = hacking2(C);
            printAnswer(result);
        }
    }

    public static void printAnswer(int[] result) {
        int count = 0;
        int time = 0;
        for (int n = 1; n < N+1; n++) {
            if (result[n] != 987654321) {
                count++;
                time = Math.max(time, result[n]);
            }
        }
        System.out.println(count + " " + time);
    }

    public static int[] hacking2(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321);
        dist[start] = 0;

        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int now = node.computerNum;
            int time = node.time;

            if (dist[now] < time) continue;

            for (Node next : list.get(now)) {
                if ((time + next.time) < dist[next.computerNum]) {
                    dist[next.computerNum] = time + next.time;
                    queue.add(new Node(next.computerNum, dist[next.computerNum]));
                }
            }
        }
        return dist;
    }

    public static int hacking(BufferedReader br) throws IOException {
        int second = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        for (int d = 0; d < D; d++) {
            int[] inputs = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            queue.add(inputs);
        }
        boolean[] visited = new boolean[N];
        int start = C - 1;
        visited[start] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[1] == start && !visited[now[0]]){

            }
        }
        return 0;
    }

    private static void printMap(int[][] map) {
        System.out.println("----------------");
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static class Node implements Comparable<Node> {
        int computerNum;
        int time;

        public Node(int computerNum, int time) {
            this.computerNum = computerNum;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }
}
