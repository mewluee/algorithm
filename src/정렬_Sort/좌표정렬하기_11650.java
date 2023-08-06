package 정렬_Sort;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class 좌표정렬하기_11650 {

    public void result() throws IOException {

        //백준 11650번 풀이중
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int input=Integer.parseInt(bf.readLine());
        int[][] arrInt=new int[input][2];

        for(int i=0; i<input; i++){
            String[] str=bf.readLine().split(" ");
            arrInt[i][0]=Integer.parseInt(str[0]);
            arrInt[i][1]=Integer.parseInt(str[1]);
        }

        /*bw.write("정렬전:-----\n");
        for(int j=0; j<input; j++){
            bw.write("["+arrInt[j][0]+","+arrInt[j][1]+"]\n");
        }
*/
      /*  Arrays.sort(arrInt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o1[1]-o2[1]; // 첫번째 인덱스의 값이 같다면 두번째 인덱스의 오름차순(양수)
                else return o1[0]-o2[0]; // 아니면 첫번째 인덱스의 값으로 오름차순(양수)
            }
        });*/

        Arrays.sort(arrInt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return o1[0]-o2[0]; // y좌표가 같으면 x좌표 오름차순
                else return o1[1]-o2[1]; // y좌표 오름차순
            }
        });
/*
        bw.write("정렬후:-----\n");*/
        for(int j=0; j<input; j++){
            bw.write(arrInt[j][0]+" "+arrInt[j][1]+"\n");
        }

        bw.close();
    }
}
