package 문자열_String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 크로아티아알파벳_2941 {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str=br.readLine();
        String[] abc=new String[] {
                "c=","c-","dz=","d-","lj","nj","s=","z="
        };
        int count=0;
        for(int i=0; i<str.length(); i++) {

            if(i+2<str.length() && str.charAt(i)=='d' && str.charAt(i+1)=='z' && str.charAt(i+2)=='=')  {
                count++;
                i=i+2;
            }else if(	i+1<str.length() && (
                    (str.charAt(i)=='c' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='c' && str.charAt(i+1)=='-') ||
                            (str.charAt(i)=='l' && str.charAt(i+1)=='j') ||
                            (str.charAt(i)=='n' && str.charAt(i+1)=='j') ||
                            (str.charAt(i)=='s' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='z' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='d' && str.charAt(i+1)=='-'))
            )
            {
                count++;
                i=i+1;
            }else {
                count++;
            }
        }
        System.out.println(count);
    }

    //	런타임 에러 (StringIndexOutOfBounds)
    //	-> 원인 :
    //	문자열의 길이가 10일때 인덱스9에서 d를 발견해서 검사를 시작하면
    //  str.charAt(i+1) 에서 인덱스 10을 검색해서 범위를 벗어나게 된다.
    public static void main3(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str=br.readLine();
        String[] abc=new String[] {
                "c=","c-","dz=","d-","lj","nj","s=","z="
        };
        int count=0;
        for(int i=0; i<str.length(); i++) {

            if(str.charAt(i)=='d' && str.charAt(i+1)=='z' && str.charAt(i+2)=='=')  {
                count++;
                i=i+2;
            }else if(
                    (str.charAt(i)=='c' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='c' && str.charAt(i+1)=='-') ||
                            (str.charAt(i)=='l' && str.charAt(i+1)=='j') ||
                            (str.charAt(i)=='n' && str.charAt(i+1)=='j') ||
                            (str.charAt(i)=='s' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='z' && str.charAt(i+1)=='=') ||
                            (str.charAt(i)=='d' && str.charAt(i+1)=='-')
            )
            {
                count++;
                i=i+1;
            }else {
                count++;
            }
        }
        System.out.println(count);
    }

    // 구현 불가,
    // (예제) ddz=z=
    // (성공) d / dz= / z= 3개
    // (실패) d (dz=) z= --> dz= / dz= 2개
    public static void main2(String[] args) throws IOException{

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str=br.readLine();
        String[] abc=new String[] {
                "c=","c-","dz=","d-","lj","nj","s=","z=","=","-"
        };
        int searchIndex=0;
        int count1=0;
        int count2=0;
        do {
            int startIndex;
            int pastIndex=-1;
            do {
                startIndex=str.indexOf(abc[searchIndex]);
                System.out.println("찾는 값:"+abc[searchIndex]);
                System.out.println("인덱스:"+startIndex);

                if(startIndex>-1 && pastIndex<startIndex) {
                    String front=str.substring(0, startIndex);
                    String back=str.substring(startIndex+abc[searchIndex].length());
                    System.out.println("f:"+front+", b:"+back);
                    if(searchIndex==8 || searchIndex==9) {
                        count2++;
                    }else {
                        count1++;
                        pastIndex=startIndex;
                    }
                    str=front+back;
                    System.out.println(str);

                }
            }while(startIndex!=-1);
            searchIndex++;
        }while(searchIndex<10);

        System.out.println(str.length()+count1);
    }
}
