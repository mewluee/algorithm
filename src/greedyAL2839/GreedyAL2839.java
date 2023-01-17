package greedyAL2839;

import java.io.*;

public class GreedyAL2839 {
    public void result() throws IOException{

        //백준 2839번 풀이중 >> 그리디 알고리즘(탐욕->최적해구하기/속도올라감)
        //순서
        //0. 5로 나눴을때 나머지가 0인 경우>>5로 나눈 몫
        //1. 5로 나눴을때 나머지가 3일 경우>>5로 나눈 몫+1
        //2. 5로 나눴을때 나머지가 1인 경우>>5로 나눈 몫-1(단 몫이 1이상인 수) + (6/3)2
        //3. 5로 나눴을때 나머지가 4인 경우>>5로 나눈 몫-1(단 몫이 1이상인 수) + (9/3)3
        //4. 5로 나눴을때 나머지가 2인 경우>>5로 나눈 몫-2(단 10이상인 수) + (12/3)4
        //5. 3일때는 1(5보다 작을때, 3일경우)
        //6. 나머지는 -1

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int N=Integer.parseInt(bf.readLine());

        if(N%5==0) bw.write(Integer.toString(N/5));
        else if(N/5>=1 && N%5==1) bw.write(Integer.toString((N/5-1)+2));
        else if(N/5>=2 && N%5==2) bw.write(Integer.toString((N/5-2)+4));
        else if(N/5>=1 && N%5==3) bw.write(Integer.toString((N/5)+1));
        else if(N/5>=1 && N%5==4) bw.write(Integer.toString((N/5-1)+3));
        else if(N==3) bw.write(Integer.toString(1));
        else bw.write(Integer.toString(-1));

        bw.close();
    }
}
