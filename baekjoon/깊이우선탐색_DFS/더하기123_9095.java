package 깊이우선탐색_DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 더하기123_9095 {

    static int T;
    static int N;
    static int[] dic = {1, 2, 3};
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        //재귀로 구현
//        for (int t = 0; t < T; t++) {
//            N = Integer.parseInt(br.readLine());
//            count = 0;
//            dfs(0);
//            System.out.println(count);
//        }

        //스택으로 구현
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            count = 0;
            dfs2();
            System.out.println(count);
        }
    }

    static void dfs(int result) {
        if (result == N) {
            count++;
            return;
        }
        if (result > N) return;
        for (int i = 0; i < 3; i++) {
            result += dic[i];
            dfs(result);
            result -= dic[i];
        }
    }

    static void dfs2() {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int now = stack.pop();

            if (now == N) {
                count++;
                continue;
            }
            if (now > N) {
                continue;
            }
//            for (int i = 2; i >=0; i--) {
//                int next = now + dic[i];
//                stack.push(next);
//            }
            for (int i = 0; i < 3; i++) {
                int next = now + dic[i];
                stack.push(next);
            }
        }
    }
}
