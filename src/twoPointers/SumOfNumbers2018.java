package twoPointers;

import java.util.Scanner;

public class SumOfNumbers2018 {


        public static void main(String[] args) {

            int N=new Scanner(System.in).nextInt();
            int s=0;
            int e=0;
            int sum=0;
            int count=0;

            while(s<N){
                if(sum<N){
                    sum+=++e;
                }else{
                    sum-=++s;
                }
                if(sum==N){
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}
