
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int M=Integer.parseInt(strarr[0]);//가로 칸
        int N=Integer.parseInt(strarr[1]);//세로 칸

        boolean[][] visited=new boolean[N][M]; //false로 초기화됨.
        int[][] box=new int[N][M];

        // 1 익은 토마토
        // 0 익지 않은 토마토
        // -1 토마토 없음
        for(int n=0; n<N; n++){
            String[] strTomatos=br.readLine().split(" ");
            int[] tomatos=Stream.of(strTomatos).mapToInt(Integer::parseInt).toArray();
            box[n]=tomatos;
        }

        System.out.println(Arrays.deepToString(box));

        //출력은 다 익는 최소날짜
        //다 익어있는 상태면 날짜 0 출력
        //다 못익는 상태라면 -1 출력

        //dfs라고 했는데, 탐색을 하는 그 조건이 뭐지?
        //하루 지날때마다 인접토마토가 익어야하고
        //dfs로 그걸 탐색한다고?
        //완전 탐색...음..
        //한칸씩 탐색할때마다 날짜 카운트는 증가해야하고
        //아! 깊이우선이 아니고 너비우선이로구만 ㅇ3ㅇ..
        //너비우선은 Queue아닌감?
        //아귀간지렁
        //놀러와서 호텔에서 코딩하고잇는게 레전드...ㅋ..ㅋㅋ..



    }



}