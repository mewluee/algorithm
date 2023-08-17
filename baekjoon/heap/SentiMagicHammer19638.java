package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SentiMagicHammer19638 {
    private String site="https://www.acmicpc.net/problem/19638";


    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); //거인 인구수
        int H = Integer.parseInt(st.nextToken()); //센티으 ㅣ키
        int T = Integer.parseInt(st.nextToken()); //망치 횟수제한
        int copyT=T;

        PriorityQueue<Integer> bigs = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> smalls = new PriorityQueue<>(Comparator.reverseOrder());

        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(br.readLine());
            if(input>=H)
                bigs.add(input);
            else
                smalls.add(input);
        }

        while(T>0){
            if (!bigs.isEmpty()) {
                if (bigs.peek() != 1) {
                    int one=bigs.poll()/2;
                    if(one<H)
                        smalls.add(one);
                    else
                        bigs.add(one);
                    //다시넣기

                    T--;
                }else{
                    //1까지왔다는건 다했다는거다!
                    break;
                }

            }else{
                break;
            }
        }

        if (bigs.isEmpty()) {
            System.out.println("YES");
            System.out.println(copyT-T);
        }else{
            System.out.println("NO");
            System.out.println(bigs.poll());
        }




    }

}
