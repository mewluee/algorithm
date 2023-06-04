
import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

    static int[] dx={-1,0,1,0}; //상,좌,하,우
    static int[] dy={0,-1,0,1};

    static int R;
    static int C;
    static int T;

    static int[][] map;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        R=input[0];
        C=input[1];
        T=input[2];

        int[] airCleaner=new int[2];


        map=new int[R+1][C+1];

        for (int r = 1; r <= R; r++) {
            int tr=r;
            int[] inputMap=Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            IntStream.range(1,C+1)
                    .forEach(e->map[tr][e]=inputMap[e-1]);
        }

        //System.out.println(Arrays.deepToString(map));


    }

    public void airCleaning(){

    }

    public void moveMeonji(int x, int y){

        int count=0;
        int babyMeonji=map[x][y]/5;

        for (int i = 0; i < 4; i++) {
            int tx=x+dx[i];
            int ty=y+dy[i];

            if (0 < tx && tx <= R && 0 < ty && ty <= C) {
                if(map[tx][ty]!=-1){
                    count++;
                    map[tx][ty]=map[tx][ty]+babyMeonji;
                }
            }

        }

        map[x][y]-=count*babyMeonji;
    }
}