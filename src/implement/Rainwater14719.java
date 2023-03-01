package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rainwater14719 {

    private String site = "https://www.acmicpc.net/problem/14719";

    static int H;
    static int W;

    static int[] blocks;

    public static void result() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //한줄씩 가져와서 공간을 비교하면 될 것 같다.

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];

        st = new StringTokenizer(br.readLine(), " ");
        for (int w = 0; w < W; w++) {
            blocks[w] = Integer.parseInt(st.nextToken());
        }

        //System.out.println(Arrays.toString(blocks));

        //한줄씩 뜯어온다 -> 즉 배열값을 -1씩 한다.
        //바닥부터 검사하게 된다.
        //로직

        int count=0;

        for (int h = 0; h < H; h++) {

            for (int w = 0; w < W; w++) {

                if(blocks[w]!=0){//기둥이 있다!
                    int subCount=0;
                    boolean flag=false;

                    for(int u=w+1; u<W; u++){

                        if(blocks[u]==0){
                            subCount++;

                        }else{
                            flag=true;

                            if(u<W-1){//뒤에 공간이 더 있다면..
                                w=u-1;
                            }else{
                                w=u;
                            }
                            break;
                        }
                    }
                    if(flag) count=count+subCount;
                }

            }

            for (int w = 0; w < W; w++) {
                blocks[w]=blocks[w]-1>0?blocks[w]-1:0;

            }

        }

        System.out.println(count);

    }

    /*
        for (int h = 0; h < H; h++) {
            //System.out.println("\n"+(h+1)+"층------>");
            for (int w = 0; w < W; w++) {

                //blocks[w]=blocks[w]-1>0 ? blocks[w]-1:0;
                //기둥이 있을 때 깊이 검사하기.
                //System.out.println("blocks:"+blocks[w]);

                if(blocks[w]!=0){//기둥이 있다!
                    //System.out.println("기둥!");
                    int subCount=0;
                    boolean flag=false;
                    for(int u=w+1; u<W; u++){
                        //System.out.println("(내부) w:"+w+", u:"+u+", b[u]="+blocks[u]);
                        if(blocks[u]==0){
                            //System.out.println("sub증가");
                            subCount++;
                        }else{
                            flag=true;
                            if(u<W-1){//뒤에 공간이 더 있다면..
                                w=u-1;
                                //System.out.println("1나오는 w:"+w);
                            }else{
                                w=u;
                                //System.out.println("2나오는 w:"+w);
                            }
                            break;
                        }
                    }
                    if(flag) count=count+subCount;
                    //System.out.println("count:"+count);
                }

            }

            for (int w = 0; w < W; w++) {
                blocks[w]=blocks[w]-1>0?blocks[w]-1:0;

            }

            //System.out.println("결과:"+Arrays.toString(blocks));

        }

        System.out.println(count);*/
}
