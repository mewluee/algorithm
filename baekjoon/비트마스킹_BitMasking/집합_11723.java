package 비트마스킹_BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 집합_11723 {

    static int[] S;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        S = new int[21];
        int x=0;
        for (int m = 0; m < M; m++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];
            if (input.length>1) x = Integer.parseInt(input[1]);
            switch (command) {
                case "add":
                    f_add(x);
                    break;
                case "remove":
                    f_remove(x);
                    break;
                case "check":
                    f_check(x);
                    break;
                case "toggle":
                    f_toggle(x);
                    break;
                case "all":
                    f_all();
                    break;
                case "empty":
                    f_empty();
                    break;
            }
        }

        System.out.println(sb.toString());
    }

    static void f_add(int x) {
        if (S[x] == 0) S[x] = 1;
    }

    static void f_remove(int x) {
        if (S[x] == 1) S[x] = 0;
    }

    static void f_check(int x) {
        if (S[x] == 0) sb.append("0\n");
        else if (S[x] == 1) sb.append("1\n");
    }

    static void f_toggle(int x) {
        if (S[x] == 0) S[x] = 1;
        else if (S[x] == 1) S[x] = 0;
    }

    static void f_all() {
        for (int i = 0; i < 21; i++) {
            S[i] = 1;
        }
    }

    static void f_empty() {
        for (int i = 0; i < 21; i++) {
            S[i] = 0;
        }
    }


}
