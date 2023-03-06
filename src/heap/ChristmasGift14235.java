package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ChristmasGift14235 {

    private String site = "https://www.acmicpc.net/problem/14235";

    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> qu=new PriorityQueue<>(Comparator.reverseOrder());

        for (int n = 0; n < N; n++) {

            String input=br.readLine();

            if (input.length() == 1) {
                int num = Integer.parseInt(input);
                if (num == 0) {
                    if(qu.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(qu.poll());
                }
            }else{
                String[] gifts = input.split(" ");
                for(int i=1; i<=Integer.parseInt(gifts[0]); i++){
                    qu.add(Integer.parseInt(gifts[i]));
                }
            }

        }

    }
}
