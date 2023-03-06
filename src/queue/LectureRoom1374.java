package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class LectureRoom1374 {
    private String site = "https://www.acmicpc.net/problem/1374";


    static int N;


    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] lists = new int[N][2];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n2=Integer.parseInt(st.nextToken());
            lists[n2-1] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        //System.out.println(Arrays.deepToString(lists));

        Arrays.sort(lists, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        //System.out.println(Arrays.deepToString(lists));

        //큐에 넣는다
        //최소힙으로 구성(두번째 값 기준)
        //큐에 들어간건 강의실을 뜻한다.

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(lists[0][1]);
        for (int n = 1; n < N; n++) {
            if (queue.peek() <= lists[n][0]) {
                queue.poll();
            }
            queue.offer(lists[n][1]);
        }

        System.out.println(queue.size());

    }
}
