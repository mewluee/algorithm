package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_전깃줄_2565 {

    //문제: 골드5 전깃줄 2565
    //요약:
    //두 전봇대 A와 B
    //전깃줄을 추가하다보니 서로 교차하는 경우 합선위험
    //몇 개의 전깃줄을 없애 교차하지 않도록.
    //없애야하는 전깃줄의 최소 개수 구하기
    //위치 번호 1~500
    //같은 위치에 두개이상의 전깃줄이 연결될 수 없다.
    //전깃줄의 개수는 100이하
    //

    //풀이:브루트포스?
    //전깃줄이 겹치는 경우는 어떻게 알 수 있을까.
    //그림을 그릴 수도 없고..
    //전깃줄이 겹치지 않는 다는 건..
    //두 전봇대를 배열로 표현했을때,
    //A전봇대에서 0번 인덱스부터 비교한다.
    //연결되어 있는 점을 찾으면 해당 숫자를 기준으로 B전봇대의 처음부터 검색한다.
    //B전봇대에서 해당 숫자를 찾는데 해당 숫자보다 먼저 큰숫자가 나오면 겹치는 전깃줄이다.
    //왼쪽 기준의 숫자보다 오른쪽 숫자가 너무 크거나 너무 작을때, 고려해야한다.
    //조합을 짜서.. 되는걸 다 검사?
    //100을 100번 곱해야 한다..맙소사..

    //풀이2: dp
    //전체 전선개수- 설치가능 개수 = 철거개수
    //LIS

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int[][] as = new int[N][2];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            as[n] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(as, (o1, o2) -> o1[0] - o2[0]);
        //a 기둥을 기준으로 b 기둥 정렬

        int[] dp = new int[N];
        int count = 0;

        for (int n = 0; n < N; n++) {
            for (int m = 0; m < n; m++) { // 0 ~ n-1 검색해서 현재 b기둥 숫자보다 작은지 확인
                if (as[m][1] < as[n][1]) dp[n] = Math.max(dp[m] + 1, dp[n]);
                // b기둥 현재 숫자보다 작은 개수(n) = 그 전 숫자(m)보다 작은 숫자 개수 + 1(m 포함)
            }
            if (count < dp[n]) count = dp[n];
        }
        System.out.println(N - (count + 1));
        //count+1 을 해서 빼야한다.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int[][] as = new int[N][2];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            as[n] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(as, (o1, o2) -> o1[0] - o2[0]);
        //print(N, as);

        int[] arr = new int[N];
        int length = 0;

        for (int n = 0; n < N; n++) {
            //System.out.println("---------------\nn:"+n);
            int index = Arrays.binarySearch(arr, 0, length, as[n][1]);
            //System.out.println("index:"+index);
            if (index < 0) {
                index = -index - 1;
            }
            arr[index] = as[n][1];
            if (index == length) length++;
            //System.out.println(Arrays.toString(arr));
        }
        //System.out.println(N - length);
    }

//    static void print(int N, int[][] as) {
//        for (int n = 0; n < N; n++) {
//            System.out.println(Arrays.toString(as[n]));
//        }
//    }
}
