package 조합_Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이항계수1_11050 {
    static int a;
    static int b;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        answer=0;
        combi(0, 1);
        System.out.println(answer);
    }

    static void combi(int count, int input){
        if(b==count) {
            answer++;
            return;
        }
        for (int i = input; i <= a; i++) {
            combi(count+1, i+1);
        }
    }
}
