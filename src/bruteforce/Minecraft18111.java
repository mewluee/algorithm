package bruteforce;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.IntStream;

public class Minecraft18111 {

    private String site="https://www.acmicpc.net/problem/18111";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]); // 집터 세로 N 가로 M
        int M=Integer.parseInt(strarr[1]); //
        int B=Integer.parseInt(strarr[2]); // 인벤토리에 들어있는 블록 개수

        int[][] ddang=new int[N][M];

        for(int i=0; i<N; i++){
            String[] strddangs=br.readLine().split(" ");
            ddang[i]= Arrays.stream(strddangs).mapToInt(Integer::parseInt).toArray();
        }
//        System.out.println("ddang출력:"+Arrays.deepToString(ddang)); //[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 1]]

        //조건 생각해보기
        //0.땅 높이 선정..? 최대값, 최소값, 중간값, 최빈값 >> 뭐가 더 좋지.
        //  *1) 높이에 최빈값을 기준으로 쌓을지 제거할지 고민
        //  *2) 단, 제공하는 땅이 다 각자 높이가 다르면 땅을 쌓는게. 시간이 덜 걸림/
        //         >> 시간이 같다면 땅의 높이가 더 높은걸 뽑아야함!
        //         >> 그럼 인벤이 넉넉하면 땅을 쌓자! 어처피 쌓는거네..?
        //         >> 브루트포스인가?.. 입력된 땅의 높이마다 다 해보고 최소시간 선택해서 출력
        //         >> 입력된 땅의 높이마다 해야할 것 >> (1)일단 쌓기부터 고려(인벤이 넉넉한가? yes) (2) 1번이 no이면 제거

        //1.블록을 제거하거나(2초)
        //  *1) 인벤토리는 늘 넉넉함. 제거해서 넣을 블록수는 고려안해도됨.
        //

        //2.블록을 쌓거나(1초)
        //  *1) 쌓을려면 인벤이 넉넉해야함! 쌓기 전에 인벤에 넉넉한지 확인하고 쌓기.

        //출력은 땅의 높이가 가장 높은 것. > while문 돌리기?

        //와 모든 땅의 경우의 수를;;
        ArrayList<Integer[]> result=new ArrayList<>(); // 배열을 리스트에! [ [0,1], [0,1], .. ] 0번 인덱스엔 시간 / 1번 인덱스엔 땅의 높이를 저장할 예정
        //큐나 스택..? 쓸게없는듯.

        //땅의 모든 높이들을 hashset으로 저장해서 중복값 없애서 result 배열에 저장하자. 이때 시간은 0 고정
        HashSet<Integer> heights=new HashSet<>();
        for(int i=0; i<ddang.length; i++){
            for(int j=0; j<ddang[i].length; j++){
                heights.add(ddang[i][j]);
                //중복값들 편하게 제거.
            }
        }

        Iterator heightsIt=heights.iterator();
        while(heightsIt.hasNext()){
            int height=((Integer) heightsIt.next()).intValue(); // Object->Integer->int 우욱..
//            System.out.println("height:"+height);
            result.add(new Integer[]{0,height}); //
        }
