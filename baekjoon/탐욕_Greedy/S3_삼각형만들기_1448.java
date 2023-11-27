package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class S3_삼각형만들기_1448 {

    //문제: 실버3 삼각형 만들기
    //요약:
    //N개 중 3개의 빨대를 선택해서 삼각형 만드는데 세변의 길이의 합의 최댓값
    //만들 수 없으면 -1

    //풀이:
    //삼각형의 조건->세변의 길이가 주어졌을떄, 가장 긴변의 길이는 다른 두변의 길이의 합보다 작아야 삼각형을 그릴 수 있다.
    //정렬 후 경우의 수
    //최댓값을 뽑으니까 정렬해서 하나씩 비교해도 ok

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); //빨대의 수
        Integer[] straws = new Integer[N];
        for (int n = 0; n < N; n++) {
            straws[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(straws, Collections.reverseOrder());
        //System.out.println(Arrays.toString(straws));

        for (int n = 0; n <= N - 3; n++) {
            if (straws[n] < straws[n + 1] + straws[n + 2]) {
                System.out.println(straws[n] + straws[n + 1] + straws[n + 2]);
                return;
            }
        }
        System.out.println("-1");
    }

}
