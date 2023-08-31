package 스택_Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 제로_10773 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine());
        Stack<Integer> stack=new Stack<>();
        for(int n=0; n<N; n++) {
            String str=br.readLine();
            if(str.equals("0")) {
                stack.pop();
            }else {
                stack.add(Integer.parseInt(str));
            }
        }

        long answer=0;
        while(!stack.isEmpty()) {
            answer+=stack.pop();
        }
        System.out.println(answer);
    }
}
