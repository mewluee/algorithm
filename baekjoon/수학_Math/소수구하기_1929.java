package 수학_Math;

import java.io.*;
import java.util.StringTokenizer;

public class 소수구하기_1929 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int M=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());

        for(int i=M; i<=N; i++) {
            if(i==1) continue;
            if(check(i)) bw.append(i+"\n");
        }

        bw.flush();
        bw.close();
    }

    //에라토스테네스의 체 -> 소수를 구할때 1~N까지 범위가 아니라, 제곱근 범위까지 해서 구한다.
    static boolean check(int num) {

        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num%i==0) return false;
        }
        return true;
    }
}
