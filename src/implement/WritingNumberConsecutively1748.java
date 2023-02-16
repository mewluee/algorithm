package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WritingNumberConsecutively1748 {

    private String site = "https://www.acmicpc.net/problem/1748";

    public void result(){

        //1748번 수 이어쓰기1
        //1부터 N까지의 수 이어쓰면 하나의 새로운 수 >> 그 수가 몇자리수인지?
        //구현자체는...음
        //1의 자리 수 1~9 > 1 / 9개(9 * 10^(1-1)) x 1 = 9
        //2의 자리 수 10~99 > 2 / 90개(9 * 10^(2-1)) x 2 = 180
        //3의 자리 수 100~999 > 3 / 900개(9 * 10^(3-1)) x 3 = 2700
        //모듈화..

        //예를들어 120라고 하면..
        //1. 입력받는 숫자의 문자열의 길이를 가져온다 3이지. > 그럼 1,2자리수의 개수는 정해짐
        //1의 자리수 일때 9개
        //2의 자리수 일때 180개
        //3의 자리수 일때 120-100=20+1=21 x3 = 63
        //180+9+63=252 좋아!

        //입력받는 수의 자리수를 받아서
        //for문 돌리는데 자리수보다 -1 해서 돌려가지고 함수 호출하기
        //나머지 1은 빼가지고 알아서 뭐...ㅇ...그래..


        Scanner s=new Scanner(System.in);
        String N=s.nextLine();
        //3자리수면 1 2 가 실행되야함!
        double result=0;

        for(int n=1; n<N.length(); n++){
            result=result+numberOfDigitsCount(n);
        }

        result=result+theOtherCount(Integer.parseInt(N),N.length());

        System.out.println(String.format("%.0f",result));
    }


    public double numberOfDigitsCount(int len){
        return 9 * Math.pow(10,len-1) * len;
    }

    public double theOtherCount(int num, int len){
        return (((double)num)-Math.pow(10,len-1)+1)*len;
    }




    //다른사람 푼거1
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringN = br.readLine();
        int len = stringN.length();
        int N = Integer.parseInt(stringN);
        long answer = 0;

        for(int i = 1; i <= len; i++) {
            if(i < len) {
                answer += 9 * (long)Math.pow(10, i-1) * i;
            }else {
                answer += (N - (long)Math.pow(10, i-1) + 1) * len;
            }
        }

        System.out.println(answer);

    }

    //다른사람 푼거2
    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;

        int n = Integer.parseInt(br.readLine());

        // n이 120이라고하면
        // n - 1 + 1-> 1이상 n이하 까지 1의 자리수를 가진 수의 개수
        // n - 10 + 1-> 1이상 n이하 까지 10의 자리수를 가진 수의 개수
        // n - 100 + 1-> 1이상 n이하 까지 100의 자리수를 가진 수의 개수
        for(int i =1; i <= n; i*=10){
            answer += n - i + 1;
            //n=120이면
            //i=1일때 120-1+1=120 // 120숫자의 1의 자리들의 숫자 개수셈.
            //i=10일때 120-10+1=111 // 10, 11, 12, 13 ,,,, 120까지 개수셈. 십의 자리숫자만.
            //i=100일때 120-100+1=21 // 즉 100, 101, 102 ... 120까지 개수 셈>> 백의 자리수 세기.
        }
        System.out.println(answer);
    }

    //다른사람 푼거3-stringbuilder
    public void solution3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder numLength = new StringBuilder();
        String num = br.readLine();
        long count = 0;

        if (num.length() > 1) {
            sb.append(9);
            numLength.append(1);
            System.out.println(sb+","+numLength);
            for (int i = 0; i < num.length() - 1; i++) {
                count += Long.parseLong(sb.toString()) * (i + 1);
                //sb:9->90->900
                //num:1->10->100
                //count:9+180+2700..
                sb.append(0);
                numLength.append(0);
            }
            long value = ((Long.parseLong(num) - Long.parseLong(numLength.toString())) + 1) * num.length();

            count += value;
        } else {
            count += Long.parseLong(num);
        }

        System.out.println(count);
    }



}
