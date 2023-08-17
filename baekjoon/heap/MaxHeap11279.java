package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxHeap11279 {


    private String site="https://www.acmicpc.net/problem/11279";

    public static void result2() throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                //제거
                if(!maxQueue.isEmpty()){
                    System.out.println(maxQueue.poll());
                }else{
                    System.out.println(0);
                }

            }else{
                //삽입
                maxQueue.add(input);
                //System.out.println("삽입후:"+Arrays.toString(result));
            }
        }

    }
}
