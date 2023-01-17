package stack;

import java.io.*;
import java.util.Stack;

public class Stack10828 {

    private String site="https://www.acmicpc.net/problem/10828";

    public void result() throws IOException {

        Stack<String> stack=new Stack<>();

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int numCommand=Integer.parseInt(br.readLine());

        for(int i=0; i<numCommand; i++){
            String[] strarr=br.readLine().split(" ");
            String command=strarr[0];

            if(command.equals("push")){
                stack.push(strarr[1]); //출력 없음
            }else if(command.equals("pop")){
                if(stack.isEmpty()) bw.write(Integer.toString(-1)+"\n");
                else bw.write(stack.pop().toString()+"\n");
            }else if(command.equals("size")){
                bw.write(Integer.toString(stack.size())+"\n");
            }else if(command.equals("empty")){
                if(stack.isEmpty()) bw.write(Integer.toString(1)+"\n");
                else bw.write(Integer.toString(0)+"\n");
            }else if(command.equals("top")){
                if(stack.isEmpty()) bw.write(Integer.toString(-1)+"\n");
                else bw.write(stack.peek().toString()+"\n");
            }
        }

        bw.close();

    }


}
