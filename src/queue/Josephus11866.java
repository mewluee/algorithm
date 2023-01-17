package queue;

import java.io.*;
import java.util.LinkedList;

public class Josephus11866 {

    public void result() throws IOException {

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));



        //큐>>LinkedList로 구현


        String[] input=bf.readLine().split(" ");
        int N=Integer.parseInt(input[0]);
        int K=Integer.parseInt(input[1]);

        LinkedList<Integer> list=new LinkedList<>();
        for(int i=0; i<N; i++){
            list.add(i+1);
        }

        int head=0;
        String str="<"; // 결과값 출력용

        while(N>0){
            for(int j=0; j<K-1; j++){

                list.add(list.get(head));
                list.remove();


            }

            str=str+Integer.toString(list.get(head))+", ";
            list.remove();

            N--;
        }

        bw.write(str.substring(0,str.length()-2)+">");
        bw.close();

    }
}
