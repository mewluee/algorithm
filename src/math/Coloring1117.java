package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coloring1117 {
    String site="https://www.acmicpc.net/problem/1117";


    public static void result() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long W = Integer.parseInt(st.nextToken());
        long H = Integer.parseInt(st.nextToken());
        long f = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long x1 = Integer.parseInt(st.nextToken());
        long y1 = Integer.parseInt(st.nextToken());
        long x2 = Integer.parseInt(st.nextToken());
        long y2 = Integer.parseInt(st.nextToken());

        // W H f c x1 y1 x2 y2
        // 5 6 2 2  1  1  3  2

        //아~ 칠한게 흰색.
        //안친해진 부분을 찾는게 답.


        //난 매번 새로운 배열을 만들어 줄까했는데 그럴 필요가 없다.

        //로직
        //세로선 f를 기준으로 2개인지 1개인지 나누고 (세로선 f를 기준으로 한번 접었을때 겹치는 부분 2개, 안겹치는 부분 1개)
        //가로선으로 c번 접을 때 어처피 동일한 크기로 접기 때문에, 색칠한 칸이 어디냐에 따라 값이 달라진다.
        //어처피 가로선을 기준으로 접을땐 c+1번 곱하는 건 동일하다.
        //세로선을 기준으로 겹치는 부분만 걸러주면 된다.

        //가로선의 f는 W-f 와 f 중 작은 값으로 해야한다.
        //왼쪽에서 오른쪽으로 접으면 항상 왼쪽이 접는 쪽이 된다.
        //f를 기준으로 접으니까아..
        //5를 기준으로 f=2일때 접으면 2(f),3(W-f)중 작은쪽 2가 f로 갱신되고
        //f=3일때 접으면 2(W-f),3(f)중 작은쪽 2가 f로 갱신된다.
        //f가 중간이상일 때 접으면 W-f가 겹치는 쪽이 된다.
        long coloring;
        if (x2 <= Math.min(f, W - f)) {
            coloring = (x2 - x1) * 2 * (y2 - y1) * (c + 1);
        } else if (x1 < Math.min(f, W - f) && Math.min(f, W - f) < x2) {
            coloring = ((Math.min(f, W - f) - x1) * 2 + (x2 - Math.min(f, W - f))) * (y2 - y1) * (c + 1);
        } else {
            coloring = (x2 - x1) * (y2 - y1) * (c + 1);
        }

        System.out.println(W * H - coloring);


    }
}
