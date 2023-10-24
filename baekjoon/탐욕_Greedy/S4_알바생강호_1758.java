package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class S4_알바생강호_1758 {

    //문제: 42217 실버4 - 로프
    //알고리즘: 정렬
    //요약
    //커피를 몇번재 받는지에 따라 팁을 다른 액수로 강호에게 준다.
    //각 손님의 팁 : 원래 주려고 생각했던 돈 - (받은 등수-1)
    //음수일 경우 팁 0
    //손님의 순서를 적절하게 바꿔서 강호가 받을 수 있는 팁의 최댓값 구하기
    //N:스타벅스 앞에 서 있는 사람 수
    //각 사람이 주려고 하는 팁(1~100,000)

    //등수 1~N등
    //팁이 많은 사람이 앞쪽에..
    //1. 틀렸다. sum의 범위가 너무 작아서 틀린 것 같다.
    //int형은 대략 21억정도다.
    //값 범위를 보면 10,000,000,000 얼추 100억 -> long타입으로 바꾸기
    public static void main(String[] args)throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        Integer[] tips=new Integer[N];
        for(int n=0; n<N; n++) {
            tips[n]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(tips, Collections.reverseOrder());
        long sum=0;
        for(int n=1; n<=N; n++){
            if(tips[n-1]<=n-1) break;
            sum+=tips[n-1]-n+1;
        }
        System.out.println(sum);
    }
}
