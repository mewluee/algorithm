package stack;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Stack10828 {

    private String site="https://www.acmicpc.net/problem/10828";

    //스택으로 구현/하단에 배열로 구현한거 있음.
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

    //배열로 구현
    public void result2 () throws IOException {

        // 배열로 만들기
        int[] stack=new int[]{}; // []
        //System.out.println(Arrays.toString(stack));

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int numCommand=Integer.parseInt(br.readLine());

        for(int i=0; i<numCommand; i++){
            String[] strarr=br.readLine().split(" ");
            String command=strarr[0];

            if(command.equals("push")){
                //stack.push(strarr[1]); //출력 없음
                //System.arraycopy(src배열, scr시작인덱스, dest배열, dest시작인덱스, src복사개수인덱스)
                //Arrays.copyOf(src배열,복사요소개수) // 위치지정없이 처음부터 복사됨
                //새배열 만들고
                //arraycopy로 새배열에 기존배열을 복사해넣고 > 새 값 넣기.
                int[] sample=Arrays.copyOf(stack,stack.length+1); // 새 배열을 만드는데, 기존 stack배열의 크기보다 +1 크게 만듬. 0으로 채워짐.
                //System.out.println("입력 전:"+Arrays.toString(stack));
                sample[sample.length-1]=Integer.parseInt(strarr[1]); // 새로만든 공간에 값을 넣는다.
                stack=sample; // 기존 stack 배열 갱신
                //System.out.println("입력 후:"+Arrays.toString(stack));
                //출력없음

            }else if(command.equals("pop")){
//                if(stack.isEmpty()) bw.write(Integer.toString(-1)+"\n");
//                else bw.write(stack.pop().toString()+"\n");
                // 배열이 비어있으면 -1 출력
                // 아니면 항상 배열의 마지막 인덱스를 제거한다.
                // 제거하고 배열의 크기를 줄여준다.

                if(stack.length==0) bw.write(Integer.toString(-1)+"\n"); // 빈 배열이면 -1 출력
                else{
                    //System.out.println("출력 전:"+Arrays.toString(stack));
                    int getValue=stack[stack.length-1];
                    bw.write(Integer.toString(getValue)+"\n");
                    stack=Arrays.copyOf(stack,stack.length-1); // 기존 stack 배열 = 새 배열을 만드는데 기존 stack 배열킈 크기보다 -1 작게 복사해서 만듬 > 갱신
                    //System.out.println("출력 후:"+Arrays.toString(stack));

                }

            }else if(command.equals("size")){
//                bw.write(Integer.toString(stack.size())+"\n");
                bw.write(Integer.toString(stack.length)+"\n");

            }else if(command.equals("empty")){
//                if(stack.isEmpty()) bw.write(Integer.toString(1)+"\n");
//                else bw.write(Integer.toString(0)+"\n");

                if(stack.length==0) bw.write(Integer.toString(1)+"\n");
                else bw.write(Integer.toString(0)+"\n");


            }else if(command.equals("top")){
//                if(stack.isEmpty()) bw.write(Integer.toString(-1)+"\n");
//                else bw.write(stack.peek().toString()+"\n");

                if(stack.length==0) bw.write(Integer.toString(-1)+"\n");
                else bw.write(Integer.toString(stack[stack.length-1])+"\n");

            }
        }

        bw.close();
    }


}