//        System.out.println("hashset출력:"+heights.toString()); //hashset 출력해보기~
//        System.out.println("result출력:"+Arrays.deepToString(result.toArray())); // ArrayList의 모든 원소 출력하기
        // 무슨 원리지..? 나중에 알아보기.

        //본격 알고리즘.
        //result에 들어간 개수만큼 돌리고
        //각 입력된 땅의 개수마다 시간세서 입력하기.
        //땅을 쌓거나 제거하거나 위에 알고리즘대로 구현하기

        for(int k=0; k<result.size(); k++){
//            System.out.println("for문 시작");
            int ddangH=result.get(k)[1]; // 1번 인덱스 가져오기
//            System.out.println("ddangH:"+ddangH);

            //ddangH 높이를 기준으로 ddang고르게 만들기! > 걸리는 시간은!?
            //int time;

            //땅을 모두 순회하면서 ddangH높이에 맞춰서 쌓을 개수랑 제거할 개수 세기.
            //쌓을 개수가 넉넉하면 쌓기. 안되면 걍..제거하셈.......
            //어 문제점 .>>만약 내가 인벤에 블록개수가 넉넉하지 않아서 못 쌓으면 애초에 해당 ddangH높이로 맞추질 못함.
            //>> 그럼 ddangH-1 높이로 만들어서 저장해야함.
            //result에 ddangH-1 가 존재하는지 확인후에 저장하고, 없으면 추가해서 저장
            //기존에 있던 값이 있으면 시간을 비교해서 최소시간으로 저장하기.

          /*  int plus=0; // 1초
            int minus=0; // 2초

            for(int i=0; i<ddang.length; i++){
                for(int j=0; j<ddang[i].length; j++){
                    if(ddangH>ddang[i][j]) plus=ddangH-ddang[i][j]; // ddangH까지 쌓아올려야함.
                    else minus=ddang[i][j]-ddangH; // ddangH까지 제거해야함.
                }
            }

            System.out.println("plus:"+plus+" minus:"+minus);*/
            // 함수로 뽑음.

            int[] plusAndMinus=getPlusAndMinus(ddang, ddangH);

            // 이제 쌓을수있는지 검사 //아 잠깐, 제거한 블록의 수도 포함해야함;;
            // 0번 인덱스  plus / 1번 인덱스  minus
            if(plusAndMinus[0]<=B+plusAndMinus[1]){ //인벤에 블록이 충분할 경우 > 일단 먼저 제거한 블록+인벤 블록 넉넉해~?
                result.get(k)[0]=getTime(plusAndMinus); // 시간 저장

            }else{ //여기서부터 복잡; 쌓질 못하니까 ddangH-1해서 다시 확인해야함..!
                // 어.. 어떻게 값을 반환하지 > 반환해야할게 두개라서 메서드는 그닥인듯..? 힝구..ㅠ >메서드에서 빼옴!ㅋㅋ
                // 다시 ddangH-1 찾기.
                plusAndMinus=getPlusAndMinus(ddang, ddangH-1); // 새로운 p/m 블록수 찾고
                int time=getTime(plusAndMinus); // 해당하는 시간 뽑고
                boolean isNope=true; // result에 없는가? ㅇㅇ없음 true

                //해당 높이가 result에 있는지 확인하기 위해서 순회시작
                for(int n=0; n<ddang.length; n++){
                    if(result.get(n)[1]==ddangH-1){ // result 리스트에서 해당하는 높이가 있으면
                        //시간 비교해서 작은 시작으로 저장 // 헐 처음에 근데 0입력되어잇으면...헉..스...무조건 0이니까 바꿔야함..
                        if(result.get(n)[0]==0) { // 시간이 0이라면
                            result.get(n)[0]=time; // 초기값이니까 비교안하고 바로 저장.

                        }else{
                            if(result.get(n)[0]>time) result.get(n)[0]=time; // 작은 시간으로 갱신하고 반복문 종료
                        }

                        isNope=false; // ㄴㄴ 있음 false
                        break;

                    }
                }

                if(isNope) { //없으면 result에 추가해주기
                    result.add(new Integer[]{time,ddangH-1});
                }

            }

        } // result구성 완료

        System.out.println("0시간 제거전:"+Arrays.deepToString(result.toArray()));
        // 시간이 0인값은 제거하고 ??이걸 굳이 하지말고 아이거해야해..안할수없어..
        // > 순회해서 시간이 제일 작은거 뽑고.. (시간이 0이면 제외)
        // 같은 시간을 갖고잇는 높이들 중에서 제일 높은 높이 뽑고.. 이거 한번에 할 방법은 없을까?
        // 시간 비교하고 > 높이 비교하고
//        for(int p=0; p<result.size(); p++){
//            if(result.get(p)[0]==0){ //시간이 0이면. 제거해.
//                result.remove(p);
//            }
////        }
//        result=result.stream().filter(u->u[0]!=0).collect(Collectors.toList()).toArray();

        ArrayList<Integer[]> newResult=new ArrayList<Integer[]>();

        Iterator iterator=result.iterator();
        while(iterator.hasNext()){
            Integer[] one=(Integer[]) iterator.next();
            if(one[0]!=0){
                newResult.add(one);
            }
        }

        System.out.println("0시간 제거후:"+Arrays.deepToString(newResult.toArray()));

