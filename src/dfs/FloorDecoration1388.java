package dfs;

import java.io.*;

public class FloorDecoration1388 {
    private String site="https://www.acmicpc.net/problem/1388";

    //그런데 dfs로 구현안함;;
    //다시 만들어야할듯..ㄷㅅㄷ...
    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strarr=br.readLine().split(" ");
        int N = Integer.parseInt(strarr[0]); // 세로크기 행
        int M = Integer.parseInt(strarr[1]); // 가로크기 열

        char[][] bottom=new char[N][M];//''로 초기화(?)
        boolean[][] visited=new boolean[N][M]; //false로 초기화

        //일단 입력받고
        for(int n=0; n<N; n++){
            String floor=br.readLine();
            bottom[n]=floor.toCharArray();
        }

        //-랑 ㅣ만날때마다 다른 처리
        int count=0;
        for(int n=0; n<N; n++){
            for(int m=0; m<M; m++){
                if(!visited[n][m]){ //false면 실행됨

                    count++;

                    if(bottom[n][m]=='-'){ //오른쪽으로 쭉 검사!
                        //count++;
                        for(int i=m+1; i<M; i++){
                            if(bottom[n][i]=='-'&&!visited[n][i]) visited[n][i]=true;
                            else break;
                        }
                    }else{
                        for(int j=n+1; j<N; j++){
                            if(bottom[j][m]=='|'&&!visited[j][m]) visited[j][m]=true;
                            else break;
                        }
                    }
                }
            }
        }

        bw.write(Integer.toString(count));
        bw.close();
    }
}
