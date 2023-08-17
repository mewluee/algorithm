package 수학_Math;

import java.io.*;
import java.util.Arrays;

public class 직각삼각형_4153 {
    private String site="https://www.acmicpc.net/problem/4153";

    public void result() throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //boolean notDone=true;
        while (true) {

            String[] strarr = br.readLine().split(" ");
            int[] nums = Arrays.stream(strarr).mapToInt(Integer::parseInt).toArray();
            int max = 0;
            int maxIndex = 0;
            for (int i = 0; i < nums.length; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                    maxIndex = i;
                }
            }

            if (max == 0) {
                bw.close();
                return;
            }

            long sum = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j != maxIndex) sum = sum + nums[j]*nums[j];
            }

            //System.out.println("mul:"+sum);
            //System.out.println("max:"+max*max);
            if (max * max == sum) {
                bw.write("right\n");
            } else {
                bw.write("wrong\n");
            }

        }
    }
}
