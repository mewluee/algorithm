package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CuttingLANcable1654 {

    private String site="https://www.acmicpc.net/problem/1654";

    public void result() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stt=new StringTokenizer(br.readLine()," ");
        //StringTokenizer는 한줄마다 만들면된다.

        int K=Integer.parseInt(stt.nextToken()); // 갖고있는 랜선의 개수 K
        int N=Integer.parseInt(stt.nextToken()); // 필요한 랜선의 개수 N

        int[] lans=new int[K];
        long min=1; //1부터 시작함.사실 이유는 잘 모르겠음...질문게시판보니까 1부터 시작하래..
        long max=0;
        //boolean allSame=true;
        for(int k=0; k<K; k++){
            lans[k]=Integer.parseInt(br.readLine());
            if(lans[k]>max) max=lans[k]; //max값도 갱신해줌.
            //if(lans[k]!=lans[0]) allSame=false; //하나라도 값이 틀리면 false로 만들기
        }

        max=max+1;

      /*  if(allSame){
            System.out.println(lans[0]);
            br.close();
            return;
        }*/

        //으앙 이게 뭐가 나무자르기랑 비슷해..ㅠㅠ
        //짜증ㅇ나아아아아악!
        //응용방법이 뭘까..?
        //아 mid값을 각 배열값에 나누면 되겠군.그리고 sum하기.

        while(min<max){
            long sum=0;
            long mid=(min+max)/2;
            //System.out.println(">> min:"+min+", max:"+max);
            for(int k=0; k<K; k++){
                sum=sum+lans[k]/mid;
            }
            //System.out.println("mid:"+mid+", sum:"+sum);
            //lower써볼래
            /*if(N<=sum){//내가 구한값이 N보다 크다는건 mid값을 키워야한다는 것. 하향선을 높여야함.
                min=mid+1;
            }else{
                max=mid;
            }*/
            if(N<=sum){
                min=mid+1;
            }else{ //N>sum
                max=mid;
            }

        }

        System.out.println(min-1);
        //아ㅁㄹㄴㅇㄹㅇㄴㄹ 똑같을땐 -1안하는데 어카딤
        br.close();
    }
}
