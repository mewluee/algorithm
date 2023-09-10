package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인구이동_16234 {
    static int N;
    static int L;
    static int R;
    static int[][] map;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine(), " ");
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        map=new int[N][N];
        for(int n=0; n<N; n++) {
            map[n]= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int day=-1;
        while(map!=null) {
            day++;
            map=cal();
        }
        System.out.println(day);
    }

    static int[][] cal() {

        boolean[][] visited=new boolean[N][N];
        int[][] copyMap=new int[N][N];
        for(int n=0; n<N; n++) {
            for(int m=0; m<N; m++) {
                copyMap[n][m]=map[n][m];
            }
        }
        //0 상 1 하 2 좌 3 우
        int[] dx= {-1, 1, 0, 0};
        int[] dy= {0, 0, -1, 1};

        boolean check=false;

        ArrayList<int[]> list=new ArrayList<>();
        Queue<int[]> queue=new LinkedList<>();

        for(int n=0; n<N; n++) {
            for(int m=0; m<N; m++) {
                queue.clear();
                list.clear();//초기화

                int[] start=new int[] {n,m};
                queue.add(start);
                list.add(start);

                visited[n][m]=true;
                int population=0;
                int union=1;

                //printMap(copyMap);
                while(!queue.isEmpty()) {
                    int[] now=queue.poll();
                    population+=map[now[0]][now[1]];
                    //System.out.println("["+now[0]+", "+now[1]+"]");
                    //System.out.println("->");

                    for(int i=0; i<4; i++) {
                        int nx=now[0]+dx[i];
                        int ny=now[1]+dy[i];

                        if(nx<0 || N<=nx || ny<0 || N<=ny) continue;
                        if(visited[nx][ny]) continue;

                        int difference=Math.abs(map[nx][ny]-map[now[0]][now[1]]);
                        if(difference<L || R<difference) continue;

                        int[] next=new int[] {nx, ny};
                        list.add(next);
                        queue.add(next);

                        visited[nx][ny]=true;
                        union++;

                        //System.out.println("[add]:"+nx+", "+ny);
                    }
                }

                if(list.size()==1) continue;

                check=true;
                //System.out.println("p:"+population+", u:"+union);
                population=population/union;
                //System.out.println("계산 후 p:"+population);

                for(int j=0; j<list.size(); j++) {
                    int[] now=list.get(j);
                    copyMap[now[0]][now[1]]=population;
                }

                //printMap(copyMap);
                //System.out.println("===============");
            }
        }

        if(!check) return null;
        else return copyMap;
    }

    static void printMap(int[][] arr) {
        for(int n=0; n<N; n++) {
            System.out.println(Arrays.toString(arr[n]));
        }
    }
}
