package binarysearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class CuttingWood2805 {

    private String site="https://www.acmicpc.net/problem/2805";

    public static int count=0;

    //이걸로 제출함.
    public void result3() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        StringTokenizer strtrees=new StringTokenizer(br.readLine()," "); //나무들의 높이

        int[] intTrees=new int[N];
        int min=0;
        int max=0;
        for(int n=0; n<N; n++){
            intTrees[n]=Integer.parseInt(strtrees.nextToken());
            if(intTrees[n]>max) max=intTrees[n];
        }

        //bw.write(Integer.toString(max)+", "+Integer.toString(min)+"\n");
        while(min<max){
            //bw.write("min:"+Integer.toString(min)+", max:"+Integer.toString(max)+"\n");
            int mid=(min+max)/2;
            long sum=0;
            for(int i=0; i<intTrees.length; i++){

                int one=intTrees[i]-mid;
                if(one>0) sum=sum+one;

            }

            //bw.write("mid:"+Integer.toString(mid)+" sum:"+Long.toString(sum)+"\n");

            //lower랑 upper중에 뭘 써야하는 걸까?
            //적어도 M의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이의 최대값..
            //즉 이상. 그러면 lower!
            /*if(M>=sum){//mid일때 구한 sum이 M보다 크니까 상한값을 낮춰야함.>>(수정)mid일때 구한 sum이 M보다 크니까 mid값을 올리기 위해서는 하한값을 올려야함.
                //내가 쓴 코드   if(M<=sum) lower일떄 << upper일때>> if(M<sum)
                //이렇게 했더니 SUM값이 계속 커져서 MAX값이 자꾸 낮아지게됨.
                //MAX값이 낮아지니까 SUM은 더 커짐.계에속.
                //수정된 코드  M>=sum
                //자 이걸 해석해보자...음...
                //코드들과 다른점이 있다면 기준점이 다른 느낌?
                //max값이 어느순간 고정되고, 그 뒤로는 계속 min값을 올려서 max값까지 끌고올리고 종료가 됨.

                max=mid;
            }else{
                min=mid+1;
            }*/
            if(M<=sum){
                min=mid+1;
            }else{ //M>sum //mid일때 구한 sum값이 M보다 작을때 >> 즉 작으니까 mid값을 낮춰야함. 즉 상향선을 낮춘다.
                max=mid;
            }
            //이렇게 코드짜니까 여기선 min값을 어느순간 고정시키고 max값을 계속 낮추게됨.
            //이땐 내가 구한 mid값에서 +1한 min값이 고정되고 max가 min값까지 내려가서 반복문이 종료하게 됨으로.
            //내가 max나 min값을 출력할 때 -1해야 그때의 mid값을 반환할 수 있다.
            //왜 min값이 고정되는가..?min값이 고정되는 의미..???
            //min값이 고정된다는건 max값이 min값보단 항상 크다고 가정하면
            //특정 값 이상을 충족시킨다는 의미..?????? 그건 max값이 고정될때도 똑같음.
            //둘다 하나로 고정되는 순간


        }

        bw.write(Integer.toString(max-1));
        bw.close();

    }

    public void result2() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        String[] strtrees=br.readLine().split(" "); //나무들의 높이

        //나무의 수가 1이면 그냐앙ㅇ~~~ 바로처리!
        if(N==1) {
            if(M==Integer.parseInt(strtrees[0])) bw.write(Integer.toString(M));
            else bw.write(Integer.toString(Integer.parseInt(strtrees[0])-M));
            bw.close();
            return;
        }

        int[] intTrees=new int[strtrees.length];
        int min=0;
        int max=0;
        for(int n=0; n<strtrees.length; n++){
            intTrees[n]=Integer.parseInt(strtrees[n]);
            if(intTrees[n]>max) max=intTrees[n];
        }

        bw.write(Integer.toString(max)+", "+Integer.toString(min));
        while(min<max){
            int mid=(min+max)/2;
            long sum=0;
            for(int i=0; i<intTrees.length; i++){

                int one=intTrees[i]-mid;
                if(one>0) sum=sum+one;

            }
            //lower랑 upper중에 뭘 써야하는 걸까?
            //적어도 M의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이의 최대값..
            //즉 이상. 그러면 lower!
            if(M<=sum){
                max=mid;
            }else{
                min=mid+1;
            }
        }

        bw.write(max);
        bw.close();



    }
    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        String[] strtrees=br.readLine().split(" "); //나무들의 높이

        //나무의 수가 1이면 그냐앙ㅇ~~~ 바로처리!
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

        int result=bst(0,max,M,intTrees);

        bw.write(Integer.toString(result));
        bw.close();

    }

    public static int bst(int start, int end, int find,int[] trees){

        count++;
        System.out.println("count:"+count);
        int Height=(start+end)/2;
        int sum=0;
        for(int i=0; i<trees.length; i++){
            int one=trees[i]-Height;
            if(one<=0) one=0;
            sum=sum+one;
        }

        //System.out.println("sum:"+sum+" H:"+Height+" M:"+M);

        if(sum==find) return Height;
        else if(sum>find) return bst(Height+1,end,find, trees); //sum값이 크다는건 높이를 올려서 잘리는 통나무의 길이를 줄여야함.
        else return bst(start,Height-1,find, trees);

    }



    //브루트포스 사용해서 했었음.
    public void badCode() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N=Integer.parseInt(strarr[0]);//나무의 수
        int M=Integer.parseInt(strarr[1]);//나무의 길이

        String[] strtrees=br.readLine().split(" "); //나무들의 높이


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
