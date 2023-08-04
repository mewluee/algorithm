package 스택_Stack;

import java.util.Scanner;
import java.util.Stack;

public class 균형잡힌세상_4949 {

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
                if(str.charAt(i)=='('){ //1
                    stack.push(str.charAt(i));

                }else if(str.charAt(i)=='['){ //2
                    stack.push(str.charAt(i));


                }else if(str.charAt(i)==')'){//3
                    if(stack.size()==0) { // 틀린놈
                        stack.push(str.charAt(i));
                        break;
                    }

                    if(stack.peek()=='(') stack.pop(); // () ㅓㅂ렷.
                    else break;



                }else if(str.charAt(i)==']'){ //4

                    if(stack.size()==0) {
                        stack.push(str.charAt(i));
                        break;
                    }

                    if(stack.peek()=='[') stack.pop();
                    else break;

                }
            }

            if(stack.isEmpty()) System.out.println("yes");
            else System.out.println("no");



        }
    }
}
