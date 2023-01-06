package balancedWorld4949;

import java.util.Scanner;
import java.util.Stack;

public class BalancedWorld4949 {

    public void result(){
        Scanner input=new Scanner(System.in);

        int count;
        Stack<Character> stack=new Stack<>();
        //String str=input.nextLine();


        while(true){

            String str=input.nextLine();

            if(str.equals(".")) break;
            stack.clear();

            // 아~ 스택해야겠는걸~~에반데~


            for(int i=0; i<str.length();i++){
                if(str.charAt(i)=='('){
                    //System.out.print("**1"+","+i+":"+str.charAt(i));
                    stack.push(str.charAt(i));

                }else if(str.charAt(i)=='['){
                    //System.out.print("**2"+","+i+":"+str.charAt(i));
                    stack.push(str.charAt(i));

                }else if(str.charAt(i)==')'){
                    //System.out.print("**3"+","+i+":"+str.charAt(i));

                    if(stack.size()==0) {
                        stack.push(str.charAt(i));
                        break;
                    }

                    if(stack.peek()=='(')stack.pop();
                    else break;



                }else if(str.charAt(i)==']'){
                    //System.out.print("**4"+","+i+":"+str.charAt(i));

                    if(stack.size()==0) {
                        stack.push(str.charAt(i));
                        break;
                    }

                    if(stack.peek()=='[') stack.pop();
                    else break;

                    //System.out.println("  count:"+count);
                }
            }

            if(stack.isEmpty()) System.out.println("yes");
            else System.out.println("no");



        }
    }
}
