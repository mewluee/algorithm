package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Stack;

public class StringBomb9935 {

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        String bomb=br.readLine();

        //01234567891011121314
        //mirkovC4ni z C C 4 4
        //C4가 포함된 인덱스가 6랑 12
        //만약 내가 6을 없애면(6포함 7 총 2개)
        //0123456789101112
        //mirkovnizC C 4 4
        //12도 10이됨.-2되서.


        //한글자씩 비교할수밖에 없나요?
        //쓸수있는 자료구조있을까요?
        //스택 큐

        //폭탄의 앞글자면 일단 꺼내..?
        //

        Stack<Character> stack=new Stack<Character>();
        char[] charArr=str.toCharArray();
        int index=0;
        String checkStr=str;

        while(checkStr.contains(bomb)){

            while(index<charArr.length){

                char one=charArr[index];
                //System.out.println("char one:"+one);
                stack.add(charArr[index]);

                if(one==bomb.charAt(0)){ //폭탄의 앞글자면

                    String checkBomb="";

                    for(int i=0; i<bomb.length(); i++){
                        checkBomb=checkBomb+charArr[index+i];
                    }
                    //System.out.println("bomb확인:"+checkBomb);

                    if(bomb.equals(checkBomb)){//폭탄이 맞으면 해당 문자까지 집어넣지 않아야함.
                        //0 1 (2) (3) 4
                        //0 1 index+length
                        stack.pop();
                        index=index+bomb.length()-1;
                        //System.out.println("index:"+index);
                    }
                }

                //System.out.println(">>"+Arrays.toString(stack.toArray()));
                index++;
            }


            checkStr="";
            Iterator iterator=stack.iterator();
            while(iterator.hasNext()){
                checkStr=checkStr+iterator.next();
            }
            //System.out.println("checkStr:"+checkStr);
            charArr=checkStr.toCharArray();
            //System.out.println("charArr:"+Arrays.toString(charArr));
            index=0;
            stack.clear();
        }

        if(checkStr.length()==0){
            System.out.println("FRULA");
        }else{
            System.out.println(checkStr);
        }

    }
}
