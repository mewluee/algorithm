package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GuitarLesson2343 {
    private String site="https://www.acmicpc.net/problem/2343";
    private String site2="https://bingorithm.tistory.com/10"; //반례 모음


    public void result2() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken()); //강의의 수
        int M=Integer.parseInt(st.nextToken()); //블루레이 수

        st=new StringTokenizer(br.readLine(), " ");
        int[] lectures=new int[N];

        for(int n=0; n<N; n++){
            lectures[n]=Integer.parseInt(st.nextToken());
        }

        long max=Arrays.stream(lectures).sum(); //입력된 데이터들의 값
        long min=Arrays.stream(lectures).max().getAsInt();
        //얜 max=max+1안해도됨~

        while(min<max){

            long mid=(min+max)/2;
            int count=0;
            long sub_mid=mid;
            for(int i=0; i<lectures.length; i++){
                if(sub_mid-lectures[i]<0){
                    count++;
                    sub_mid=mid;
                }
                sub_mid=sub_mid-lectures[i]; //기본은 늘 뺀다.
            }
            if(sub_mid<mid){//하나가 차다가 끝났으면
                count++; //카운트 증가시켜놓기.
            }

            //내가 구한값이 찾는값보다 초과인 순간 구하기 (최대값을 구할떄는 이상/최소값을 구할때는 초과)
            if(M<count){ //초과면 count값을 감소시키기위해서 mid값을 증가시켜서 더 많이 담아야한다. >> 즉 하한값 상승시키기
                min=mid+1;
            }else{
                max=mid;
            }


            //아..중요한게 있네 값은 늘 최대값이상이어야해.....안그럼..영상을 담을수가없음.....착각했따..
            //그리고 입력값이 무조건 순서대로가 아님..
            //난 동영상을 자르는 개념인줄알앗네..
            //min의 최소값이 정해져있어야함.

        }

        System.out.println(max);
    }


    public void result() throws IOException {

        //문제가 요구하는건 기타 강의를 몇분단위로 자를건지.
        //해당 단위가 최소값이어 한다는 것.

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        int N=Integer.parseInt(st.nextToken()); //강의의 수
        int M=Integer.parseInt(st.nextToken()); //블루레이 수

        st=new StringTokenizer(br.readLine(), " ");
        int[] lectures=new int[N];

        for(int n=0; n<N; n++){
            lectures[n]=Integer.parseInt(st.nextToken());
        }

        //min값과 max값 정하기
        //min값은 데이터가 1개 들어올때 기준으로 일단 1은 고정.
        //max값은 데이터들의 합으로 정하면 될거같다.
        //mid값이 나올때마다 해당 값에 최대한 담아보고 넘으면 count를 센다ㅣㅇ.
        //그리고 그 count를 비교하면 되는 것이지 후후
        //이것도 max값+1하고 돌려야할듯 ㅇㅇ

        long max= Arrays.stream(lectures).sum(); //입력된 데이터들의 값
        long min=Arrays.stream(lectures).max().getAsInt();
        max=max+1;

        while(min<max){

            long mid=(min+max)/2;
            int count=0;
            long sub_mid=mid;
            //System.out.println("min:"+min+" max:"+max+" mid:"+mid);
            for(int i=0; i<lectures.length; i++){
                //System.out.println("1>> submid:"+sub_mid+" lec[i]:"+lectures[i]);
                if(sub_mid-lectures[i]<0){

                    count++; //하나의 블루레이가 찬거니까 수 증가시키기
                    sub_mid=mid; //다시 mid값으로 증가시키고 밑에서 빼야함.
                    //System.out.println("if문 들어옴!count:"+count);
                }
                sub_mid=sub_mid-lectures[i]; //기본은 늘 뺀다.
                //System.out.println("2>> submid:"+sub_mid+" lec[i]:"+lectures[i]);
            }
            if(sub_mid<mid){//하나가 차다가 끝났으면
                count++; //카운트 증가시켜놓기.
            }
            //System.out.println("찾는값M:"+M+" mid:"+mid+" 구한값count:"+count);


            //내가 구한값이 찾는값보다 초과인 순간 구하기 (최대값을 구할떄는 이상/최소값을 구할때는 초과)
            if(M<count){ //이상이면 count값을 감소시키기위해서 mid값을 증가시켜서 더 많이 담아야한다. >> 즉 하한값 상승시키기
                min=mid+1;
            }else{
                max=mid;
            }

            //System.out.println("-".repeat(20));

            //아..중요한게 있네 값은 늘 최대값이상이어야해.....안그럼..영상을 담을수가없음.....착각했따..
            //그리고 입력값이 무조건 순서대로가 아님..
            //난 동영상을 자르는 개념인줄알앗네..
            //min의 최소값이 정해져있어야함.

        }

        //System.out.println("max:"+max+", max-1:"+(max-1));
        System.out.println(max);
    }
}
