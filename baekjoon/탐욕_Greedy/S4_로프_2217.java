package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_로프_2217 {

    //문제: 42217 실버4 - 로프
    //알고리즘: 정렬, 그리디
    //1. 제일 작은 중량을 버틸 수 있는 로프를 기준으로 로프 개수만큼 곱하면 된다.
    //2. 로프가 끊어진다(?)고 해도 다른 로프가 해당 중량을 버틸 수 있다면 가능하다.
    //   그래서 두개를 가지고 최대값을 비교하면 된다.
    //3. N개의 로프가 주어지지만, k개의 로프를 선택해서 들 수 있다(문제 잘 읽자)
    //   -> 조합 구현해서 하니까 시간초과..ㅎㅎ
    //4. k가 몇이든 제일 작은 중량으로 결정되니까 정렬되어 있는 데이터를 기준으로 검사한다.
    //   -> 시간호과 ㅇㅇ
    //		왜? 애초에 정렬하는데 1초 든다고 가정하면 그 후에도 n^2이라서 또 1초가 든다 (2초 초과!)
    //5. 생각..
    // (k)  2   4   6   8   10
    //  1   2   4   6   8   10
    //  2   2x2 4x2 6x2 8x2
    //  3   2x3 4x3 6x3
    //  4   2x4 4x4
    //  5   2x5
    // 정렬된 데이터를 기준으로, k가 5개라는건 5개 루프를 선택했을때, 중량의 최대값은 min*k이다.
    // k가 4개(4개의 루프를 선택했을 때, 선택한 범위에서 제일 작은 min은 2혹은 4를 갖는다. 그래서 그 둘만 2x4, 4x4를 하면 된다.


    static int N;
    static int[] input;
    static int max=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        input=new int[N];
        for(int n=0; n<N; n++) {
            input[n]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(input);
//조합구현(3)
//		for(int i=1; i<=N; i++) {
//			boolean[] visited=new boolean[N];
//			combi(i, 0, i, visited);
//		}
//(4)
//		for(int i=1; i<=N; i++) {
//			solve4(i);
//		}
        //(5)
        for(int i=0; i<N; i++) {
            int min=input[i];
            max=Math.max(max, min*(N-i));
        }
        System.out.println(max);
    }

    public static void solve4(int count) {
        for(int i=0; i<=N-count; i++) {
            max=Math.max(max, input[i]*count);
        }
    }


    public static void combi(int goal, int start, int count, boolean[] visited) {
        if(goal==0) {
            check(visited, count);
        }
        for(int i=start; i<N; i++) {
            if(visited[i]) continue;
            visited[i]=true;
            combi(goal-1, i+1, count, visited);
            visited[i]=false;
        }
    }

    public static void check(boolean[] visited, int count) {
        int[] arr=new int[count];
        int index=0;
        for(int n=0; n<N; n++) {
            if(visited[n]) {
                arr[index]=input[n];
                index++;
            }
        }
        //System.out.println(Arrays.toString(arr));
        max=Math.max(max, Math.max(arr[0]*count, arr[count-1]));
    }
}
