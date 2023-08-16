package programmers;

import java.lang.reflect.Array;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;

public class n제곱배열자르기 {

    public static void main(String[] args) {
        solution(4, 1, 3);
    }

    public static int[] solution(int n, long left, long right) {
        int[] answer = new int[n*n];

        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                //System.out.println("i:"+i+", j:"+j);
                //map[j][i]=i+1;
                answer[j*n+i]=i+1;
                //map[i][j]=i+1;
                answer[i*n+j]=i+1;

            }
        }

        //System.out.println(Arrays.deepToString(map));
        //System.out.println(Arrays.toString(answer));
        return Arrays.copyOfRange(answer, (int) left, (int)(right-left)+1);
    }
}
