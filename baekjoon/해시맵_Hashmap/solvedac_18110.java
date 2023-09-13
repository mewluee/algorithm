package 해시맵_Hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class solvedac_18110 {

    //문제
    //절사 평균 30%
    //위 15% 아래 15% 를 제외한 나머지 수로 평균을 구한다.
    //N명 -> 30%계산 -> 반으로 나누기 -> 반올림

    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map=new HashMap<>();

        int minus= (int)Math.round(N*0.15);

        for(int n=0; n<N; n++) {
            int input=Integer.parseInt(br.readLine());
            map.put(input, map.getOrDefault(input, 0)+1);
        }

        ArrayList<Integer> keys=new ArrayList<>(map.keySet());

        keys.sort((o1,o2)->o1-o2); //오름차순

        int min=0;
        int max=keys.size()-1;//인덱스 설정

        for(int m=0; m<minus; m++) {
            int key=keys.get(min);
            if(map.get(key)==0) {
                min++;
                m--;
                continue;
            }else {
                map.put(key, map.get(key)-1);
            }
        }
        for(int m=0; m<minus; m++) {
            int key=keys.get(max);
            if(map.get(key)==0) {
                max--;
                m--;
                continue;
            }else {
                map.put(key, map.get(key)-1);
            }
        }

        int sum=0;
        for(Integer key:keys) {
            sum+=key*map.get(key);
        }
        System.out.println(Math.round((float)sum/(N-(2*minus))));
    }
}
