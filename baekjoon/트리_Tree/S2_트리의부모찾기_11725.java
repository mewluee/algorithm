package 트리_Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2_트리의부모찾기_11725 {
    //문제: 실버2 트리의 부모 찾기
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] nodes=new ArrayList[N+1];
        for(int n=0; n<N+1; n++){
            nodes[n]=new ArrayList<>();
        }
        int[] result=new int[N+1];
        StringTokenizer st;

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            nodes[node1].add(node2);
            nodes[node2].add(node1);
        }

        Queue<Integer> queue=new LinkedList<>();
        boolean[] visited=new boolean[N+1];
        queue.add(1);
        visited[1]=true;

        while(!queue.isEmpty()){
            int now=queue.poll();
            for(int i=0;i<nodes[now].size(); i++){
                if(visited[nodes[now].get(i)]) continue;
                result[nodes[now].get(i)]=now;
                visited[now]=true;
                queue.add(nodes[now].get(i));
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int n=2; n<N+1; n++){
            sb.append(result[n]+"\n");
        }
        System.out.println(sb.toString());
    }
    //배열로 구현하고 싶었으나, 적절한 처리방법이 떠오르지 않아서 시간초과.
    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nodes = new int[N + 1];
        Arrays.fill(nodes, -1);
        nodes[1] = 0;
        StringTokenizer st;
//        PriorityQueue<int[]> after=new PriorityQueue<>((o1, o2)->{
//            //o1.0 / o1.1 / o2.0 / o2.1
//            if(nodes[o1[0]]!=-1){
//                return -1;
//            }else if(nodes[o1[1]]!=-1){
//                return -1;
//            }else if(nodes[o2[0]]!=-1){
//                return 1;
//            } else if(nodes[o2[1]]!=-1){
//                return 1;
//            }else{
//                return 0;
//            }
//        });
        LinkedList<int[]> queue = new LinkedList<>();

        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            queue.add(new int[]{node1, node2});
            //if (nodes[node1] == -1) nodes[node1] = node2;
            //if (nodes[node2] == -1) nodes[node2] = node1;
        }


        while (!queue.isEmpty()) {

            Collections.sort(queue, (o1, o2) -> {
                //o1.0 / o1.1 / o2.0 / o2.1
                if (nodes[o1[0]] != -1) {
                    return -1;
                } else if (nodes[o1[1]] != -1) {
                    return -1;
                } else if (nodes[o2[0]] != -1) {
                    return 1;
                } else if(nodes[o2[1]]!=-1){
                    return 1;
                }else{
                    return 0;
                }
            });
            int[] now = queue.poll();
            if (nodes[now[0]] == -1) nodes[now[0]] = now[1];
            if (nodes[now[1]] == -1) nodes[now[1]] = now[0];
        }
        for (int i = 2; i <= N; i++) {
            System.out.println(nodes[i]);
        }
    }
}
