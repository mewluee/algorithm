package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_오리와박수치는춘배_30404 {
    //문제: 실버4 오리와 박수치는 춘배
    //알고리즘:?
    //요약:
    //오리가 꽥꽥한 시간이 입력
    //입력된 시간 이상 시간+K 이하 시간 내에 한번 이상 박수
    //최소한의 박수 개수 출력
    //풀이:
    //수직선으로 count가 증가하는 경우의 수를 그려서 코드 구현
    //바로 전 입력시간+K로 비교할 수 없어서 배열로 만들고, 인덱스가 변경되는 구간을 지정했다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); //오리 꽥꽥 횟수
        int K = Integer.parseInt(st.nextToken()); //
        int[] ducks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        //System.out.println(Arrays.toString(ducks));
        int[] claps = Arrays.stream(ducks)
                .map(e -> e + K)
                .toArray();
        //System.out.println(Arrays.toString(claps));

        //int duckIndex=0;
        int clapIndex = 0;
        int count = 0;
        //첫번째 입력은 패스
        //오리가 꽥꽥 입력되는 순서는 오름차순으로 입력된다.
        for (int n = 1; n < N; n++) {
            //System.out.println("오리가 꽥꽥한 시간:"+ducks[n]);
            if (ducks[n] > claps[clapIndex]) {
                count++;
                clapIndex = n;
                //clapIndex++;
                //if(claps[clapIndex]<ducks[n]) clapIndex=n;
            }
        }
        System.out.println(count + 1);
    }
}
