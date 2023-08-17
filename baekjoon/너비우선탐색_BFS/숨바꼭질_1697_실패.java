package 너비우선탐색_BFS;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 숨바꼭질_1697_실패 {

    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" "); //1m당 참외 개수
        int N=Integer.parseInt(str[0]);
        int K=Integer.parseInt(str[1]);
        int min=K-N;

        //너비우선탐색 BFS
        //경우의 수는 걷거나 -1/+1 순간이동 x2
        //K를 넘으면 탐색 그만하고
        //K면 정답

        Queue<Integer[]> queue=new LinkedList<>();
        HashSet<Integer> result=new HashSet<>();

        queue.add(new Integer[]{N,0}); //초기값 넣어주기, 시작점이랑 0초

        while(!queue.isEmpty()){

            Integer[] subin=queue.poll();
            int location=subin[0];
            int time=subin[1];

            System.out.println("loc:"+location+", time:"+time);

            if(location==K) {
                if(min>time) min=time;
                System.out.println("time min:"+min);

            }else if(location<K){
                //1.걷기+1
                queue.add(new Integer[]{location+1,time+1});

                //순간이동하는 조건 만들어야...하나..?
                if(location*2<=K){
                    //2.순간이동x2
                    queue.add(new Integer[]{location*2,time+1});
                }

                //뺴는 조건..만들어..?
                if(Math.abs((location-1)*2-K)<Math.abs(location*2-K)){
                    //0.걷기-1
                    queue.add(new Integer[]{location-1,time+1});
                }

            }else if(location>K){
                //0.걷기-1
                //아니 -1 어케하냐..?..???뭐임
                queue.add(new Integer[]{location-1,time+1});
            }


        }


        bw.write(Integer.toString(min));
        bw.close();
    }
}
