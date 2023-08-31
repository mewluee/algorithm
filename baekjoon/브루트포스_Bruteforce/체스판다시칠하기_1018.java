package 브루트포스_Bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 체스판다시칠하기_1018 {

    static int countColoring(String[] arrStr,int rowStart, int colStart){
        //System.out.println("ji");
        //System.out.println(arrStr[1].charAt(1));
        int count=0;

        //2. 체스판의 시작 W? B?
        if(arrStr[0].charAt(0)=='W'){ //

            for(int k=rowStart; k<rowStart+8; k++){
                for(int v=colStart; v<colStart+8; v++){
                    //System.out.println("k:"+k+",v:"+v+", char:"+arrStr[k].charAt(v));
                    if(k%2==0){ // 행이 짝수일때
                        if(v%2==0){ // 열도 짝수면 W여야함
                            if(arrStr[k].charAt(v)=='B'){ //그런데 B값이면 카운터 증가
                                //System.out.println("(1)");
                                count++;
                                //System.out.println("(1)증가됨");
                            }
                        }else { //열이 홀수면 B여야함
                            if(arrStr[k].charAt(v)=='W'){ //그런데 W값이면 카운터 증가
                                //System.out.println("(2)");
                                count++;
                                //System.out.println("(2)증가됨");
                            }
                        }
                    }else{ // 행이 홀수일때
                        if(v%2==0){ // 열이 짝수면 B여야함
                            if(arrStr[k].charAt(v)=='W'){ // 그런데 W값이면 카운터 증가
                                //System.out.println("(3)");
                                count++;
                                //System.out.println("(3)증가됨");
                            }
                        }else {//열이 홀수면 W여야함
                            if(arrStr[k].charAt(v)=='B'){ // 그런데 B값이면 카운터 증가
                                //System.out.println("(4)");
                                count++;
                                //System.out.println("(4)증가됨");
                            }
                        }
                    }
                }
            }

        }else{

            for(int k=rowStart; k<rowStart+8; k++){
                for(int v=colStart; v<colStart+8; v++){
                    //System.out.println("k:"+k+",v:"+v+", char:"+arrStr[k].charAt(v));
                    if(k%2==0){ // 행이 짝수일때
                        if(v%2==0){ //열이 짝수면 B여야함
                            if(arrStr[k].charAt(v)=='W'){ // 그런데 W면 카운터 증가
                                count++;
                                //System.out.println("(1)증가됨");
                            }
                        }else { //열이 홀수면 W여야함
                            if(arrStr[k].charAt(v)=='B'){ // 그런데 B면 카운터 증가
                                count++;
                                //System.out.println("(2)증가됨");
                            }
                        }
                    }else{ // 행이 홀수일때
                        if(v%2==0){ // 열이 짝수면
                            if(arrStr[k].charAt(v)=='B'){
                                count++;
                                //System.out.println("(3)증가됨");
                            }
                        }else {
                            if(arrStr[k].charAt(v)=='W'){
                                count++;
                                //System.out.println("(4)증가됨");
                            }
                        }
                    }
                }
            }

        }

        count=Math.min(count,64-count);

        //System.out.println(count);

        return count;
    }

    public void result(){

        Scanner input=new Scanner(System.in);

        //0. 행 열 입력
        int row = input.nextInt(); // 행
        int column = input.nextInt(); // 열

        input.nextLine();

        //1. 체스판 입력
        String[] arrStr=new String[row];
        for(int i=0; i<row; i++){
            String str=input.nextLine();
            arrStr[i]=str;
        }

        int min=row*column;
        for(int n=0; n<=row-8; n++){
            for (int m=0; m<=column-8; m++){
                System.out.println("n:"+n+", m:"+m);
                int one=countColoring(arrStr,n,m);
                System.out.println("one:"+one);
                if(min>one){
                    min=one;
                }
            }
        }
        System.out.println(min);
    }

    //2회차 풀이
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        String[] map = new String[N];

        for(int n=0; n<N; n++) {
            map[n]=br.readLine();
        }
        int min=250;

        for(int n=0; n<=N-8; n++) {
            for(int m=0; m<=M-8; m++) {
                int count=check(map, n, m);
                count=Math.min(count,  64-count);
                if(count<min) {
                    min=count;
                }
            }
        }
        System.out.println(min);
    }

    static int check(String[] map, int n, int m) {
        int count=0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(i%2==0) { //짝수는 BW..
                    if(j%2==0) { //짝수는 B
                        if(map[n+i].charAt(m+j)!='B') count++;
                    }else { //홀수는 W
                        if(map[n+i].charAt(m+j)!='W') count++;
                    }
                }else { //홀수는 WB..
                    if(j%2==0) { //짝수는 W
                        if(map[n+i].charAt(m+j)!='W') count++;
                    }else { //홀수는 B
                        if(map[n+i].charAt(m+j)!='B') count++;
                    }
                }
            }
        }

        return count;
    }
}
