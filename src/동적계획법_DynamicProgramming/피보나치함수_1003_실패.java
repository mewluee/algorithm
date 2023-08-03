package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 피보나치함수_1003_실패 {

    static int T;
    static int count_zero;
    static int count_one;

    static int[] dic;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

//        for (int t = 0; t < T; t++) {
//            count_zero=0;
//            count_one=0;
//            fibo2(Integer.parseInt(br.readLine()));
//            System.out.println(count_zero+" "+count_one);
//        }

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            dic = new int[n + 1];
            Arrays.fill(dic, -1);
            dic[0]=0;
            dic[1]=1;
            fibo3(n);
            System.out.println(count_zero + " " + count_one);
        }
    }

    //반환값이 있다. (int)
    static int fibo(int n) {
        if (n == 0) {
            count_zero++;
            return 0;
        } else if (n == 1) {
            count_one++;
            return 1;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
    }

    //반환값이 없다. (void)
    static void fibo2(int n) {
        if (n == 0) {
            count_zero++;
        } else if (n == 1) {
            count_one++;
        } else {
            fibo(n - 1);
            fibo(n - 2);
        }
    }

    //dp 사용해보기
    static void fibo3(int n) {
        if (n == 0) {
            count_zero++;
        } else if (n == 1) {
            count_one++;
        } else {
            if(dic[n]!=-1){
                fibo3(n-2);
                fibo3(n-1);
            }

        }
    }
}
