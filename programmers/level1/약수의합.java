package level1;

import java.io.IOException;

public class 약수의합 {

    //방법1: 1~n 범위
    public static void main(String[] args) throws IOException {
        int n = 4; //
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                answer += i;
            }
        }
        System.out.println(answer);
    }

    //방법2: 1~n의 제곱근 범위
    public static void main2(String[] args) throws IOException {
        int n = 4; //
        int answer = 0;
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n / i == i) answer += i;
                else answer += i + n / i;
            }
        }
        System.out.println(answer);
    }
}
