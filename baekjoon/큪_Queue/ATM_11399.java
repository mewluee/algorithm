package 큪_Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class ATM_11399 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] PList = Arrays.stream(br.readLine().split(" "))
                .mapToInt(o->Integer.parseInt(o))
                .toArray();

        br.close();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();


        Arrays.stream(PList)
                .forEach(priorityQueue::add);

        int result= IntStream.rangeClosed(1, N)
                .map(i -> N+1 - i)
                .map(i->priorityQueue.poll()*i)
                .sum();

        System.out.println(result);

    }
}
