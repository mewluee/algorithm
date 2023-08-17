package 분할정복_DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 색종이만들기_2630 {
    private String site="https://www.acmicpc.net/problem/2630";

    public void result() throws IOException {


        //분할정복 알고리즘
        //재귀사용하기

        //재귀를 어떻게 사용해야 할까용?
        //재귀 호출할때마다 4분의 1로 조각나눠지니까 +4씩 증가하고
        //재귀 탈출 조건은 가로세로 사이즈가2일때..?
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Scanner s=new Scanner(System.in);

        int N=Integer.parseInt(br.readLine());
        int[][] papers=new int[N][N];

        StringTokenizer st;

        for(int n=0; n<N; n++){
            st=new StringTokenizer(br.readLine(), " ");
            for(int m=0; m<N; m++){
                papers[n][m]=Integer.parseInt(st.nextToken());
            }
        }

        //System.out.println(Arrays.deepToString(papers));


        //재귀 구현하기
        //매개변수로 배열을 잘라서 줄지
        //visited배열을 이용해서 이미 지나친곳이면,...어 근데 여긴 지나쳐도 다시 들어가야해서
        //이건 이 배열 못쓸거같앙
        //그럼 배열을 새로 잘라서 주는 방향으로 해보자.
        //재귀의 반환값은 어떻게할까?
        //올라가서 최종적으로 더하는걸룽?
        //아님 static변수줘서 계속 누적해서 더할깡?
        //올라가서 최종적으로 더하는거해보장.
        int[] result={0,0};
        cuttingPapers(N, papers, 0, 0, result);

        System.out.println(result[0]);
        System.out.println(result[1]);
        //System.out.println(Arrays.toString(result));

    }


    public void cuttingPapers(int side, int[][] papers, int x, int y, int[] result){

        //System.out.println("-".repeat(20));
        //System.out.print("변의 길이:"+side+" ");
        //System.out.println("x:"+x+", y:"+y);
        //재귀 탈출
        if(side==1){
            if(papers[x][y]==0) // 하양색
                result[0]++;
            else
                result[1]++;
            //System.out.println("result:"+Arrays.toString(result));
        }else{
            //4조각으로 나눌 준비 드가자.
            //4조각으로 나눌지 말지 조건문 만들어야함.
            //papers을 이제 하나씩 검사하자.
            //1로 시작했다면 그 이후 0이 나오는 순간 다시 재귀 호출.
            //int start=papers[x][y]; // 시작값을 기준으로 비교할 거라서 새로 변수 선언해주기. 근데 사실상 안해도 괜찮을지둥?
            //일단 papers배열 검사시작
            //System.out.println("["+x+"]["+y+"] 시작해서 변의 길이:"+side);
            for(int i=x; i<x+side; i++){ //x가 0이고 side가 8이면 0~7까지 검사함 굿.
                for(int j=y; j<y+side; j++){
                    //System.out.println("papers["+i+"]["+j+"] ="+papers[i][j]);
                    if(papers[x][y]!=papers[i][j]) {
                        //다른값이면 바로 어디냐 그 재귀해야함.근데 사각형 쪼개야하자나용...후에이...ㅇ엥ㅇ...으엥ㄱ...이거하기싫어..
                        //매개변수로 시작인덱스를 제공하는걸로할까..?
                        //오 이거 괜찮을듯,ㅎ힣히!
                        // 0~7 >> 0~3 , 4~7  0, 0 / 0,4 / 4,0 / 4, 4
                        //System.out.println("4분할!");
                        cuttingPapers(side/2, papers, x,y,result);
                        cuttingPapers(side/2, papers, x, y+side/2,result);
                        cuttingPapers(side/2, papers, x+side/2, y,result);
                        cuttingPapers(side/2, papers, x+side/2, y+side/2,result);
                        return;
                    }
                }
            }

            if(papers[x][y]==0) result[0]++;
            else result[1]++;
            //System.out.println("result:"+Arrays.toString(result));
        }

    }
}
