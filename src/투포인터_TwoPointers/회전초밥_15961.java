package 투포인터_TwoPointers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 회전초밥_15961 {


    //어떤 알고리즘?
    //슬라이딩 윈도우, 투포인터

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] list = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //로직
        //1. 중복이 안되게 일단 k개 자르고
        //2. 거기서 쿠폰 c가 있는지 검사
        //3. 최대값 갱신 하기

        int N=list[0];
        int d=list[1];
        int k=list[2];
        int c=list[3];

        int[] sushies=new int[N];

        for (int i = 0; i < N; i++) {
            sushies[i] = Integer.parseInt(br.readLine());
        }

        int k_count=0;

        // 시간초과 남.
        for (int i = 0; i < N - k + 1 ; i++) {
            int count= (int) Arrays.stream(Arrays.copyOfRange(sushies, i, i + k))
                    .filter(e->e!=c)
                    .distinct()
                    .count();

            if(count>k_count){
                k_count=count;
            }
        }

        br.close();
        System.out.println(k_count+1);

    }

    public static void main3(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] list = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //로직
        //1. 중복이 안되게 일단 k개 자르고
        //2. 거기서 쿠폰 c가 있는지 검사
        //3. 최대값 갱신 하기

        int N=list[0];
        int d=list[1];
        int k=list[2];
        int c=list[3];

        int[] sushies=new int[N];
        int[] distinct=new int[d+1]; //이게 바로 중복검사다 이말이야.

        for (int i = 0; i < N; i++) {
            sushies[i] = Integer.parseInt(br.readLine());
        }

        //10퍼에서 ㅎㅎ틀림!
        int s=0;
        int e=0;
        int max_count=0;
        int count=0;
        int eat=0;
        //TODO 식으로하지말고, 1. 배열 두배 만들기, 2.먹은 거 카운트하는 변수로 조건문 따지기
        while(s<N) {
            if(eat>k){
                if(distinct[sushies[s]]==1){
                    count--;
                }
                distinct[sushies[s]]--;
                eat--;
                s++;
            }else{
                if(distinct[sushies[e]]==0){
                    count++;
                }
                distinct[sushies[e]]++;
                e++;
                eat++;
                if(e>=N){
                    e=0;
                }
            }
            if(eat==k){
                if(distinct[c]>=1) {
                    if(count-1>max_count){
                        max_count=count-1;
                    }
                }else{
                    if(count>max_count){
                        max_count=count;
                    }
                }
            }
        }

        br.close();
        System.out.println(max_count+1);

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] list = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N=list[0];
        int d=list[1];
        int k=list[2];
        int c=list[3];

        int[] sushies=new int[N];
        int[] distinct=new int[d+1]; //이게 바로 중복검사다 이말이야.

        for (int i = 0; i < N; i++) {
            sushies[i] = Integer.parseInt(br.readLine());
        }

        int[] copy2sushies=new int[N*2];
        System.arraycopy(sushies, 0, copy2sushies, 0, N);
        for(int j=N; j<N*2; j++ ){
            copy2sushies[j]=sushies[j-N];
        }

        int s=0;
        int e=0;
        int max_count=0;
        int count=0;
        int eat=0;

        while(s<N) {
            if(eat>k){
                if(distinct[copy2sushies[s]]==1){
                    count--;
                }
                distinct[copy2sushies[s]]--;
                eat--;
                s++;
            }else{
                if(distinct[copy2sushies[e]]==0){
                    count++;
                }
                distinct[copy2sushies[e]]++;
                e++;
                eat++;

            }
            if(eat==k){
                if(distinct[c]>=1) {
                    if(count-1>max_count){
                        max_count=count-1;
                    }
                }else{
                    if(count>max_count){
                        max_count=count;
                    }
                }
            }
        }

        br.close();
        System.out.println(max_count+1);

    }
}
