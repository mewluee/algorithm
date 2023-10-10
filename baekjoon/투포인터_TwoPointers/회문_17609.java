package 투포인터_TwoPointers;

import java.io.*;

public class 회문_17609 {


    //문제
    //회문 0 유사회문 1  일반 문자열 2 출력
    //반례
    //axaaxaa -> 1
    //앞에껄 감소시키면 회문이 아니고, 뒤에껄 감소시키면 회문이다.

    public static void main(String[] args)throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        //System.out.println("answer1:"+isPalindrome("aabba"));
        //System.out.println("answer2:"+isPseudoPalindrome("aabba"));
        int T=Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            String str=br.readLine();
            //회문 -> 유사회문 검사
            if(isPalindrome(str)) bw.append("0\n");
            else if(isPseudoPalindrome(str)) bw.append("1\n");
            else bw.append("2\n");
        }

        bw.flush();
        bw.close();
    }

    public static boolean isPalindrome(String str) {

        //홀수짝수 나눌 필요없다.
        //반을 기준으로 양옆이 맞기만 하면된다.
        int back=str.length()-1;
        int front=0;

        int count=0;
        int half=str.length()/2;

        while(count<half) {
            char left=str.charAt(front);
            char right=str.charAt(back);
            if(left!=right) return false;
            front++;
            back--;
            count++;
        }
        return true;

    }

    public static boolean isPseudoPalindrome(String str) {
        //짝수도 유사회문이 될 수 있다. asdfsa -> asdsa or asfsa ..ㅠㅠ
        int back=str.length()-1;
        int front=0;

        int count=0;
        int half=str.length()/2;
        if(str.length()%2==0) half--;

        boolean chance=true;
        while(count<half) {
            char left=str.charAt(front);
            char right=str.charAt(back);
            if(left!=right) {
                if(!chance) return false;

                //앞을 증가시켜보기
                boolean check1=isPalindrome(str.substring(front+1, back+1));
                //뒤를 감소시켜보기
                boolean check2=isPalindrome(str.substring(front, back));
                if(check1 || check2) return true;
                else return false;
            }
            front++;
            back--;
            count++;
        }
        return true;
    }

}
