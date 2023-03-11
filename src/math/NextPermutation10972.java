package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NextPermutation10972 {

    String site="https://www.acmicpc.net/problem/10972";

    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        String[] strs = br.readLine().split(" ");
        for (int n = 0; n < N; n++) {
            numbers[n] = Integer.parseInt(strs[n]);
        }



        //로직
        //1.오른쪽부터 바로 이전수와 크기 비교 : i-1 < i
        int i = numbers.length - 1; // 맨 마지막 인덱스(length-1)부터 비교
        while (i>0 && numbers[i - 1] >= numbers[i]) {
            i--;
        }//n[i-1] < n[i]일 때 반복문 탈출한다.

        //1-(1) 결과적으로 i-1 를 제외한 i부터 length-1까지의 데이터는 a>b>c 즉 내림차순 형태를 띄게된다.
        //      ex. 4 2 3 6 5 1
        //      4 2 3(i-1) < 6(i) 5 1
        //      4 2 3(i-1) < [잘라서 오른쪽 데이터만 보면] 6(i) > 5 > 1 (내림차순 형태 : 즉 1 5 6 숫자로 가질 수 있는 최대값을 갖는 순열 형태)
        //      즉, [4 2]를 제외한 [3 6 5 1] 은 3(i-1)을 기준으로 가질 수 있는 최대값인 순열 형태이다.
        //      다음으로 큰 순열을 구할려면 [3]과 [6 5 1 중 3보다 큰 제일 첫번째 수]를 찾아서 자리를 교환해줘야한다.(남은 2,3번 과정)


        //1-(2) i가 0이란건 끝까지 비교했고 결과적으로 제일 큰 수이다.
        if (i == 0) {
            System.out.println(-1);
            return;
        }


        //2.[i-1]인덱스의 수보다 큰 제일 첫번째 수의 인덱스 찾기
        int o = numbers.length - 1; // 제일 오른쪽부터 비교
        while (numbers[i - 1] >= numbers[o]) {
            o--;
        }//n[i-1] < n[o]일 때 반복문 탈출한다.

        //2-(1) 내림차순 된 데이터에서 오른쪽부터 비교했단 건 작은 수부터 비교했다는 것이다.
        //      즉 3(i-1)보다 큰 제일 첫번째 수의 인덱스를 찾는 것이다
        //      결과적으로 여기서는 6 5 1 중 5가 제일 첫번째로 3보다 큰 수이다.
        //      다시말하면 다음 순열로 정렬해야하는 기준(o)을 찾은 것이다.

        //2-(2) 왼쪽부터 비교했다면 6을 찾을 것이다. 그렇게 되면 다음으로 큰 순열이 될 수 없다.
        //      3 6 5 1 < 5 1 3 6 : 바로 다음 큰 순열이다.
        //      3 6 5 1 < ... < 6 1 3 5 : 바로 다음 큰 순열이 아니다.

        //3.swap해주기
        swap(numbers,i-1,o);
        //다음으로 큰 순열로 만들기 위해 원래 기준(i-1)을 다음 기준(o)이랑 바꿔주면 된다.
        //3(i-1) -> 5(o)

        //4.바꿔준 기준(i-1)을 제외한 수들을 다시 정렬해야 한다.
        //  어떻게 정렬? 기준을 제외한 수들로 만들 수 있는 제일 작은 순열(이렇게 해야 바로 다음 큰 순열이다)
        o = numbers.length - 1;
        while(i<o){
            swap(numbers,i,o);
            i++;
            o--;
        }
        //4-(1) 왼쪽 끝과 오른쪽 끝을 인덱스값을 비교하면서 자리를 swap하는 방법으로 정렬이 가능한 이유는
        //      기준(i-1)을 제외한 오른쪽 값들은 늘 정렬된 데이터이기 때문이다.
        //      (전) 3(i-1) [6 > 5 > 1] -> (후) 5(i-1) [6 > 3 > 1]
        //           기준을 swap해준 이후에도 정렬된 데이터(내림차순)다.
        //      그래서 따로 정렬 알고리즘을 할 필요없이 바로바로 끝과 끝을 교환해주면 된다.

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
