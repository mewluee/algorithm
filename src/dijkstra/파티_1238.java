package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class 파티_1238 {

    static int N;
    static int M;
    static int X;
    static int[][] map;
    static int[][] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        X = Integer.parseInt(inputs[2]);

        map = new int[N][N];
        IntStream.range(0, N)
                .forEach(e -> Arrays.fill(map[e], 1000000000));
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

        for (int i = 1; i <= N; i++) {
            result[i-1]=dijkstra(i);
        }
        //printMap(result);

        int max=0;
        for (int i = 0; i < N; i++) {
            int distance=result[i][X]+result[X][i];
            if (distance > max) {
                max=distance;
            }
        }
        System.out.println(max);
    }

    static int[] dijkstra(int start) {
        int[] dist = map[start - 1];
        //System.out.println("초기dist값:" + Arrays.toString(dist));
        boolean[] visited = new boolean[N];
        visited[start - 1] = true;

        int node = findMinValueNode(dist, visited);
        while (node >= 0) {
            for (int n = 0; n < N; n++) {
                int ds = map[node][n] + dist[node];
                if (ds < dist[n]) {
                    dist[n] = ds;
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
