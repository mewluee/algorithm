package 수학_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대공약수와최소공배수_2609 {

    static int GCD;
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int A=Integer.parseInt(st.nextToken());
        int B=Integer.parseInt(st.nextToken());

        cal(A,B);

        System.out.println(GCD);
        System.out.println((A*B)/GCD);
    }

    // 나눠지는 수는 "dividend"
    // 나누는 수는 "divisor"
    // 몫은 "quotient"
    // 나머지는 "remainder"
    static void cal(int dividend, int divisor){

        int quotient=dividend/divisor;
        int remainder=dividend%divisor;
        if(remainder==0){
            GCD=divisor;
        }else{
            cal(divisor, remainder);
        }

    }
}
