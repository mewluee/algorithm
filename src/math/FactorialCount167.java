package math;

import java.util.Scanner;

public class FactorialCount167 {
    public void result(){
        Scanner s=new Scanner(System.in);
        int N=Integer.parseInt(s.nextLine());
        int count=0;

        while(N>=5){
            if(N%5==0) {
                N=N/5;
                count++;
            }
        }

    }
}
