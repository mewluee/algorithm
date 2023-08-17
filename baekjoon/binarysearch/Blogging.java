package binarysearch;

import java.io.*;
import java.util.StringTokenizer;

public class Blogging {

    //기본 이분탐색(while문으로 구현)
    public void result(){

        int[] data={1, 3, 5, 7, 9, 11, 13, 15};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=8; //찾는값
        int result=-1; //int형은 null로 초기화 할수없다(그래서 null인지 비교도 불가능)
        //인덱스값이 저장되므로 초기값을 -1로 지정한다

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            if(N<data[mid]) max=mid-1;
            else if(N==data[mid]) result=mid;
            else min=mid+1;
        }

        if(result==-1){
            System.out.println("값을 찾을 수 없습니다.");
        }else{
            System.out.println(result);
        }

    }

    // lower bound 구현
    public void result2(){
        int[] data={7, 9, 11, 13, 15, 17, 19, 21};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=8; //찾는값

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            //--------------------------------------------------//
            if(N<=data[mid]) max=mid; // (4) = (1) + (2)
            else min=mid+1; // (5) = (3)
        }

        System.out.println(max);
    }

    // upper bound 구현
    public void result3(){
        int[] data={1, 3, 5, 7, 9, 11, 13, 15};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=9; //찾는값

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            //--------------------------------------------------//
            if(N<data[mid]) max=mid;
            else min=mid+1;
        }

        System.out.println(min);
    }

    //나무자르기 구현
    public void result4() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//구해야하는 총 나무토막 길이

        StringTokenizer strtrees=new StringTokenizer(br.readLine()," "); //나무들의 높이

        int[] intTrees=new int[N];
        int min=0;
        int max=0; //제공되는 나무들의 최대값
        for(int n=0; n<N; n++){
            intTrees[n]=Integer.parseInt(strtrees.nextToken());
            if(intTrees[n]>max) max=intTrees[n];//max값 갱신해준다.
        }


        while(min<max){

            //bw.write("min:"+Integer.toString(min)+", max:"+Integer.toString(max)+"\n");

            int mid=(min+max)/2;

            long sum=0;//나무 수 범위 1 ≤ N ≤ 1,000,000 / 나무 높이 범위 0~1,000,000,000 이라서 int를 넘는다
            for(int i=0; i<intTrees.length; i++){
                int one=intTrees[i]-mid;
                if(one>0) sum=sum+one;
            }

            //bw.write("찾는값:"+M+" mid:"+Integer.toString(mid)+"  sum:"+Long.toString(sum)+"\n");
            //bw.write("-".repeat(20)+"\n");

//            //(1) 수정 전 (배열 기준)
//            if(M<=sum) max=mid;
//            else min=mid+1;

            //(2) 수정 후 (나무자르기 기준)
            if(M<=sum) min=mid+1;
            else max=mid;


        }

        bw.write(Integer.toString(max-1));
        bw.close();
    }

    //랜선자르기 구현
    public void result5() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stt=new StringTokenizer(br.readLine()," ");
        //StringTokenizer는 한줄마다 만들면된다.

        int K=Integer.parseInt(stt.nextToken()); // 갖고있는 랜선의 개수 K
        int N=Integer.parseInt(stt.nextToken()); // 필요한 랜선의 개수 N

        int[] lans=new int[K];
        long min=0;
        long max=0;
        for(int k=0; k<K; k++){
            lans[k]=Integer.parseInt(br.readLine());
            if(lans[k]>max) max=lans[k]; //max값 갱신
        }

        max=max+1; // 중요!!!!!!! max값을 그대로 출력하기 위해서 max의 범위를 하나 더 증가시켜준다.

        while(min<max){

            System.out.println("min:"+min+" max:"+max);
            long sum=0;
            long mid=(min+max)/2;
            for(int k=0; k<K; k++){
                sum=sum+lans[k]/mid;
            }

            System.out.println("찾는값:"+N+" mid:"+mid+" sum:"+sum);
            System.out.println("-".repeat(20));

            if(N<=sum) min=mid+1;
            else max=mid;

        }

        System.out.println(min-1);
        br.close();

    }
}