//        System.out.println("답:"+minTime+" "+maxHeight);
        if(newResult.size()==0){
            bw.write(Integer.toString(0)+" "+Integer.toString(ddang[0][0]));
        }else{
            int minTime=newResult.get(0)[0]; // 제일 첫번째 시간값을 min에다가 넣어두고 비교한다
            int maxHeight=newResult.get(0)[1]; // 제일 첫번째 높이값을 max에다가 넣어두고 비교한다
            for(int q=1; q<newResult.size(); q++){  // 0번인덱스빼고 비교한다

                if(newResult.get(q)[0]<minTime){ // min보다 작으면
                    minTime=newResult.get(q)[0]; // 시간이랑 높이 넣기.
                    maxHeight=newResult.get(q)[1];

                }else if(newResult.get(q)[0]==minTime){ // min이랑 같으면 높이만 비교해서 넣기
                    if(newResult.get(q)[1]>maxHeight)
                        maxHeight=newResult.get(q)[1];
                }
            }
            bw.write(minTime+" "+maxHeight);
        }

        bw.close();
    }

    //맞은거
    public static void result3() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]); // 집터 세로 N 가로 M
        int M=Integer.parseInt(strarr[1]); //
        int B=Integer.parseInt(strarr[2]); // 인벤토리에 들어있는 블록 개수



        int[][] ddang=new int[N][M];

        int maxHeight=0;

        for(int i=0; i<N; i++){
            String[] strddangs=br.readLine().split(" ");
            ddang[i]= Arrays.stream(strddangs).mapToInt(Integer::parseInt).toArray();
            maxHeight=maxHeight+ IntStream.of(ddang[i]).sum();
        }

        //System.out.println(maxHeight);

        maxHeight=(B+maxHeight)/(N*M);

        //System.out.println(maxHeight);

        ArrayList<Integer[]> result=new ArrayList<Integer[]>();

        //브루트포스 알고리즘 써야함.
        //그럼 최대로 쌓을 수 있는 땅의 높이 설정하기
        //최대 땅 높이 = 땅 0을 기준으로 이미 올려진 블록들의 수 + 인벤의 수 / (땅 너비)
        //0부터 최대땅 높이 만큼 경우의 수 돌리기
        for(int i=0; i<=maxHeight; i++){
            int[] pm=getPlusAndMinus(ddang, i);
            int time;
            if(pm[0]<=B+pm[1]){ // 땅을 해당 높이로 만들수있다면.(쌓을 블록 충분?)
                time=getTime(pm);
                result.add(new Integer[]{time,i});
            }
        }

        int resultTime=result.get(0)[0];
        int resultHeight=result.get(0)[1];
        for(int q=1; q<result.size(); q++){  // 0번인덱스빼고 비교한다

            if(result.get(q)[0]<resultTime){ // min보다 작으면
                resultTime=result.get(q)[0]; // 시간이랑 높이 넣기.
                resultHeight=result.get(q)[1];

            }else if(result.get(q)[0]==resultTime){ // min이랑 같으면 높이만 비교해서 넣기
                if(result.get(q)[1]>resultHeight)
                    resultHeight=result.get(q)[1];
            }
        }
        bw.write(resultTime+" "+resultHeight);


        //System.out.println("\n"+Arrays.deepToString(result.toArray()));

        bw.close();



    }

    public static void result2() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]); // 집터 세로 N 가로 M
        int M=Integer.parseInt(strarr[1]); //
        int B=Integer.parseInt(strarr[2]); // 인벤토리에 들어있는 블록 개수

        int[][] ddang=new int[N][M];

        for(int i=0; i<N; i++){
            String[] strddangs=br.readLine().split(" ");
            ddang[i]= Arrays.stream(strddangs).mapToInt(Integer::parseInt).toArray();
        }
