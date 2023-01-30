package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class RGcolorWeakness10026 {
    private String site="https://www.acmicpc.net/problem/10026";

    public void result() throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        char[][] input=new char[N][];
        boolean[][] visited=new boolean[N][];
        boolean[][] visited2=new boolean[N][];

        for(int n=0; n<N; n++){
            char[] str=br.readLine().toCharArray();
            input[n]=str;
            visited[n]=new boolean[str.length];
            visited2[n]=new boolean[str.length];
        }

        //System.out.println(Arrays.deepToString(input));

        int num1=checkNormal(N, visited[0].length,input, visited);
        int num2=checkColorWeakness(N, visited[0].length,input, visited2);
        System.out.println(num1+" "+num2);

        br.close();

    }


    public int checkNormal(int R, int C, char[][] input, boolean[][] visited){


        Queue<int[]> queue=new LinkedList<>();
        int count=0;

        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(!visited[r][c]){
                    queue.add(new int[]{r,c});
                    visited[r][c]=true;

                    while(!queue.isEmpty()){

                        int[] one=queue.poll();
                        char check=input[one[0]][one[1]];
                        //System.out.println("char:"+check);
                        //왼 오 위 아
                        int[] xn={-1, 1, 0, 0};
                        int[] yn={0, 0, -1, 1};

                        for(int i=0; i<4; i++){
                            int row=one[0]+xn[i];
                            int col=one[1]+yn[i];
                            if( !(row<0 || row>=R || col<0 || col>=C) && !visited[row][col] && input[row][col]==check){ //집어넣을 조건
                                queue.add(new int[]{row, col});
                                visited[row][col]=true;
                            }
                        }
                    }

                    count++;

                }
            }
        }

        return count;


    }

    public int checkColorWeakness(int R, int C, char[][] input, boolean[][] visited){


        Queue<int[]> queue=new LinkedList<>();
        int count=0;

        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(!visited[r][c]){
                    queue.add(new int[]{r,c});
                    visited[r][c]=true;

                    while(!queue.isEmpty()){

                        int[] one=queue.poll();
                        char check=input[one[0]][one[1]];
                        //System.out.println("char:"+check);
                        //왼 오 위 아
                        int[] xn={-1, 1, 0, 0};
                        int[] yn={0, 0, -1, 1};

                        for(int i=0; i<4; i++){
                            int row=one[0]+xn[i];
                            int col=one[1]+yn[i];

                            if( !(row<0 || row>=R || col<0 || col>=C) && !visited[row][col] ){ //집어넣을 조건

                                if( (check=='R' && input[row][col]=='G') || (check=='G' && input[row][col]=='R') || (check==input[row][col])){
                                    queue.add(new int[]{row, col});
                                    visited[row][col]=true;
                                }

                            }
                        }
                    }

                    count++;

                }
            }
        }

        return count;
    }
}
