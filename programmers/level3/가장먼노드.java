package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

    ArrayList<Integer>[] list;
    public int solution(int n, int[][] edge) {
        list=new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            list[i]=new ArrayList<>();
        }
        for(int i=0; i<edge.length; i++){
            //양방향
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        Queue<Integer> queue=new LinkedList<>();
        boolean[] visited=new boolean[n+1];
        int[] distance=new int[n+1]; //거리를 저장하는 배열
        queue.add(1);
        visited[1]=true;

        while(!queue.isEmpty()){
            int now=queue.poll();

            for(int next:list[now]){
                if(visited[next]) continue;
                distance[next]=distance[now]+1; //거리 저장(넘어온 노드의 거리값+1)
                queue.add(next);
                visited[next]=true;
            }
        }

        int max= Arrays.stream(distance).max().orElse(0);
        int answer=(int)Arrays.stream(distance).filter(e-> e==max).count();
        return answer;
    }
}
