package 수학_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카잉달력_6064 {

    //문제
    //년도 <x:y> 표현
    //시초 1번쨰 해 <1:1>
    //<2:2> ..
    //x<M x+1 else 1
    //y<N y+1 else 1
    //ex M=10이고 N=12일때,
    //<1:1> <2:2> <3:3> <4:4> <5:5> <6:6> <7:7> <8:8> <9:9> <10:10> <1:11> <2:12> <3:1>
    //M N
    //M*n+x , N*n+y
    //x==M 일때, 나머지값이 0인 걸 의미 -> M*(n+1)

    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int t=0; t<T; t++) {
            int[] in= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int limit_year=(in[0]*in[1]/GCD(in[0],in[1])); // M과 N의 최소공배수 구하기
            System.out.println(cal(in[0],in[1], in[2], in[3], limit_year));
        }
    }

    static int cal(int M, int N, int x, int y, int limit) {
        int year=0;
        boolean flag=false;
        int n=0;
        do {
            if(x==M) {
                year=M*(n+1);
                if(y==N) {
                    if(year%N==0) {
                        flag=true;
                        break;
                    }
                }else {
                    if(year%N==y) {
                        flag=true;
                        break;
                    }
                }
            }else {
                year=M*n+x;
                if(y==N) {
                    if(year%N==0) {
                        flag=true;
                        break;
                    }
                }else {
                    if(year%N==y) {
                        flag=true;
                        break;
                    }
                }
            }
            n++;
        }while(year<limit);
        if(flag) return year;
        else return -1;
    }

    //유클리드 호제법
    static int GCD(int a, int b) {
        if(a%b==0) return b;
        else return GCD(b, a%b);
    }
}
