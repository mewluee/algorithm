package 플로이드워셜_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 케빈케이컨의6단계법칙_1389 {

    static int N;
    static int count;
    static int answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        count=Integer.MAX_VALUE;
        answer=0;

        map = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(map[i], 987654321);
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 1;
        }
        //printMap();
        floyd();
        //printMap();
        getAnswer();
        System.out.println(answer);
    }

    static void floyd() {

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
    }

    static void getAnswer(){
        for (int i = 1; i <=N ; i++) {
            int kebin=0;
            for (int j = 1; j <=N ; j++) {
                if(i==j) continue;
                kebin+=map[i][j];
            }
            if(kebin<count){
                answer=i;
                count=kebin;
            }
        }
    }

    static void printMap(){
        for (int i = 0; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("---------------");
    }
}
