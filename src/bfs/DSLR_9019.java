package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DSLR_9019 {


    static boolean[] visited;
    static String[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            visited = new boolean[10000];
            result = new String[10000];
            Arrays.fill(result, "");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            BFS(A, B);
            System.out.println(result[B]);
        }
    }

    public static void BFS(int A, int B) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);
        visited[A] = true;

        while (!queue.isEmpty() && !visited[B]) {
            int a = queue.poll();

            int d = D(a);
            int s = S(a);
            int l = L(a);
            int r = R(a);

            if (!visited[d]) {
                queue.add(d);
                visited[d]=true;
                result[d]=result[a]+"D";
            }
            if (!visited[s]) {
                queue.add(s);
                visited[s]=true;
                result[s]=result[a]+"S";
            }
            if (!visited[l]) {
                queue.add(l);
                visited[l]=true;
                result[l]=result[a]+"L";
            }
            if (!visited[r]) {
                queue.add(r);
                visited[r]=true;
                result[r]=result[a]+"R";
            }

        }
    }

    public static int D(int a) {
        return (a * 2) % 10000;
    }

    public static int S(int a) {
        return a==0? 9999 : a-1;
    }

    public static int L(int a) {

        String str_a = Integer.toString(a);
        str_a="0".repeat(4-str_a.length())+str_a;
        char[] chars = str_a.toCharArray();
        char temp = chars[0];
        System.arraycopy(chars, 1, chars, 0, chars.length - 1); //오른쪽껄 왼쪽으로 끌고오기
        chars[chars.length - 1] = temp;

        return Integer.parseInt(new String(chars));
    }

    public static int R(int a) {
        String str_a = Integer.toString(a);
        str_a="0".repeat(4-str_a.length())+str_a;
        char[] chars = str_a.toCharArray();
        char temp = chars[chars.length - 1];
        System.arraycopy(chars, 0, chars, 1, chars.length - 1); //오른쪽껄 왼쪽으로 끌고오기
        chars[0] = temp;

        return Integer.parseInt(new String(chars));
    }
}
