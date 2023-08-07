package 수학_Math;

import java.io.*;

public class AcmHotel_10250 {

    private String site="https://www.acmicpc.net/problem/10250";

    public void result() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        int test_number=Integer.parseInt(br.readLine());

        for(int test_case=0; test_case<test_number; test_case++){
            String[] strarr=br.readLine().split(" ");
            int H=Integer.parseInt(strarr[0]); //호텔의 층수
            int W=Integer.parseInt(strarr[1]); //각 층의 방수
            int N=Integer.parseInt(strarr[2]); //몇번째 손님

            //호텔의 층수는 상관없다는 것은...!
            //각 걷는 거리 순서라서 열 순서로 차게됨.
            //앞에는 N%H 해서 나머지값
            //뒤에는 N/H+1하면됨.
            String front="";
            String behind="";
            if(N%H==0){
                front=Integer.toString(H);
                behind=Integer.toString(N/H);
                if(behind.length()==1) behind="0"+behind;

            }else{
                front=Integer.toString(N%H);
                behind=Integer.toString((N/H)+1);
                if(behind.length()==1) behind="0"+behind;
                //System.out.print(front+behind);
            }

            bw.write(front+behind+"\n");

        }

        br.close();
        bw.close();
    }
}
