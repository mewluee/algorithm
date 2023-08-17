package 다익스트라_Dijkstra;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인접행렬로구현_참고1 {
    static int[][] map;
    static int V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        map = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()); //출발 노드
            int end = Integer.parseInt(st.nextToken()); //도착 노드
            int weight = Integer.parseInt(st.nextToken()); //가중치

            map[start][end] = weight;
            map[end][start] = weight;
        }

        Dijkstra(0);
    }

    private static void Dijkstra(int node) {
        int[] distance = new int[V];          // 최단 거리를 저장할 변수
        boolean[] visited = new boolean[V];     // 해당 노드를 방문했는지 체크할 변수

        // distance값 초기화.
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 시작노드값 초기화.
        distance[node] = 0;
        visited[node] = true;

        // 결과값 출력
        for (int i = 0; i < V; ++i) {
            if (distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
            else System.out.print(distance[i] + " ");
        }
        System.out.println();

        // 연결노드 distance갱신
        for (int i = 0; i < V; ++i) {
            if (!visited[i] && map[node][i] != Integer.MAX_VALUE) {
                distance[i] = map[node][i];
            }
        }

        // 결과값 출력
        for (int i = 0; i < V; ++i) {
            if (distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
            else System.out.print(distance[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < V - 1; ++i) {
            // 노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
            int min = Integer.MAX_VALUE;
            int min_index = -1;

            // 노드 최소값 찾기
            for (int j = 0; j < V; ++j) {
                if (!visited[j]) {
                    if (distance[j] < min) {
                        min = distance[j];
                        min_index = j;
                    }
                }
            }

            // 다른 노드를 거쳐서 가는 것이 더 비용이 적은지 확인
            visited[min_index] = true;
            for (int j = 0; j < V; ++j) {
                if (!visited[j] && map[min_index][j] != Integer.MAX_VALUE) {
                    if (distance[min_index] + map[min_index][j] < distance[j]) {
                        distance[j] = distance[min_index] + map[min_index][j];
                    }
                }
            }

            // 결과값 출력
            for (int j = 0; j < V; ++j) {
                if (distance[j] == Integer.MAX_VALUE) System.out.print("∞ ");
                else System.out.print(distance[j] + " ");
            }
            System.out.println();
        }
    }
}