//        System.out.println("ddang출력:"+Arrays.deepToString(ddang)); //[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 1]]

        //조건 생각해보기
        //0.땅 높이 선정..? 최대값, 최소값, 중간값, 최빈값 >> 뭐가 더 좋지.
        //  *1) 높이에 최빈값을 기준으로 쌓을지 제거할지 고민
        //  *2) 단, 제공하는 땅이 다 각자 높이가 다르면 땅을 쌓는게. 시간이 덜 걸림/
        //         >> 시간이 같다면 땅의 높이가 더 높은걸 뽑아야함!
        //         >> 그럼 인벤이 넉넉하면 땅을 쌓자! 어처피 쌓는거네..?
        //         >> 브루트포스인가?.. 입력된 땅의 높이마다 다 해보고 최소시간 선택해서 출력
        //         >> 입력된 땅의 높이마다 해야할 것 >> (1)일단 쌓기부터 고려(인벤이 넉넉한가? yes) (2) 1번이 no이면 제거

        //1.블록을 제거하거나(2초)
        //  *1) 인벤토리는 늘 넉넉함. 제거해서 넣을 블록수는 고려안해도됨.
        //

        //2.블록을 쌓거나(1초)
        //  *1) 쌓을려면 인벤이 넉넉해야함! 쌓기 전에 인벤에 넉넉한지 확인하고 쌓기.

        //출력은 땅의 높이가 가장 높은 것. > while문 돌리기?

        //와 모든 땅의 경우의 수를;;
        ArrayList<Integer[]> result=new ArrayList<>(); // 배열을 리스트에! [ [0,1], [0,1], .. ] 0번 인덱스엔 시간 / 1번 인덱스엔 땅의 높이를 저장할 예정
        //큐나 스택..? 쓸게없는듯.

        //땅의 모든 높이들을 hashset으로 저장해서 중복값 없애서 result 배열에 저장하자. 이때 시간은 0 고정
        HashSet<Integer> heights=new HashSet<>();
        for(int i=0; i<ddang.length; i++){
            for(int j=0; j<ddang[i].length; j++){
                heights.add(ddang[i][j]);
                //중복값들 편하게 제거.
            }
        }

        Iterator heightsIt=heights.iterator();
        while(heightsIt.hasNext()){
          //  int height=((Integer) heightsIt.next()).intValue(); // Object->Integer->int 우욱..
//            System.out.println("height:"+height);
            //result.add(new Integer[]{0,height}); //
    //    }
//        System.out.println("hashset출력:"+heights.toString()); //hashset 출력해보기~
//        System.out.println("result출력:"+Arrays.deepToString(result.toArray())); // ArrayList의 모든 원소 출력하기
        // 무슨 원리지..? 나중에 알아보기.

        //본격 알고리즘.
        //result에 들어간 개수만큼 돌리고
        //각 입력된 땅의 개수마다 시간세서 입력하기.
        //땅을 쌓거나 제거하거나 위에 알고리즘대로 구현하기

      //  for(int k=0; k<result.size(); k++){
//            System.out.println("for문 시작");
           // int ddangH=result.get(k)[1]; // 1번 인덱스 가져오기
            int ddangH=((Integer) heightsIt.next()).intValue(); // Object->Integer->int 우욱..
//            System.out.println("ddangH:"+ddangH);

            //ddangH 높이를 기준으로 ddang고르게 만들기! > 걸리는 시간은!?
            //int time;

            //땅을 모두 순회하면서 ddangH높이에 맞춰서 쌓을 개수랑 제거할 개수 세기.
            //쌓을 개수가 넉넉하면 쌓기. 안되면 걍..제거하셈.......
            //어 문제점 .>>만약 내가 인벤에 블록개수가 넉넉하지 않아서 못 쌓으면 애초에 해당 ddangH높이로 맞추질 못함.
            //>> 그럼 ddangH-1 높이로 만들어서 저장해야함.
            //result에 ddangH-1 가 존재하는지 확인후에 저장하고, 없으면 추가해서 저장
            //기존에 있던 값이 있으면 시간을 비교해서 최소시간으로 저장하기.

          /*  int plus=0; // 1초
            int minus=0; // 2초

            for(int i=0; i<ddang.length; i++){
                for(int j=0; j<ddang[i].length; j++){
                    if(ddangH>ddang[i][j]) plus=ddangH-ddang[i][j]; // ddangH까지 쌓아올려야함.
                    else minus=ddang[i][j]-ddangH; // ddangH까지 제거해야함.
                }
            }

            System.out.println("plus:"+plus+" minus:"+minus);*/
            // 함수로 뽑음.
/*
            int[] plusAndMinus=getPlusAndMinus(ddang, ddangH);

            // 이제 쌓을수있는지 검사 //아 잠깐, 제거한 블록의 수도 포함해야함;;
            // 0번 인덱스  plus / 1번 인덱스  minus
            if(plusAndMinus[0]<=B+plusAndMinus[1]){ //인벤에 블록이 충분할 경우 > 일단 먼저 제거한 블록+인벤 블록 넉넉해~?
                result.get(k)[0]=getTime(plusAndMinus); // 시간 저장

            }else{ //여기서부터 복잡; 쌓질 못하니까 ddangH-1해서 다시 확인해야함..!
                // 어.. 어떻게 값을 반환하지 > 반환해야할게 두개라서 메서드는 그닥인듯..? 힝구..ㅠ >메서드에서 빼옴!ㅋㅋ
                // 다시 ddangH-1 찾기.
                plusAndMinus=getPlusAndMinus(ddang, ddangH-1); // 새로운 p/m 블록수 찾고
                int time=getTime(plusAndMinus); // 해당하는 시간 뽑고
                boolean isNope=true; // result에 없는가? ㅇㅇ없음 true

                //해당 높이가 result에 있는지 확인하기 위해서 순회시작
                for(int n=0; n<ddang.length; n++){
                    if(result.get(n)[1]==ddangH-1){ // result 리스트에서 해당하는 높이가 있으면
                        //시간 비교해서 작은 시작으로 저장 // 헐 처음에 근데 0입력되어잇으면...헉..스...무조건 0이니까 바꿔야함..
                        if(result.get(n)[0]==0) { // 시간이 0이라면
                            result.get(n)[0]=time; // 초기값이니까 비교안하고 바로 저장.

                        }else{
                            if(result.get(n)[0]>time) result.get(n)[0]=time; // 작은 시간으로 갱신하고 반복문 종료
                        }

                        isNope=false; // ㄴㄴ 있음 false
                        break;

                    }
                }

                if(isNope) { //없으면 result에 추가해주기
                    result.add(new Integer[]{time,ddangH-1});
                }

            }*/

            int[] plusAndMinus=getPlusAndMinus(ddang, ddangH);
            //플러스마이너스 블록으로 if문 분기(쌓을수있나?)
            if(plusAndMinus[0]<=B+plusAndMinus[1]){
                result.add(new Integer[]{getTime(plusAndMinus),ddangH});
            }else{
                plusAndMinus=getPlusAndMinus(ddang,ddangH-1);
                int time=getTime(plusAndMinus);

            }


        } // result구성 완료

        System.out.println("0시간 제거전:"+Arrays.deepToString(result.toArray()));
        // 시간이 0인값은 제거하고 ??이걸 굳이 하지말고 아이거해야해..안할수없어..
        // > 순회해서 시간이 제일 작은거 뽑고.. (시간이 0이면 제외)
        // 같은 시간을 갖고잇는 높이들 중에서 제일 높은 높이 뽑고.. 이거 한번에 할 방법은 없을까?
        // 시간 비교하고 > 높이 비교하고
