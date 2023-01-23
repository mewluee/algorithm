package binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class CuttingWood2805 {

    private String site="https://www.acmicpc.net/problem/2805";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        String[] strtrees=br.readLine().split(" "); //나무들의 높이

        if(N==1) {
            if(M==Integer.parseInt(strtrees[0])) bw.write(Integer.toString(M));
            else bw.write(Integer.toString(Integer.parseInt(strtrees[0])-M));
            bw.close();
            return;
        }

        //이진탐색트리를 어디에 적용해야 할까.
        //최대값찾을때?
        //이진탐색트리는 데이터가 정렬되어있어야함.
        //최대값은 그냥 뭐 그대로고..어처피 정렬해서검색하는건 똑같음.
        //내가 찾는 값은 M으로 하고
        //새로 계산한 값의 결과에 따라 그 값이 크면 작은 값으로 탐색하고
        //그 값이 작으면 큰 값으로 탐색..?
        //그러니까 결국 브루트포스를 안한다는 거지.
        //근데 왱..?

        //시간초과뜸, 근데 뜰 구간은 지금 max빼곤 없음..
        //bst구현했기 때문에 저부분은 ㅇㅇ..괜찮은뎅...
        //그럼그냥 max값안구하고, 바로M때려넣을까? 아냐 그래도 시간단축 꽤 되는데..음..
        //그럼 스트림 안쓰고 옮기면서 최대값까지 넣어보자

        //int[] intTrees=Stream.of(strtrees).mapToInt(Integer::parseInt).toArray();
        //int max= Arrays.stream(intTrees).max().getAsInt();
        int[] intTrees=new int[strtrees.length];
        int max=0;
        for(int n=0; n<strtrees.length; n++){
            intTrees[n]=Integer.parseInt(strtrees[n]);
            if(intTrees[n]>max) max=intTrees[n];
        }

        int result=bst(M, max-(M/2),intTrees);

        bw.write(Integer.toString(result));
        bw.close();

    }

    public static int bst(int M, int Height,int[] trees){

        int sum=0;
        for(int i=0; i<trees.length; i++){
            int one=trees[i]-Height;
            if(one<=0) one=0;
            sum=sum+one;
        }

        //System.out.println("sum:"+sum+" H:"+Height+" M:"+M);

        if(sum==M) return Height;
        else if(sum>M) return bst(M, Height+1, trees);
        else return bst(M, Height-1, trees);

    }


    public void badCode() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        String[] strtrees=br.readLine().split(" "); //나무들의 높이

        if(N==1) {
            if(M==Integer.parseInt(strtrees[0])) bw.write(Integer.toString(M));
            else bw.write(Integer.toString(Integer.parseInt(strtrees[0])-M));
            bw.close();
            return;
        }

        //브루트 포스 알고리즘 써야할거 같음
        //그런데 범위를 어떻게 줄일까?
        //최소 최대값...
        //최대값은 잘 모르겠음.
        //최소값은 일단 나무의 높이들중 제일 큰 나무 기준으로 -M한 높이가 최대높이임.
        //왜냐하면 최소값을 기준으로 위로 올리면서 값 비교할 거임(브루트포스)
        //왜냐하면!!! 말로 표현하기 좀 그렇네
        //왜냐하면~~ 예를 들어 10m가 필요한데 주어진 나무가 15 20 이면 20-10=10을 최소값으로 +1씩하면서
        //자른 나무들의 합이 M이상인 순간 멈추면 됨.


        //Stream으로 String[]을 Integer[] OR int[]로 만들기
        //Integer배열
        // Integer[] inttrees= Stream.of(strtrees).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);

        //int배열
        int[] intTrees= Stream.of(strtrees).mapToInt(Integer::parseInt).toArray();

        int max= Arrays.stream(intTrees).max().getAsInt();
        for(int i=max-M; i<=max; i++){
            int sum=0;
            //bw.write("H:"+Integer.toString(i)+"\n");
            //i가 H임.
            for(int j=0; j<N; j++){
                int one=intTrees[j]-i;
                if(one<=0) one=0;
                sum=sum+one; // 각 나무들을 잘랐을 때 토막난 나무들의 길이 더하기.
            }
            //bw.write("sum:"+Integer.toString(sum)+"\n");
            //조건문으로 나갈 타이밍 잡기
            if(sum<M){
                bw.write(Integer.toString(i-1));
                break;
            }

        }
        bw.close();
    }

}
