package 탐욕_Greedy;

import java.io.*;

public class 삼십_10610 {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String str=br.readLine();
        // 30의 배수니까 0이 있어야 함.
        // 각 자리의 숫자의 합이 3의 배수여야 함.
        int[] list=new int[10]; //0~9 (10개)
        boolean isZero=false;
        int sum=0;
        for(int i=0; i<str.length(); i++) {
            char now=str.charAt(i);
            int now2=now-'0';
            if(now2==0) isZero=true;
            sum+=now2;
            list[now2]+=1;
        }

        if(isZero && sum%3==0) {
            int index=10;
            for(int i=0; i<str.length(); i++) {
                do {
                    index--;
                    if(index<0) break;
                }while(list[index]==0);
                if(index<0) break;
                for(int j=0; j<list[index]; j++) {
                    bw.append(Integer.toString(index));
                }
            }
            bw.flush();
            bw.close();
        }
        else {
            bw.append(Integer.toString(-1));
            bw.flush();
            bw.close();
        }
    }
}