//        for(int p=0; p<result.size(); p++){
//            if(result.get(p)[0]==0){ //시간이 0이면. 제거해.
//                result.remove(p);
//            }
////        }
//        result=result.stream().filter(u->u[0]!=0).collect(Collectors.toList()).toArray();

        ArrayList<Integer[]> newResult=new ArrayList<Integer[]>();

        Iterator iterator=result.iterator();
        while(iterator.hasNext()){
            Integer[] one=(Integer[]) iterator.next();
            if(one[0]!=0){
                newResult.add(one);
            }
        }

        System.out.println("0시간 제거후:"+Arrays.deepToString(newResult.toArray()));

        int minTime=newResult.get(0)[0]; // 제일 첫번째 시간값을 min에다가 넣어두고 비교한다
        int maxHeight=newResult.get(0)[1]; // 제일 첫번째 높이값을 max에다가 넣어두고 비교한다
        for(int q=1; q<newResult.size(); q++){  // 0번인덱스빼고 비교한다

            if(newResult.get(q)[0]<minTime){ // min보다 작으면
                minTime=newResult.get(q)[0]; // 시간이랑 높이 넣기.
                maxHeight=newResult.get(q)[1];

            }else if(newResult.get(q)[0]==minTime){ // min이랑 같으면 높이만 비교해서 넣기
                if(newResult.get(q)[1]>maxHeight)
                    maxHeight=newResult.get(q)[1];
            }
        }

//        System.out.println("답:"+minTime+" "+maxHeight);
        bw.write(minTime+" "+maxHeight);

        bw.close();
    }


    private static int[] getPlusAndMinus(int[][] ddang,int ddangH){

        int plus=0; // 1초
        int minus=0; // 2초

        for(int i=0; i<ddang.length; i++){
            for(int j=0; j<ddang[i].length; j++){
                if(ddangH>ddang[i][j]) plus=plus+(ddangH-ddang[i][j]); // ddangH까지 쌓아올려야함.
                else minus=minus+(ddang[i][j]-ddangH); // ddangH까지 제거해야함.
            }
        }

//        System.out.println("plus:"+plus+" minus:"+minus);


        return new int[]{plus,minus};

    }

    private static int getTime(int[] plusAndMinus){
        return plusAndMinus[0]+(plusAndMinus[1]*2);
    }
}
