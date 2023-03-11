package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PreviousPermutation10973 {

    String site = "https://www.acmicpc.net/problem/10973";


    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        String[] strs = br.readLine().split(" ");
        for (int n = 0; n < N; n++) {
            numbers[n] = Integer.parseInt(strs[n]);
        }



        //로직
        //1.오른쪽부터 바로 이전수와 크기 비교 : i-1 > i
        int i = numbers.length - 1; // 맨 마지막 인덱스(length-1)부터 비교
        while (i>0 && numbers[i - 1] <= numbers[i]) {
            i--;
        }//n[i-1] > n[i]일 때 반복문 탈출한다.

        //i가 0이란건 끝까지 비교했고 결과적으로 제일 작은 수이다.
        if (i == 0) {
            System.out.println(-1);
            return;
        }


        //2.[i-1]인덱스의 수보다 큰 제일 첫번째 수의 인덱스 찾기
        int o = numbers.length - 1; // 제일 오른쪽부터 비교
        while (numbers[i - 1] <= numbers[o]) {
            o--;
        }//n[i-1] > n[o]일 때 반복문 탈출한다.


        //3.swap해주기
        swap(numbers,i-1,o);


        //4.바꿔준 기준(i-1)을 제외한 수들을 다시 정렬해야 한다.
        //  어떻게 정렬? 기준을 제외한 수들로 만들 수 있는 제일 작은 순열(이렇게 해야 바로 이전 순열이다)
        o = numbers.length - 1;
        while(i<o){
            swap(numbers,i,o);
            i++;
            o--;
        }

        //5.출력
        for (int n = 0; n < N; n++) {
            System.out.print(numbers[n]+" ");
        }

    }

    public static void swap(int[] numbers, int a, int b) {
        int temp=numbers[b];
        numbers[b]=numbers[a];
        numbers[a]=temp;
    }
}
