package 파싱_Parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 잃어버린괄호_1541 {

    private String site="https://www.acmicpc.net/problem/1541";

    public void result() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //값을 최소로 만든다는 건 빼기 기준으로 늘 오른쪽이 커야지 최소가 된다.
        //다행이도 연산자가 단 두개!
        //플러스일때는 그냥 무지성으로 더하면 되지만
        //마이너스일때 적절하게 괄호를 넣어줘야한다.
        //마이너스일때! 다른 마이너스 만날때까지....넣

        //숫자를 반환하는 모듈 생성하기 > 0으로 시작하는 숫자 처리하는 용
        //스택에 순서대로 숫자를 넣을건뎅.
        //스택에 있는건 무조건 다 더한다고 생각했을 때
        //마이너스를 만나면 해당 숫자를 음수로 만들어서 스택에 넣는다 > 그럼 다 더할 수 있다.
        //마이너스일때 다음 마이너스를 만날때까지 스택에 넣었던 걸 빼서 더한다음에 스택에 넣기.

        //리스트(스트링)로 만들어서 숫자랑 연산자 저장하기 (얼마나 입력할지 몰라서 배열 안씀)
        //리스트에 있는 걸 하나씩 꺼내서 스택에 넣는다?
        //스택에 넣고 )를 만나면 (나올떄까지 pop...
        //괄호를 리스트에 넣고 스택에 돌릴거니??

        //실제로 굳이 (를 넣어서 연산을 더할 필욘 없을 것 같당.

        //괄호 넣을 조건
        //1) -만나면 그 이후 - 전까지
        //2) 20+10-30+40-50+140-680-340-240+100+480
        //3) 30 - 30
        String str=br.readLine();
        char[] chars=str.toCharArray();

        List<String> list=new ArrayList<>();

        String subStr="";

        for(int i=0; i<chars.length; i++){

            if(chars[i]=='-' || chars[i]=='+'){ //연산자가 뜨면
                list.add(subStr);//앞에서 누적해서 만든 숫자 넣고
                subStr="";
                list.add(Character.toString(chars[i]));//연산자 넣기
            }else{
                subStr=subStr+chars[i];//누적해서 숫자 만들기
            }
        }

        list.add(subStr);

        //System.out.println(Arrays.toString(list.toArray()));

        //리스트에 잘 들어갔는지 확인했음.
        //그다음엔 마이너스 만나면 다음 마이너스까지 나오는 숫자를 다 더하는 !!!
        //로직을 짜볼까요!!
        //일단 숫자로 만들어주는 함수 만들기

        //Stack<Integer> stack=new Stack<>();
        int result=0;
        for(int n=0; n<list.size(); n++){
            if(list.get(n).equals("+")){
//                int plusSum=stack.pop()+stringToInt(list.get(++n));
//                stack.push(plusSum);
                result=result+stringToInt(list.get(++n));

            }else if(list.get(n).equals("-")){
                //for문 > n다음 인덱스부터 다시 - 나올때까지 검색하자.
                int index=list.size()-1; //끝까지 없을수있음.
                for(int m=n+1; m<list.size(); m++){
                    if(list.get(m).equals("-")){
                        index=m-1; //m이 마이너스니까 m-1까지해야 숫자까지.
                        break;
                    }
                }
                int minusSum=subPlus(list,n+1,index);
                //System.out.println(minusSum);
                n=index; //index까지 검사한거니까 n인덱스값 변경해줘야함.

//                int sum2=stack.pop()-minusSum;
//                stack.push(sum2);
                result=result-minusSum;

            }else{
                result=stringToInt(list.get(n)); //숫자

            }
        }



        //System.out.println(stack.pop());
        System.out.println(result);

    }


    public static int subPlus(List<String> list,int start,int end){
        int sum=0;
        for(int i=start; i<=end; i++){
            if(!list.get(i).equals("+")){
                sum=sum+stringToInt(list.get(i));
            }
        }
        return sum;
    }

    public static int stringToInt(String str){
        //맨 처음값이 0일때만 문제.
        if(str.charAt(0)=='0'){ //0이 아닌 수가 나오면 noZeroNumber에 붙여서 숫자로 반환 00109 일땐..?
            String noZeroNumber="";
            int index=1; //어처피 0번 인덱스는 확정으로 0이라서 일단 초기값 1로 저장함.
            for(int i=1; i<str.length(); i++){
                if(str.charAt(i)!='0'){
                    //0이 안나오는 인덱스부터 그 후의 인덱스를 뽑아내야함.
                    index=i;
                    break;

                }
            }
            noZeroNumber=new String(Arrays.copyOfRange(str.toCharArray(),index,str.length()));
            // 0 1 2 3 4 5
            // index가 2라고 치면 2~5까지 뽑아내야하니까
            //4개 / 길이 6 -2
            //System.out.println(noZeroNumber);
            return Integer.parseInt(noZeroNumber);
        }else{

            return Integer.parseInt(str);
        }
    }


    public void resultMain(){
        /*


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //값을 최소로 만든다는 건 빼기 기준으로 늘 오른쪽이 커야지 최소가 된다.
        //다행이도 연산자가 단 두개!
        //플러스일때는 그냥 무지성으로 더하면 되지만
        //마이너스일때 적절하게 괄호를 넣어줘야한다.
        //마이너스일때! 다른 마이너스 만날때까지....넣

        //숫자를 반환하는 모듈 생성하기 > 0으로 시작하는 숫자 처리하는 용
        //스택에 순서대로 숫자를 넣을건뎅.
        //스택에 있는건 무조건 다 더한다고 생각했을 때
        //마이너스를 만나면 해당 숫자를 음수로 만들어서 스택에 넣는다 > 그럼 다 더할 수 있다.
        //마이너스일때 다음 마이너스를 만날때까지 스택에 넣었던 걸 빼서 더한다음에 스택에 넣기.

        //리스트(스트링)로 만들어서 숫자랑 연산자 저장하기 (얼마나 입력할지 몰라서 배열 안씀)
        //리스트에 있는 걸 하나씩 꺼내서 스택에 넣는다?
        //스택에 넣고 )를 만나면 (나올떄까지 pop...
        //괄호를 리스트에 넣고 스택에 돌릴거니??

        //실제로 굳이 (를 넣어서 연산을 더할 필욘 없을 것 같당.

        //괄호 넣을 조건
        //1) -만나면 그 이후 - 전까지
        //2) 20+10-30+40-50+140-680-340-240+100+480
        //3) 30 - 30
        String str=br.readLine();
        char[] chars=str.toCharArray();

        List<String> list=new ArrayList<>();

        String subStr="";

        for(int i=0; i<chars.length; i++){

            if(chars[i]=='-' || chars[i]=='+'){ //연산자가 뜨면
                list.add(subStr);//앞에서 누적해서 만든 숫자 넣고
                subStr="";
                list.add(Character.toString(chars[i]));//연산자 넣기
            }else{
                subStr=subStr+chars[i];//누적해서 숫자 만들기
            }
        }

        list.add(subStr);

        //System.out.println(Arrays.toString(list.toArray()));

        //리스트에 잘 들어갔는지 확인했음.
        //그다음엔 마이너스 만나면 다음 마이너스까지 나오는 숫자를 다 더하는 !!!
        //로직을 짜볼까요!!
        //일단 숫자로 만들어주는 함수 만들기

        //Stack<Integer> stack=new Stack<>();
        int result=0;
        for(int n=0; n<list.size(); n++){
            if(list.get(n).equals("+")){
//                int plusSum=stack.pop()+stringToInt(list.get(++n));
//                stack.push(plusSum);
                result=result+stringToInt(list.get(++n));

            }else if(list.get(n).equals("-")){
                //for문 > n다음 인덱스부터 다시 - 나올때까지 검색하자.
                int index=list.size()-1; //끝까지 없을수있음.
                for(int m=n+1; m<list.size(); m++){
                    if(list.get(m).equals("-")){
                        index=m-1; //m이 마이너스니까 m-1까지해야 숫자까지.
                        break;
                    }
                }
                int minusSum=subPlus(list,n+1,index);
                //System.out.println(minusSum);
                n=index; //index까지 검사한거니까 n인덱스값 변경해줘야함.

//                int sum2=stack.pop()-minusSum;
//                stack.push(sum2);
                result=result-minusSum;

            }else{
                result=stringToInt(list.get(n)); //숫자

            }
        }



        //System.out.println(stack.pop());
        System.out.println(result);


    }

    public static int subPlus(List<String> list,int start,int end){
        int sum=0;
        for(int i=start; i<=end; i++){
            if(!list.get(i).equals("+")){
                sum=sum+stringToInt(list.get(i));
            }
        }
        return sum;
    }

    public static int stringToInt(String str){
        //맨 처음값이 0일때만 문제.
        if(str.charAt(0)=='0'){ //0이 아닌 수가 나오면 noZeroNumber에 붙여서 숫자로 반환 00109 일땐..?
            String noZeroNumber="";
            int index=1; //어처피 0번 인덱스는 확정으로 0이라서 일단 초기값 1로 저장함.
            for(int i=1; i<str.length(); i++){
                if(str.charAt(i)!='0'){
                    //0이 안나오는 인덱스부터 그 후의 인덱스를 뽑아내야함.
                    index=i;
                    break;

                }
            }
            noZeroNumber=new String(Arrays.copyOfRange(str.toCharArray(),index,str.length()));
            // 0 1 2 3 4 5
            // index가 2라고 치면 2~5까지 뽑아내야하니까
            //4개 / 길이 6 -2
            //System.out.println(noZeroNumber);
            return Integer.parseInt(noZeroNumber);
        }else{

            return Integer.parseInt(str);
        }
    }



}
         */
    }
}
