package 수학_Math;

import java.util.Scanner;

public class 소수찾기_1978 {
    public void result(){

        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int count=0;
        input.nextLine();
        String[] arr=input.nextLine().split(" ");

        int num;
        boolean flag;

        while(n>0){
            num=Integer.parseInt(arr[n-1]);
            //System.out.println("num:"+num);

            if(num==1){
                n--;
                continue;
            }

            flag=true;

            for(int i=2; i<num; i++){
                //System.out.println("i:"+i);
                if(num%i==0){
                    //System.out.println("소수가 아닙니다!");
                    flag=false;
                    break;
                }
            }

            if(flag) count++;
            n--;
        }

        System.out.println(count);
    }
}
