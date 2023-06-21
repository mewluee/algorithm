package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS와BFS_1260 {

    static int N;
    static int M;
    static int V;

    static String resultStr;
    static Stack<Integer> stack;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        N = inputs[0];
        M = inputs[1];
        V = inputs[2];

        map = new boolean[N + 1][N + 1];


        for (int m = 0; m < M; m++) {
            int[] AB = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            map[AB[0]][AB[1]] = true;
            map[AB[1]][AB[0]] = true;
        }

        //printMap();

        boolean[] visited = new boolean[N + 1];
        resultStr = "";
        DFS3(visited, V);
        System.out.println(resultStr);
        resultStr = "";
        BFS(V);
        System.out.println(resultStr);
    }

    static void printMap() {
        for (int n = 0; n < N + 1; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
    }

    static boolean isVisitedAllNode(boolean[] visited) {
        System.out.println(Arrays.toString(visited));
        for (int n = 1; n <= N; n++) {
            if (!visited[n]) return false;
        }
        return true;
    }

    static void DFS2(boolean[] visited, int node) {
        visited[node] = true;
        stack.push(node);
        if (stack.size() == N) {
            for (int i = 0; i < stack.size(); i++) {
                System.out.print(stack.elementAt(i) + " ");
            }
            stack.pop();
            return;
        }

        for (int n = 1; n <= N; n++) {
            if (map[node][n] && !visited[n]) {
                DFS2(visited, n);
                visited[n] = false;
            }
        }

        stack.pop();
    }

    static void DFS3(boolean[] visited, int node) {
        visited[node]=true;
        resultStr+=node+" ";
        for (int n = 1; n <= N; n++) {
            if (map[node][n] && !visited[n]) {
                DFS3(visited, n);
            }
        }


    }

    static void DFS(int start) {

        boolean[] visited = new boolean[N + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {

            int now = stack.pop();

            visited[now] = true;
            resultStr += now + " ";
            System.out.println(resultStr);

            for (int n = N; n >= 1; n--) {
                if (map[now][n] && !visited[n]) {
                    stack.push(n);
                }
            }
        }

    }

    static boolean isDuplicated(Stack<Integer> stack, int obj) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.elementAt(i) == obj) {
                return true;
            }
        }
        return false;
    }

    static void BFS2(boolean[] visited, int node) {
        visited[node]=true;

    }

    static void BFS(int start) {

        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {

            int now = queue.poll();

            resultStr +=now+" ";
            //System.out.println(resultStr);

            for (int n = 1; n <= N; n++) {
                if (map[now][n] && !visited[n]) {
                    queue.add(n);
                    visited[n] = true;
                }
            }
        }

    }
}
