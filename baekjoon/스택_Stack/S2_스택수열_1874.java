package 스택_Stack;

import java.io.*;
import java.util.Stack;

public class S2_스택수열_1874 {

    public static void main(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        //StringBuilder sb=new StringBuilder();
        int N=Integer.parseInt(br.readLine());
        Stack<Integer> stack=new Stack<>();
        int num=1;
        for(int n=0; n<N; n++) {
            int input=Integer.parseInt(br.readLine());
            while(num<=input){
                stack.push(num);
                num++;
                //bw.append("+").append("\n");
                bw.write("+\n");
                //sb.append("+\n");
            }
            if(stack.peek()==input){
                stack.pop();
                if(n==N-1){
                    //bw.append("-");
                    bw.write("-");
                    //sb.append("-");
                }else{
                    //bw.append("-").append("\n");
                    bw.write("-\n");
                    //sb.append("-\n");
                }
            }else{
                System.out.println("NO");
                return;
            }
        }
        //System.out.println(sb.toString());
        bw.flush();
        bw.close();
    }
}
