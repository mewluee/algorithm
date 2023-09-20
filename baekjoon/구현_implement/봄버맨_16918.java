package 구현_implement;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 봄버맨_16918 {


    //문제 구간합.

    static int R;
    static int C;
    static int N;
    static int[][] map;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        map=new int[R][C];

        for(int r=0; r<R; r++) {
            map[r]=br.readLine().chars().toArray();
            //System.out.println(Arrays.toString(map[r]));
        }

        N--;

        boolean flag=false;

        while(N>0) {

            int[][] copy=bombPlanting();
            N--;
            if(N==0) {
                flag=true;
                break;
            }
            map=bompExploded(copy);
            N--;
        }

        if(flag) {

            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    bw.append("O");
                }
                bw.append("\n");
            }
        }else {

            for(int r=0; r<R; r++) {
                for(int c=0; c<C; c++) {
                    bw.append((char)map[r][c]);
                }
                bw.append("\n");
            }
        }

        bw.flush();
        bw.close();
    }

    static int[][] bombPlanting() {
        //System.out.println("-------------");
        //폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치
        int[][] copy=new int[R][C];
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c]==46) { //.
                    copy[r][c]=79; // . -> O
                }else {
                    copy[r][c]=0; // 0
                }
            }

            //System.out.println(Arrays.toString(copy[r]));
        }

        return copy;
    }

    static int[][] bompExploded(int[][] copy) {
        int[] dx= {-1,1,0,0};
        int[] dy= {0,0,-1,1};

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c]==79) { //폭탄이면
                    for(int i=0; i<4; i++) {
                        int x=r+dx[i];
                        int y=c+dy[i];
                        if(x<0 || R<=x || y<0 || C<=y) continue;
                        //map[x][y]=0;
                        copy[x][y]=46; // 0 -> .
                    }
                    //map[r][c]=0;
                }
                if(copy[r][c]==0) copy[r][c]=46; // .
            }

        }

//		System.out.println("터진 후 결과:map");
//		print(map);
//
//		System.out.println("터진 후 결과:copy");
//		print(copy);

        return copy;
    }

    static void print(int[][] m) {
        for(int r=0; r<R; r++) {
            System.out.println(Arrays.toString(m[r]));
        }
    }
}
