package queue;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Queue10845 {

    private String site="https://www.acmicpc.net/problem/10845";

    //큐 linkedlist로 구현/하단에 배열로 구현함
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

    //배열로 구현
    public void result2() throws IOException {

        //Queue<String> queue=new LinkedList<>();
        int[] queue=new int[10000];
//        System.out.println(Arrays.toString(queue));

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int numCommand=Integer.parseInt(br.readLine());

        for(int i=0; i<numCommand; i++){
            String[] strarr=br.readLine().split(" ");
            String command=strarr[0];

            if(command.equals("push")){
                //queue.add(strarr[1]); //출력 없음
                //stack처럼 새로운 배열 만들어서 추가하고 기존 배열로 갱신
                //System.out.println("입력 전:"+Arrays.toString(queue));
                int[] sample= Arrays.copyOf(queue, queue.length+1);
                sample[sample.length-1]=Integer.parseInt(strarr[1]);
                queue=sample;
                //System.out.println("입력 후:"+Arrays.toString(queue));

            }else if(command.equals("pop")){
//                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
//                else bw.write(queue.poll().toString()+"\n");
                //stack이랑 다르게 이동해줘야함.
                //이번엔 Arrays.copyOfRange를 써서 위치를 지정해서 복사할거임.
                if(queue.length==0) bw.write(Integer.toString(-1)+"\n");
                else{
//                    System.out.println("출력 전:"+Arrays.toString(queue));
                    int getValue=queue[0]; // stack이랑 다르게 맨 앞(선입선출)값을 출력함
                    bw.write(Integer.toString(getValue)+"\n");
                    queue=Arrays.copyOfRange(queue,1,queue.length); // 0번 인덱스빼고 1번 인덱스부터 끝 인덱스전까지 복사
                    //실제 인덱스는 1부터 queue.length-1 까지 복사됨. 즉 맨끝의 매개변수는 복사 개수가 아니라. 어디까지~ 복사하겠다는 인덱스(그런데 -1함)
//                    System.out.println("출력 후:"+Arrays.toString(queue));
                }

            }else if(command.equals("size")){
//                bw.write(Integer.toString(queue.size())+"\n");
                bw.write(Integer.toString(queue.length)+"\n");

            }else if(command.equals("empty")){
//                if(queue.isEmpty()) bw.write(Integer.toString(1)+"\n");
//                else bw.write(Integer.toString(0)+"\n");
                if(queue.length==0) bw.write(Integer.toString(1)+"\n");
                else bw.write(Integer.toString(0)+"\n");

            }else if(command.equals("front")){
//                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
//                else bw.write(queue.peek().toString()+"\n");
                if(queue.length==0) bw.write(Integer.toString(-1)+"\n");
                else bw.write(Integer.toString(queue[0])+"\n");

            }else if(command.equals("back")){
//                if(queue.isEmpty()) bw.write(Integer.toString(-1)+"\n");
//                else{//스트링 배열로 만들어서 제일 마지막값 출력
//                    String[] strArrQueue=queue.stream().toArray(String[]::new);
//                    bw.write(strArrQueue[strArrQueue.length-1]+"\n");
//                }
                if(queue.length==0) bw.write(Integer.toString(-1)+"\n");
                else bw.write(Integer.toString(queue[queue.length-1])+"\n");

            }
        }

        bw.close();

    }
}
