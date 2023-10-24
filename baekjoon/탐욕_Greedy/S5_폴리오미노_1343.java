package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_폴리오미노_1343 {

    //문제: 1343 실버5 - 폴리오미노
    //.은 덮을 수 없다.
    //X가 4개일 경우 AAAA 혹은 BBBB 로 만들 수 있다.
    //X가 2개일 경우 BB만 가능하다.
    //사전순으로 가장 앞서는 답을 출력하는 거니까, 4개 자리는 무조건 AAAA로 채워야한다.
    //덮을 수 없으면 -1 출력
    //X를 만나면 몇개있는지 센다 -> 홀수면 불가능
    //짝수면 4를 나눈 몫이 AAAA의 개수
    //짝수면 4를 나눈 나머지/2가 BB의 개수
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input=br.readLine();
        int length=input.length();
        int index=0;
        int xCount=0;
        while(index<length) {
            //System.out.println("index:"+index);
            if(input.charAt(index)=='.') {
                if(xCount%2==1) { //홀수는 불가능
                    System.out.println(-1);
                    return;
                }else {
                    //짝수면
                    //System.out.println("xc:"+xCount);
                    if(xCount!=0) {
                        int aCount=xCount/4;
                        int bCount=(xCount%4)/2;
                        int start=index-xCount;
                        //System.out.println("ac:"+aCount+", bc:"+bCount+", start:"+start);
                        String before="";
                        if(start!=0) before=input.substring(0,start);
                        String mid="AAAA".repeat(aCount)+"BB".repeat(bCount);
                        String after=input.substring(index+1, input.length());

                        //System.out.println("b:"+before);
                        //System.out.println("m:"+mid);
                        //System.out.println("a:"+after);

                        input=before+mid+"."+after;
                        //System.out.println(input);
                        xCount=0;
                    }
                }
            }else {
                xCount++;

            }
            index++;
        }
        if(xCount%2!=0) System.out.println(-1);
        else{
            if(xCount!=0) {
                int aCount=xCount/4;
                int bCount=(xCount%4)/2;
                int start=index-xCount;
                //System.out.println("ac:"+aCount+", bc:"+bCount+", start:"+start);
                String before="";
                if(start!=0) before=input.substring(0,start);
                String mid="AAAA".repeat(aCount)+"BB".repeat(bCount);

                //System.out.println("b:"+before);
                //System.out.println("m:"+mid);

                input=before+mid;
                System.out.println(input);
            }else {
                System.out.println(input);
            }
        }
    }
}
