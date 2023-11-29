package 큐_Queue;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S3_크리스마스선물_14235 {
    //문제: 실버3 크리스마스 선물
    //요약:
    //N개의 방문횟수
    //a입력
    //0 -> 아이들 만남
    //숫자 -> 숫자 개수만큼 추가 입력(선물 충전, 선물의 가치)
    //아이들을 만날대마다 가장 가치가 큰 선물하나

    //풀이: 그리디, 우선순위큐

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pqueue = new PriorityQueue<>((e1, e2) -> e2 - e1);

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            if (A > 0) {
                for (int a = 0; a < A; a++) {
                    int gift = Integer.parseInt(st.nextToken());
                    pqueue.add(gift);
                }
            } else {
                if (pqueue.isEmpty()) bw.append("-1\n");
                else {
                    bw.append(pqueue.poll() + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
