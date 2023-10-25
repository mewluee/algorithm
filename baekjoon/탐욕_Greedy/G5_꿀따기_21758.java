package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class G5_꿀따기_21758 {
    //문제: 21759 골드5 - 꿀 따기
    //알고리즘: 그리디?..
    //요약
    //N개의 장소
    //서로 다른 두 곳을 골라 벌 한마리씩 (연한 회색)
    //또 다른 한 곳에 벌통 (진한 회색)
    //장소에 있는 숫자는 꿀의 양
    //한 번 지나가면 모든 꿀을 다 캐간다. 다른 벌이 캐가도 캐간다.
    //시작 위치의 꿀은 다른 벌도 포함하지 않는다. 도착지의 꿀은 포함한다.

    //와 생각이 잘 안나는뎅..
    //제일 많은 꿀이 있는 곳이 경로에 포함되거나 도착지여야한다.
    //누적합
    //브루트포스..?
    //누적합하려면 1,000,000,000 -> 10억 -> int형 표현가능.
    //다른사람꺼 참고함..
    //조건문 3개
    //1. 벌통과 벌을 양쪽에 맨 끝에 고정 -> 벌 위치 선정
    //1-1 벌통 왼쪽 벌 오른쪽
    //1-2 벌통 오른쪽 벌 왼쪽
    //2. 벌을 양쪽에 맨 끝에 고정 -> 벌통 위치 선정
    static int N;
    static int[] sums;
    static int[] inputs;
    static int max;
    public static void main(String[] args)throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        sums=new int[N];
        inputs= Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        sums[0]=inputs[0];
        for(int n=1; n<N; n++) {
            sums[n]=sums[n-1]+inputs[n];
        }
        //System.out.println(Arrays.toString(sums));

        case1();
        case2();
        System.out.println(max);

    }

    public static void case1() {
        //1-1 벌통 왼쪽, 벌1 오른쪽 고정
        //벌2의 위치는 왼쪽 0빼고 오른쪽 N-1뺴야함
        for(int n=1; n<N-1; n++) {
            int bee1=sums[N-2]-inputs[n]; //누적합에서 bee2가 있는 위치의 꿀양만 빼면된다.
            int bee2=sums[n-1]; //자기 위치보다 왼쪽에 있는 꿀들의 누적합을 구한다.
            max=Math.max(max, bee1+bee2);
        }

        //1-2 벌통 오른쪽, 벌1 왼쪽 고정
        for(int n=1; n<N-1; n++) {
            int bee1=sums[N-1]-inputs[0]-inputs[n]; //bee2가 있는 위치의 꿀을 뺴야한다
            int bee2=sums[N-1]-sums[n];
            max=Math.max(max, bee1+bee2);
        }
    }

    public static void case2() {
        //2 벌들을 양끝에 고정하고 벌통의 위치를 선정한다.
        //1은 왼쪽 2는 오른쪽
        for(int n=1; n<N-1; n++) {
            int bee1=sums[n]-sums[0];
            int bee2=sums[N-2]-sums[n-1];
            max=Math.max(max, bee1+bee2);
        }
    }
}
