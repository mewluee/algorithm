

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
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


}