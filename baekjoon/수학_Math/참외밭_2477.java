package 수학_Math;

import java.io.*;

public class 참외밭_2477 {
    private String site="https://www.acmicpc.net/problem/2477";

    public void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine()); //1m당 참외 개수
        int[][] sides=new int[6][2];

        for(int i=0; i<6; i++){ //육각형 (6번의 변 입력받음)
            String[] strarr=br.readLine().split(" ");
            int N = Integer.parseInt(strarr[0]); // 변의 방향 동1(오른) 서2(왼) 남3(아래) 북4(위)
            int M = Integer.parseInt(strarr[1]); // 변의 길이
            sides[i]=new int[]{N, M};
        }

        //System.out.println(Arrays.deepToString(sides));

        //빼야하는 사각형의 특징
        //해당 변은 두번 반복해서 나옴.
        //조건1) -2(앞) 혹은 +2(뒤) 인덱스에 똑같은 변이 있음.
        //조건1 만족시 조건2) 내 앞뒤-1+1로 똑같은 변(나랑 다른 숫자)이면 >> 현재 변 선택(곱해서 빼야할 작은 변임)
        //조건1 불만족시) 뺴야할 큰 변임.

        int[] big=new int[2];
        int[] small=new int[2];

        for(int i=0;i<6; i++){
            int up2=i-2;
            int down2=i+2;

            if(up2<0) up2=up2+6;
            if(down2>=6) down2=down2-6;

            if(sides[i][0]==sides[up2][0] || sides[i][0]==sides[down2][0]){ // 작은변
                int up1=i-1;
                int down1=i+1;

                if(up1<0) up1=up1+6;
                if(down1>=6) down1=down1-6;

                if(sides[up1][0]==sides[down1][0]){ //빼야하는 작은변
                    if(small[0]==0) small[0]=sides[i][1]; //그냥 배열에 넣을때 아예 비어있으면 제일 처음에 넣기.
                    else small[1]=sides[i][1];
                    //System.out.println("small"+Arrays.toString(small));

                }
            }else{ // 큰변
                if(big[0]==0) big[0]=sides[i][1]; //그냥 배열에 넣을때 아예 비어있으면 제일 처음에 넣기.
                else big[1]=sides[i][1];
                //System.out.println("big"+Arrays.toString(big));
            }
        }

        int bigSquare=big[0]*big[1];
        int smallSquare=small[0]*small[1];
        //System.out.println(bigSquare+", "+smallSquare);
        int hexagon=bigSquare-smallSquare;

        bw.write(Integer.toString(hexagon*K));
        bw.close();
    }
}
