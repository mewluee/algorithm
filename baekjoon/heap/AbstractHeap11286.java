package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class AbstractHeap11286 {


    private String site = "https://dragon-h.tistory.com/3";

    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> qu=new PriorityQueue<>((o1, o2)->{
            int a1 = Math.abs(o1);
            int a2 = Math.abs(o2);

            if (a1 == a2) {
                return o1-o2;
            }else{
                return a1-a2;
            }
        });

        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (qu.isEmpty()) {
                    System.out.println(0);
                }else{
                    System.out.println(qu.poll());
                }
            }else{
                qu.add(input);
            }
        }

    }

}
