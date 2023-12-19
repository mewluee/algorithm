package 백트래킹_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_랭퍼든수열쟁이야_15918 {

    //문제: 골드5 랭퍼든 수열쟁이야
    //요약:
    //총 2n개의 수열
    //1~n이상 자연수가 각각 2개씩
    //x = y 번째 수는 동일해야 한다.
    //풀이: 백트래킹
    //x와 y번째 수가 동일하려면 y-x-1인 수여야 한다.

    static int[] arr;
    static int N;
    static int x;
    static int y;
    static int result=0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        N=Integer.parseInt(st.nextToken());
        x=Integer.parseInt(st.nextToken());
        y=Integer.parseInt(st.nextToken());
        int same=y-x-1;
        arr=new int[2*N+1];
        visited=new boolean[N+1];
        arr[x]=same;
        arr[y]=same;
        visited[same]=true;
        dfs(0);
        System.out.println(result);
    }

    public static void dfs(int index) {
        //System.out.println("=======index:"+index);
        //System.out.println("arr:"+Arrays.toString(arr));
        if(index>=2*N) {
            result++;
            return;
        }
        if(arr[index+1]!=0) {
            dfs(index+1);
            return;
        }
        for(int n=1; n<=N; n++) {
            //System.out.println("n:"+n);
            if(visited[n]) continue;
            int num=n;
            if(index+1+num+1>2*N) continue;
            if(arr[index+1+num+1]!=0) continue;

            arr[index+1]=n;
            arr[index+1+num+1]=n;
            visited[n]=true;
            dfs(index+1);
            arr[index+1]=0;
            arr[index+1+num+1]=0;
            visited[n]=false;
        }
    }

}
