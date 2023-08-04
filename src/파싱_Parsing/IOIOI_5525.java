package 파싱_Parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOIOI_5525 {

    private String site="https://www.acmicpc.net/problem/5525";

    public void result(){

    }
    public void bad() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine()); //N
        int M=Integer.parseInt(br.readLine()); //S길이
        String S=br.readLine();
        char[] charStrs=S.toCharArray();

        //N이 홀수면 가운데가 0
        //N이 짝수면 가운데가 I
        //탐색범위 N 인덱스부터 시작
        //N을 기준으로 양옆을 검사한다.
        //기준점에서 N번 검사

        // 0 1 2 3 4 5 (6)
        //     2~3<(6-2)
        int count=0;

        for(int i=N; i<charStrs.length-N; i++){
            if(N%2==0 && charStrs[i]=='I'){//N이 짝수면서 I일때

                boolean flag=true;
                //짝수니까 n도 홀수일때 O / 짝수일때 I 여야함.
                for(int n=1; n<=N; n++){
                    if(n%2==0 && charStrs[i-n]==charStrs[i+n] && charStrs[i-n]=='I'){

                    }else if(n%2==1 && charStrs[i-n]==charStrs[i+n] && charStrs[i-n]=='O'){

                    }else{
                        //위의 경우가 아니면 틀린거임
                        flag=false;
                        break; //반복문 종료
                    }
                }

                if(flag) count++;


            }else if(N%2!=0 && charStrs[i]=='O'){//N이 홀수면서 O일때

                boolean flag=true;
                //홀수니까 홀수일때 I / 짝수일때 O 여야함.
                for(int n=1; n<=N; n++){
                    if(n%2==0 && charStrs[i-n]==charStrs[i+n] && charStrs[i-n]=='O'){

                    }else if(n%2==1 && charStrs[i-n]==charStrs[i+n] && charStrs[i-n]=='I'){

                    }else{
                        //위의 경우가 아니면 틀린거임
                        flag=false;
                        break; //반복문 종료
                    }
                }

                if(flag) count++;


            }
        }


        System.out.println(count);

    }

    //기준 IOI 의 반복 개수
    public void result2() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine()); //N
        int M=Integer.parseInt(br.readLine()); //S길이
        String S=br.readLine();
        char[] charStrs=S.toCharArray();

        //N이 홀수면 가운데가 0
        //N이 짝수면 가운데가 I
        //탐색범위 N 인덱스부터 시작
        //N을 기준으로 양옆을 검사한다.
        //기준점에서 N번 검사

        // 0 1 2 3 4 5 (6)
        //     2~3<(6-2)
        int count=0;
        int result=0;

        //IOI가 하나의 세트로
        //n이 1일땐 IOI 한번 검사
        //n이 2일땐 IOI > 이동 2칸(i++ / for문의 i++ >> 총 2번)하고 IOI 더 검사 총 2번검사

        for(int i=1; i< M-1; i++){
            if(charStrs[i-1]=='I' && charStrs[i]=='O'&&charStrs[i+1]=='I'){
                count++;
                if(count==N){
                    count--; // N은 2이고 IOIOIOI라고할때 하고 index 즉 i가 0일때 IOIOI 만족 >> i는 2로 이동
                             // result 1증가함, 그리고 나서 i가 2일때도 결과적으로 IOIOI을 세줘야해서
                             // i가 2일때 개수를 카운트하기위해서 1뺴는 것임.
                    result++;
                }
                i++;
            }else{
                count=0;
            }
        }

        System.out.println(result);
    }

    //기준 OI 의 누적 개수 >> result2랑 비슷하게 품
    public void result3() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int N=Integer.parseInt(br.readLine()); //N
        int M=Integer.parseInt(br.readLine()); //S길이
        String S=br.readLine();
        char[] charStrs=S.toCharArray();


        int count=0;
        int result=0;
        //2중 반복문이 아예 없어야 시간초과가 안뜰까?
        for(int i=1; i<M-2; i++){
            if(charStrs[i]=='O' && charStrs[i+1]=='I'){

                count++;
                i++; // i 증가의 위치가 달라짐
                if(count==N){
                    count--;
                    if(charStrs[i-2*N]=='I'){ // 마지막으로 맨 처음값이 I인지 확인해야함.
                        result++;
                    }
                }


            }else{
                count=0;
            }
        }

        System.out.println(result);




    }

    //DP로 풀어보기.
    public void result4(){

    }
}
