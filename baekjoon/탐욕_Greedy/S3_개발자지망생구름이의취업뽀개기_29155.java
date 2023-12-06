package 탐욕_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3_개발자지망생구름이의취업뽀개기_29155 {
    //문제: 실버3 개발자 지망생 구름이의 취업 뽀개기
    //요약:
    //난이도 1~5
    //순서->n부터 n과 같거나 증가하는 순서대로
    //N개의 문제들을 푸는데 걸릴 예상 시간 모두 체크
    //(휴식시간개념 : 문제별, 난이도별 걸리는 시간)
    //같은 난이도->두문제를 푸는데 걸리는 시간의 차이만큼 필요
    //높은 난이도->60분의 시간 필요
    //구름이가 문제를 푸는데 걸리는 시간 = 푼 문제의 예상시간 합 + 문제사이 휴식시간의 합
    //계획한 문제를 푸는데 필요한 최소 시간 구하기
    //예시 풀이
    //1단계 3개
    //2단계 1개
    //3단계 1개
    //4단계 1개
    //5단계 1개
    //총 7문제를 풀것이다.
    //10개의 문제가 소개된다.
    //각 문제는 원하는 만큼 풀수있도록 넉넉하게 존재한다.
    //
    //알고리즘:정렬
    //
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //주어진 문제수

        int[] problems = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray(); //단계 1-5 -> 인덱스 0-4 총 더하면 풀어야하는 문제 갯수.

        StringTokenizer st;
        PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> {
            if (e1[0] == e2[0]) {
                return e1[1] - e2[1];
            } else {
                return e1[0] - e2[0];
            }
        });
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int level = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            queue.add(new int[]{level - 1, time});
        }
        int first = queue.peek()[1];
        int time = 0;
        int before = 0;
        boolean levelUp = false;
        for (int i = 0; i < 5; i++) {
            //int remain=problems[i];
            while (problems[i] > 0) {
                //int[] now=queue.poll();
                //System.out.println(Arrays.toString(now));
                //if(now[0] < i) //내가 필요한건 i레벨인데 뽑힌건 더 낮은 레벨이면 안된다.
                int[] now;
                do {
                    now = queue.poll();
                    //System.out.println(Arrays.toString(now));
                } while (now[0] < i);
                //System.out.println("선택된거:"+Arrays.toString(now));
                int breaktime;
                if (levelUp) {//60분더한걸로 끝내야한다.
                    breaktime = 60;
                    levelUp = false;
                } else {
                    breaktime = now[1] - before; //휴식시간 계산
                }
                //System.out.println("휴식시간:"+breaktime);
                time += now[1] + breaktime; //문제푸는 시간
                //System.out.println("누적시간:"+time);
                before = now[1];
                problems[i]--;
            }
            before = 0;
            levelUp = true;
        }
        System.out.println(time - first);
    }
}
