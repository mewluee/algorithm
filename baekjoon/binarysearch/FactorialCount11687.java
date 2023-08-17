package binarysearch;

import java.util.Scanner;

public class FactorialCount11687 {

    public void result(){

        Scanner scanner=new Scanner(System.in);
        //팩토리얼은 그건뎅
        //5! = 1 x 2 x 3 x 4 x 5
        //0이 생길 조건은 2x5
        //5를기준으로 앞뒤로 4 6 이라서 반드시 2가 곱해지지롱?
        //브루트포스하면 당연히 시간초과로 안되겠지 이건 촉이다.
        //어 이건 알고리즘이 생각나지 않아요~~~
        //왜 이분탐색이냐..?
        //도시떼..?
        int M=Integer.parseInt(scanner.nextLine());
/*
        long min=M;
        long max=M*5;
        long result=0;

        while(min<max){
            long mid=(min+max)/2;
            long facto=1;
            for(long i=2; i<=mid; i++){
                facto=facto*i;
            }

            long count=0;
            String factoSTR=Long.toString(facto);
            System.out.println(factoSTR);
            for(int j=factoSTR.length()-1; j>=0; j--){
                if(factoSTR.charAt(j)!='0') break;
                count++;
            }

            if(M==count) result=mid;
            else if(M<count) max=mid-1;
            else min=mid+1;
        }

        System.out.println(result);*/

        //5의 개수에 따라 0이 증가한다
        int count=0;

        while(M>0){
            if(M%5==0){
                M=M/5;
                count++;
            }
        }
        System.out.println(count);
    }
}
