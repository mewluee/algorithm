package bruteforce;

import java.util.Scanner;

public class Decomposition2231 {


    private int getSumNumberOfEach(int n, int length){

        int[] arrX=new int[length];

        // ex 345일 경우
        // ( 345 % 1000 ) = 345 / 100
        // ( 345 % 100 ) = 345 / 10
        // ( 345 % 10 ) = 345 / 1

        //각자리의 숫자 배열로 뽑아내기
        for(int k=length; k>0; k--){
            arrX[k-1]= (n % (int)Math.pow(10,k))/(int)Math.pow(10,k-1);
        }

        //System.out.println(Arrays.toString(arrX));

        int sum=0;
        for(int i=0; i<length; i++){
            sum+=arrX[i];
        }

        //System.out.println("sum:"+sum);
        return sum;
    }


    public void result(){


        Scanner input=new Scanner(System.in);
        String x=input.nextLine();
        int numX=Integer.parseInt(x);

        int numlength=x.length();


//        int[] arrX=new int[num-1];

        /*for(int k=1; k<num; k++){
            arrX[k-1]= (int) Math.pow(10,k);
        }*/


        // 입력값이 몇자리 수인지 확인 length
        // 범위를 좁혀야해
        //  n >>> 216=n+27
        // 4567 = x-36 ~ eㄷㅅㄷ...

        int min=numX;

        for(int i=numX-numlength*9; i<numX;i++){
            // i 를 검색
            //System.out.println("i:"+i);
            int ilength=Integer.toString(i).length();
            int one=i+getSumNumberOfEach(i,ilength);
            //System.out.println("one:"+one);

            if(one==numX){
                //System.out.println("찾음");
                if(min>i){
                    min=i;
                }
            }

        }
        if(min==numX) System.out.println("0");
        else System.out.println(min);


    }
}
