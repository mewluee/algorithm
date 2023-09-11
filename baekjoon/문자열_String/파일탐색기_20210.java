package 문자열_String;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class 파일탐색기_20210 {


    //문제
    //문자열은 알파벳 대소문자와 숫자
    //숫자열 > 문자열
    //알파벳 대문자 > 소문자, 오름차순.
    //문자/숫자 분리
    //숫자는 오름차순
    //같은 값의 숫자는 0의 개수 오름차순
    //즉 앞뒤에 있는 0은 숫자로 취급하지 않는다.

    static int N;
    public static void main(String[] args)throws IOException {
        //System.out.println((int)'a'+","+(int)'A');
        //System.out.println((int)'0'+","+(int)'9');
        //System.out.println(Integer.parseInt("0025"));;
        //System.out.println(Integer.parseInt("2500"));
        //A=65 ~ Z=90, a=97 ~ z=122 (25차이) 26알파벳
        //0=48 ~ 9=57 (9)차이 10개의 숫자
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        N=Integer.parseInt(br.readLine());
        List<List<String>> list=new ArrayList<>();

        for(int n=0; n<N; n++) {
            String input=br.readLine();
            list.add(getList(input));
        }

        list.sort((o1, o2)->{
            //둘 중 하나는 숫자이면..숫자가 앞으로,
            int size1=o1.size();
            int size2=o2.size();
            int min=Math.min(size1, size2);
            for(int i=0; i<min; i++) {
                //123 123
                //1 과 1로
                int n=(int)o1.get(i).charAt(0); // string -> char -> int
                int m=(int)o2.get(i).charAt(0);

                //System.out.println("n:"+n+", m:"+m);
                if(o1.get(i).equals(o2.get(i))) {
                    continue;
                }else if(n<=57 && m<=57) {
                    //둘다 숫자라면
                    //뒤에 더 검색해봐야함.
                    //int num1=Integer.parseInt(o1.get(i));
                    BigInteger num1=new BigInteger(o1.get(i));
                    BigInteger num2=new BigInteger(o2.get(i));
                    if(num1.compareTo(num2)==0) {
                        //000123(6) or 123(3) -> 123 123 -> 123 000123
                        int len1=o1.get(i).length();
                        int len2=o2.get(i).length();
                        return len1-len2;
                    }else {
                        return num1.compareTo(num2);
                    }
                }else if(n<=57){
                    //o1만 숫자라면 -> o1이 앞으로
                    return -1; //(수정전) n-m => illegalArgument 발생지점
                }else if(m<=57) {
                    //o2만 숫자라면 -> o2가 앞으로
                    return 1; //(수정전) m-n => illegalArgument 발생지점
                }else {
                    //둘다 글자라면
                    //같은 알파벳인지 대/소 확인하기. 우선순위 a > B
                    if((n<=90 && m<=90)||(97<=n && 97<=m)) { //둘다 대문자거나 둘다 소문자일 경우
                        return n-m;
                    }else { //하나는 대문자 하나는 소문자일 경우
                        if(n>m) {
                            if(n-32<m) return -1; //n(o1)이 앞으로 와야함.
                            else return 1;
                        }else {
                            if(m-32<n) return 1;
                            else return -1;
                        }

                    }
                }
            }

            if(size1<size2) return -1;
            else return 1;
        });

        //System.out.println(list.toString());

        for(int n=0; n<N; n++) {
            List<String> result=list.get(n);
            for(int m=0; m<result.size(); m++) {
                bw.append(result.get(m));
            }
            bw.append("\n");
        }
        bw.flush();
        bw.close();
    }

    static List<String> getList(String str){
        List<String> result = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isLetter(c)) {
                if (word.length() > 0) {
                    result.add(word.toString());
                    word.setLength(0);
                }
                result.add(String.valueOf(c));
            } else if (Character.isDigit(c)) {
                word.append(c);
            }
        }

        if (word.length() > 0) {
            result.add(word.toString());
        }

        return result;
    }
}
