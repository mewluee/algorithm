package 너비우선탐색_BFS;

import java.io.*;
import java.util.*;

public class 로봇청소기_4991_실패 {
    //문제
    //. 깨끗한 칸
    //* 더러운 칸 (10개 이하)
    //x 가구
    //o 로봇 청소기의 시작 위치
    //1번 방법: 청소기-먼지 거리를 다 재서 우선순위 큐에 넣고 짧은 거리에 있는 먼지부터 이동했다.
    //2번 방법: 청소기-먼지, 먼지-먼지 거리를 다 재서, 해당 거리들의 조합을 정해서 제일 짧은 거리로 탐색한다.(bfs->dfs)

    static char[][] map;
    static int[][] distances;
    static int[] start;
    static List<int[]> dusts;
    static int result;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while(true) {
            st=new StringTokenizer(br.readLine());
            int col=Integer.parseInt(st.nextToken());
            int row=Integer.parseInt(st.nextToken());
            if(row==0 && col==0) break;
            dusts=new ArrayList<>();
            map=new char[row][col];
            start=new int[2];
            for(int r=0; r<row; r++) {
                String str=br.readLine();
                for(int c=0; c<col; c++) {
                    map[r][c]=str.charAt(c);
                    if(map[r][c]=='o') start=new int[] {r,c};
                    if(map[r][c]=='*') dusts.add(new int[] {r,c});
                    //System.out.print(map[r][c]+" ");
                }
                //System.out.println();
            }
            //System.out.println("맵 입력 완료");

            //1번 방법
            //int distance=check(row, col, start);

            //2번 방법
            int distance=check2(row, col, start, dusts);

            //System.out.println("결과:"+distance);
            bw.append(Integer.toString(distance)+"\n");
        }

        bw.flush();
        bw.close();
    }

    //2번 방법
    static int check2(int row, int col, int[] start, List<int[]> dusts){
        int min=987654321;
        //2차원 배열
        distances=new int[dusts.size()+1][dusts.size()+1];
        //자기 자신한테 가는건 0
        //청소기와 모든 먼지들 간의 거리를 잰다.
        for(int i=0; i< dusts.size(); i++){
            int d=bfs(row, col, start, dusts.get(i));
            if(d==-1) return -1;
            distances[0][i+1]=distances[i+1][0]=d;
        }
        System.out.println(Arrays.toString(distances[0]));
        //모든 먼지와 먼지들 간의 거리를 잰다.
        for(int i=0; i< dusts.size(); i++){
            for(int j=i+1; j< dusts.size(); j++){
                int d=bfs(row, col, dusts.get(i), dusts.get(j));
                if(d==-1) return -1;
                distances[i+1][j+1]=distances[j+1][i+1]=d;
            }
        }
        System.out.println("먼지들까지하고 나서 출력");
        for(int i=0; i<dusts.size()+1; i++ ){
            System.out.println(Arrays.toString(distances[i]));
        }

        //조합
        boolean[] visited=new boolean[dusts.size()]; //7개의 먼지
        permu(dusts.size(),0, 0, visited);
        min=Math.min(result, min);

        return min;
    }

    static void permu(int goal, int start, int sum, boolean[] visited){
        if(goal==0){
            System.out.println("sum:"+sum);
            result=sum;
            return;
        }
        for(int i=start; i< dusts.size(); i++){
            if(visited[i]) continue;
            visited[i]=true;
            permu(goal-1, i+1, sum+distances[start][i], visited);
            visited[i]=false;
        }
    }

    //1번 방법
    static int check(int row, int col, int[] start){
        PriorityQueue<int[]> queue=new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[2]));
        int distance=0;
        boolean check=false;
        while(!check) {
            //1.모든 맵 검사해서 우선순위 큐에 넣기.
            for(int r=0; r<row; r++) {
                if(check) break;
                for(int c=0; c<col; c++) {
                    if(map[r][c]=='*') {
                        int sample=bfs(row, col, start, new int[] {r, c});
                        if(sample==-1) {
                            check=true;
                            distance=-1;
                            break;
                        }
                        queue.add(new int[] {r,c,sample});
                    }

                }
            }
            if(check) {
                //System.out.println(distance);
                break;
            }
            //2. 정렬 후 제일 가까운 지점을 꺼낸다.
            if(!queue.isEmpty()) {
                int[] best=queue.poll();
                map[best[0]][best[1]]='.';
                distance+=best[2];
                //System.out.println("누적 dis:"+distance);
                start=new int[] {best[0], best[1]};
                queue.clear();
            }else {
                break;
            }
        }

        return distance;
    }

    //이동횟수 최소는 출발위치-도착위치를 bfs로 검사해서 최단거리로만 해야한다.
    static int bfs(int r, int c, int[] start, int[] end) {
        Queue<int[]> queue=new LinkedList();
        queue.add(new int[] {start[0], start[1], 0});
        boolean[][] visited=new boolean[r][c];
        visited[start[0]][start[1]]=true;
        int answer=-1;

        int[] dx= {-1, 1, 0, 0};
        int[] dy= {0,0,-1,1};

        while(!queue.isEmpty()) {

            int[] now=queue.poll();
            if(now[0]==end[0] && now[1]==end[1]) {
                answer=now[2];
                break;
            }

            for(int i=0; i<4; i++) {
                int x=now[0]+dx[i];
                int y=now[1]+dy[i];

                if(x<0 || r<=x || y<0 || c<=y) continue;
                if(visited[x][y]) continue;
                if(map[x][y]=='x') continue;

                queue.add(new int[] {x, y, now[2]+1});
                visited[x][y]=true;
            }
        }
        //System.out.print(Arrays.toString(start));
        //System.out.println("->"+Arrays.toString(end));
        System.out.println("answer:"+answer);
        return answer;
    }

}
