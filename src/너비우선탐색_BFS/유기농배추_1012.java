package 너비우선탐색_BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class 유기농배추_1012 {
    private String site="https://www.acmicpc.net/problem/1012";

    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test_Count=Integer.parseInt(br.readLine());

        for(int test_case=0; test_case<test_Count; test_case++){

            String[] strarr = br.readLine().split(" ");
            int M = Integer.parseInt(strarr[0]);//가로 칸
            int N = Integer.parseInt(strarr[1]);//세로 칸
            int C = Integer.parseInt(strarr[2]);//배추 개수 cabbage

            boolean[][] visited=new boolean[N][M]; //다 false로 초기화

            int[][] cabbages=new int[N][M]; //다 0으로 초기화
            for(int c=0; c<C; c++){

                String[] cabbageArr=br.readLine().split(" ");
                int n=Integer.parseInt(cabbageArr[1]);
                int m=Integer.parseInt(cabbageArr[0]);
                cabbages[n][m]=1;
            }

            //System.out.println(Arrays.deepToString(cabbages));

            Queue<int[]> queue=new LinkedList<>();
            //큐에는 {n,m} 넣을거임.

            //문제 자체는 토마토랑 다를게 없음. 오히려 카운트 세는거라서 뭐 무난무난한듯?
            //근데 토마토 조건문이랑 그런게 좀 마음에 안듬. 효율적인 부분?
            //이 문제 시작점이랑 불리안 배열부분 조금 고민해야할 듯.
            //어?근데 컴포넌트 문제랑 좀 비슷한 거 같은뎅?
            //아 거긴 1차원 배열이고 여긴 2차원 배열이라서 조금 귀찮네..음
            //처음부터 순차적으로 검사하는뎅 1만나면 그때 bfs시작하면 되고, 그때 방문했던 노드들은 불리안배열로 체크.
            //cabbages가 1일때 visited도 다 1이면 불리안 변수의 값은 true로 유지되고
            //아닐경우 false여서 와일문 다시 또 ㄱㄱ
            //너무 검사량이 많고,, 따로 메서드를 뺄까?
            //아냐 굳이 그럴 필요없는거같앙.


            //bw.write("\n");

            int count=0;

            for(int n=0; n<N; n++){
                for(int m=0; m<M; m++){
                    if( cabbages[n][m]==1 && !visited[n][m] ){ // 1이고 방문 안한거면~
                        //bw.write("조건문 실행됨\n");
                        queue.add(new int[]{n,m});
                        visited[n][m]=true;

                        while(!queue.isEmpty()){
                            //bw.write("while문 실행됨\n");

                            int size=queue.size();

                            //bw.write("size:"+size+"\n");
                            for(int i=0; i<size; i++){
                                int[] one=queue.poll();

                                int up=one[0]-1;
                                int down=one[0]+1;
                                int left=one[1]-1;
                                int right=one[1]+1;

                                if(up>=0 && cabbages[up][one[1]]==1 && !visited[up][one[1]]){
                                    //bw.write("up,m:"+up+", "+one[1]+"\n");
                                    queue.add(new int[]{up,one[1]});
                                    visited[up][one[1]]=true;
                                }
                                if(down<N && cabbages[down][one[1]]==1 && !visited[down][one[1]] ){
                                    //bw.write("down,m:"+down+", "+one[1]+"\n");
                                    queue.add(new int[]{down,one[1]});
                                    visited[down][one[1]]=true;
                                }
                                if(left>=0 && cabbages[one[0]][left]==1 && !visited[one[0]][left]){
                                    //bw.write("n,left:"+n+", "+left+"\n");
                                    queue.add(new int[]{one[0],left});
                                    visited[one[0]][left]=true;
                                }
                                if(right<M && cabbages[one[0]][right]==1 && !visited[one[0]][right]){
                                    //bw.write("n,right:"+n+", "+right+"\n");
                                    queue.add(new int[]{one[0],right});
                                    visited[one[0]][right]=true;
                                }
                            }
                        }

                        count++;
                    }
                }
            }

            bw.write(Integer.toString(count)+"\n");
        }

        bw.close();

    }
}
