
import java.util.*;

public class Main {


    public static void main(String[] args) {

        Scanner input=new Scanner(System.in);

        int count;
        Stack<Character> stack=new Stack<>();
        String str=input.nextLine();

        while(true){
            if(str.equals(".")) break;
            stack.clear();
            count=0;

            // 아~ 스택해야겠는걸~~에반데~


            for(int i=0; i<str.length();i++){
                if(str.charAt(i)=='('){
                    //System.out.print("**1"+","+i+":"+str.charAt(i));
                    stack.push(str.charAt(i));
                    count++;

                }else if(str.charAt(i)=='['){
                    //System.out.print("**2"+","+i+":"+str.charAt(i));
                    stack.push(str.charAt(i));
                    count++;

                }else if(str.charAt(i)==')'){
                    //System.out.print("**3"+","+i+":"+str.charAt(i));

                    if(stack.size()==0) {
                        //System.out.println("스택에 데이터 없음");
                        count=1;
                        break;
                    }

                    if(stack.peek()=='('){
                        stack.pop();
                        count++;
                    }else{
                        count=1;
                        break;
                    }


                }else if(str.charAt(i)==']'){
                    //System.out.print("**4"+","+i+":"+str.charAt(i));

                    if(stack.size()==0) {
                        //System.out.println("스택에 데이터 없음");
                        count=1;
                        break;
                    }

                    if(stack.peek()=='['){
                        stack.pop();
                        count++;
                    }else{
                        count=1;
                        break;
                    }
                    //System.out.println("  count:"+count);
                }
            }

            if(count%2==0) System.out.println("yes");
            else System.out.println("no");

            str=input.nextLine();
        }

    }


}