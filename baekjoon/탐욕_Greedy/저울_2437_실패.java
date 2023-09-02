package 탐욕_Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 저울_2437_실패 {

    /*문제 요약
     *결과:주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값
     *
     *  입력값 -> 정렬
     *  정렬한 배열에서 인덱스 순서대로 만들 수 있는 무게의 경우의 수 구하기
     *  */
    static int[] chus;
    static int N;
    static boolean[] visited;
    static boolean[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        N=Integer.parseInt(br.readLine());
        chus=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        for(int n=0; n<N; n++) {
            chus[n]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(chus);
        if(chus[0]==1) {
            result=new boolean[1000001];
            for(int i=1; i<N; i++) {
                visited=new boolean[N];
                dfs(0, i, 0);
            }

            for(int j=1; j<1000001; j++) {
                if(!result[j]) {
                    System.out.println(j);
                    break;
                }
            }
        }else {
            System.out.println(1);
        }

    }

    static void dfs(int depth, int goal, int n) {
        if(depth==goal) {
            int sum=0;
            for(int i=0; i<N; i++) {
                if(visited[i]) sum+=chus[i];
            }
            if(sum<=1000000) result[sum]=true;
            return;
        }
        for(int i=n; i<N; i++) {
            visited[i]=true;
            dfs(depth+1, goal, i+1);
            visited[i]=false;
        }
    }

    static void print() {
        System.out.print("[");
        int sum=0;
        for(int i=0; i<N; i++) {
            if(visited[i]) {
                System.out.print(i+" ");
                sum+=chus[i];
            }
        }
        result[sum]=true;
        System.out.print("]="+sum+"\n");
    }
}
