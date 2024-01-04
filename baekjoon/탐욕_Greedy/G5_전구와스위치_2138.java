package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class G5_전구와스위치_2138 {
    //문제: 골드5 전구와 스위치
    //i 스위치를 누르면 i-1, i, i+1 전구의 상태가 바뀐다.
    //전구는 1~N개의 번호를 갖고있다.
    //제일끝번호를 누르면 1,2 / N-1,N 이렇게 눌린다.
    //스위치를 최소 몇번 눌러야 하는가
    //백트래킹 각인데. -> 응 아냐..그리디야..

    static int N;
    static String output;
    static boolean[] arr;
    static boolean[] brr;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        arr = new boolean[N];
        for (int n = 0; n < N; n++) {
            if (input.charAt(n) == '1') arr[n] = true;
        }
        output = br.readLine();
        brr = new boolean[N];
        for (int n = 0; n < N; n++) {
            if (output.charAt(n) == '1') brr[n] = true;
        }
        //0번 스위치 안눌렀을 경우
        //dfs(1,0);
        int c1 = check(0);

        //System.out.println("----------");
        //0번 스위치 눌렀을 경우
        for (int n = 0; n < N; n++) {
            if (input.charAt(n) == '1') arr[n] = true;
            else arr[n] = false;
        }
        arr[0] = !arr[0];
        arr[1] = !arr[1];
        //dfs(1,1);
        int c2 = check(1);
        if (c1 != -1 && c2 != -1) {
            System.out.println(Math.min(c1, c2));
        } else {
            System.out.println(Math.max(c1, c2));
        }
    }

    static int check(int count) {
        for (int i = 1; i < N; i++) {
            //System.out.println("i:"+i+",count:"+count);
            //print();
            if (arr[i - 1] == brr[i - 1]) continue;
            //3번 - 전구 on/off
            for (int n = i - 1; n <= i + 1; n++) {
                if (n < 0 || n >= N) continue;
                arr[n] = !arr[n];
            }
            count++;
        }
        //System.out.println("완성 c:"+count);
        //print();
        if (arr[N - 1] == brr[N - 1]) {
            return count;
        } else {
            return -1;
        }
    }

    //
    static void dfs(int index, int count) {
        System.out.println("index:" + index);
        print();
        if (index == N) {
            System.out.println("끝");
            return;
        }
        for (int i = index; i < N; i++) {
            if (arr[i - 1] == brr[i - 1]) continue;
            for (int n = i - 1; n <= i + 1; n++) {
                if (n < 0 || n >= N) continue;
                arr[n] = !arr[n];
            }
            dfs(i, count + 1);
            for (int n = i - 1; n <= i + 1; n++) {
                if (n < 0 || n >= N) continue;
                arr[n] = !arr[n];
            }
        }

    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (arr[i]) sb.append(1);
            else sb.append(0);
        }
        System.out.println(sb.toString());
    }
}
