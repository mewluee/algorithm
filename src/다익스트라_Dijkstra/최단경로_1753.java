package 다익스트라_Dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로_1753 {

    static int V;
    static int E;
    static int INF=987654321;
    static ArrayList<Edge>[] lists;
    static boolean[] visited;
    public static void main(String[] args)throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        lists=new ArrayList[V+1];
        for (int v = 0; v <V+1 ; v++) {
            lists[v]=new ArrayList<>();
        }
        int start=Integer.parseInt(br.readLine());

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            lists[u].add(new Edge(v,w));
        }

        //int[] result=dij(start);
        int[] result=dij2(start);
        for (int i = 1; i < V+1; i++) {
            if(result[i]==INF){
                bw.write("INF\n");
            }else{
                bw.write(result[i]+"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    //89%->시간초과
    static int[] dij2(int startNode){
        PriorityQueue<Edge> pq=new PriorityQueue();
        int[] dist=new int[V+1];
        Arrays.fill(dist, INF);
        dist[startNode]=0;
        pq.add(new Edge(startNode, 0));
        visited=new boolean[V+1];

        while(!pq.isEmpty()){
            Edge now=pq.poll();
            if(visited[now.dest]) continue;
            //경유지 = now.dest

            for(Edge next:lists[now.dest]){
                int via_weight=now.weight+next.weight;
                if(via_weight<dist[next.dest]){
                    dist[next.dest]=via_weight;
                    pq.add(new Edge(next.dest, dist[next.dest]));
                }
            }

        }
        return dist;
    }

    static int[] dij(int startNode){

        int[] d=new int[V+1];
        Arrays.fill(d, INF);
        for (int v = 1; v < V+1; v++) {
            if(v==startNode) {
                d[v]=0;
                continue;
            }
            for(Edge edge:lists[startNode]){
                if(v==edge.dest && edge.weight<d[v]) d[v]=edge.weight;
            }
        }
        //System.out.println("초기값:"+Arrays.toString(d));

        visited=new boolean[V+1];
        visited[startNode]=true;
        //v-1번 경유함(나자신 경유는 뺀다)
        //단순 카운트용
        for (int v = 1; v < V; v++) {
            //node 경유
            int node = findMinNode(startNode, d);
            //System.out.println("경유:"+node);
            if(node==0) break;
            //System.out.println("전:"+Arrays.toString(d));
            for(Edge edge:lists[node]){ //경유지에서 갈 수 있는 경로들 리스트
                int now=edge.dest; //경유지에서 갈 수 있는 노드 번호
                int via_weight=d[node]+edge.weight; //(시작점->경유지 거리) + (경유지->도착지 거리)
                //System.out.println("now:"+now);
                //System.out.println("via w:"+via_weight+", d[now]:"+d[now]);
                if(via_weight<d[now]){ // 경유거리와 (시작점->도착지)거리 비교
                    d[now]=via_weight; // 최단거리 갱신
                }
            }
            //System.out.println("후:"+Arrays.toString(d));
            //System.out.println("=============");
        }

        return d;
    }

    static int findMinNode(int startNode, int[] d){
        //System.out.println("find min node 입장");
        //System.out.println("start:"+startNode);
        int minNode=0;
        int minValue=INF;
        for (int v = 1; v < V+1; v++) {
            //System.out.println("v:"+v+", d[v]:"+d[v]+", min:"+minValue);
            //System.out.println("visited:"+Arrays.toString(visited));
            if(!visited[v] && d[v]<minValue){
                minValue=d[v];
                minNode=v;
            }
        }

        visited[minNode]=true;
        //System.out.println("-------------");
        return minNode;
    }

    static class Edge implements Comparable<Edge>{
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "dest=" + dest +
                    ", weight=" + weight +
                    '}';
        }
    }
}
