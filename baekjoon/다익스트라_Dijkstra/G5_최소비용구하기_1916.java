package 다익스트라_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G5_최소비용구하기_1916 {
    static int N; //도시의 개수
    static int M; //버스의 개수
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());
        ArrayList<int[]>[] nodes=new ArrayList[N+1];
        int[] dist=new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int n=0; n<=N; n++) {
            nodes[n]=new ArrayList<>();
        }
        StringTokenizer st;
        for(int m=0;m<M; m++) {
            st=new StringTokenizer(br.readLine()," ");
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int cost=Integer.parseInt(st.nextToken());
            nodes[a].add(new int[] {b, cost});
        }
        st=new StringTokenizer(br.readLine(), " ");
        int start=Integer.parseInt(st.nextToken());
        int end=Integer.parseInt(st.nextToken());
        //입력 완료

        PriorityQueue<int[]> queue=new PriorityQueue<>((o1, o2)->o1[1]-o2[1]); //비용 오름차순
        queue.add(new int[] {start, 0});
        long result=0;
        while(!queue.isEmpty()) {
            //System.out.println("---------");
            int[] now=queue.poll();
            //System.out.println("now:"+Arrays.toString(now));
            if(now[0]==end) {
                result=now[1];
                break;
            }
            for(int i=0; i<nodes[now[0]].size(); i++) {
                int[] next=nodes[now[0]].get(i);
                //System.out.println("next:"+Arrays.toString(next));
                if(now[1]+next[1]<dist[next[0]]) {
                    queue.add(new int[] {next[0], now[1]+next[1]});
                    dist[next[0]]=now[1]+next[1];
                }
            }
        }

        System.out.println(result);

    }
}
