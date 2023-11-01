package 수학_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class G4_우체국_2141_2285 {

    //문제: 2285, 2141 골드4 - 우체국
    //알고리즘: 그리디, 중간값
    //요약
    //N입력->마을의 개수
    //각
    //우체국 위치 어디? 나라에서 각 사람들끼리의 거리의 합이 최소가 되는 위치
    //일직선상에 위치한 마을.

    //우체국은 인구수가 절반이 되는 시점의 지역에 존재해야 한다.
    //[주의] 절반=(전체인구수+1)/2 왜..?
    //출력할 때 n+1이 틀린 이유는..중간에 데이터가 없을 수도 있나..?
    static int N;
    public static void main(String[] args)throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine());
        List<Point> list=new ArrayList<>();
        long allPopulation=0;

        for(int n=1; n<=N; n++) {
            StringTokenizer st=new StringTokenizer(br.readLine()," ");
            int index=Integer.parseInt(st.nextToken());
            int population=Integer.parseInt(st.nextToken());
            Point point = new Point(index, population);
            allPopulation+=population;
            list.add(point);
        }
        Collections.sort(list);
        long sample=0;
        for(int n=0; n<N; n++) {
            sample+=list.get(n).population;
            if(sample>=(allPopulation+1)/2) { //+1안해주면 틀림
                System.out.println(list.get(n).index);
                return;
            }
        }


    }

    static class Point implements Comparable<Point>{
        int index;
        int population;

        Point(int index, int population){
            this.index=index;
            this.population=population;
        }

        @Override
        public int compareTo(Point o) {
            return this.index-o.index;
        }
    }
}
