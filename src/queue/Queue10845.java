package queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Queue10845 {

    private String site="https://www.acmicpc.net/problem/10845";

    public void result() throws IOException {

        Queue<String> queue=new LinkedList<>();

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int numCommand=Integer.parseInt(br.readLine());

        for(int i=0; i<numCommand; i++){
            String[] strarr=br.readLine().split(" ");
            String command=strarr[0];

            if(command.equals("push")){
                queue.add(strarr[1]); //출력 없음
            }else if(command.equals("pop")){
                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
                else bw.write(queue.poll().toString()+"\n");
            }else if(command.equals("size")){
                bw.write(Integer.toString(queue.size())+"\n");
            }else if(command.equals("empty")){
                if(queue.isEmpty()) bw.write(Integer.toString(1)+"\n");
                else bw.write(Integer.toString(0)+"\n");
            }else if(command.equals("front")){
                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
                else bw.write(queue.peek().toString()+"\n");
            }else if(command.equals("back")){
                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
                else{//스트링 배열로 만들어서 제일 마지막값 출력
                    String[] strArrQueue=queue.stream().toArray(String[]::new);
                    bw.write(strArrQueue[strArrQueue.length-1]+"\n");
                }

            }
        }

        bw.close();
    }
}
