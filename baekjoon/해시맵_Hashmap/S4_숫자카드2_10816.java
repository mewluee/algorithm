package 해시맵_Hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S4_숫자카드2_10816 {
    //문제: 실버4 숫자카드2
    //요약:
    //숫자카드 N개 가지고있다
    //정수 M개가 주어졌을때 이 수가 적혀있는 숫자카드를 상근이가 몇개 가지고있는지 구하라
    //카드 N 1~500000 개수 -10,000,000 ~ 10,000,000 숫자들
    //숫자 M 1~500000 개수 -10,000,000 ~ 10,000,000
    //풀이:해쉬맵

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int m = 0; m < M; m++) {
            int key = Integer.parseInt(st.nextToken());
            sb.append(map.getOrDefault(key, 0) + " ");
        }
        System.out.print(sb.toString());
    }
}
