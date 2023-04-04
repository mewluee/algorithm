package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DefendTheCastle1236 {

    /*
    음...
    모든 행과 열에 한명 이상의 경비원이 있어야한다.
    그럼 경비원을 놓을 때 행도 만족하고 열도 만족하는 방향으로
    보통 그 교차지점이 가운데 대각선 줄인데...

    그리드 알고리즘?
    1. 가운데 대각선 줄 우선으로 증가시키고 x,y둘다 +1씩됨.
     >> 단 +1씩하고 해당 가로 세로 줄이 다 0일떄만 교차점에 세워두기
    2. 가로 세로가 비대칭일 경우

    .. 좀 그런데

    이거 배열 두개 만들고,
    각 배열은 가로줄,세로줄에 기사가 있는지 없는지 체크하는 용
    가로줄에 빈 인덱스를 기준으로
    해당 인덱스의 세로줄을 검사해서 없으면 교차점에 세워두고
    세로줄에 있으면 걍 넘어가면됨!

    아 잠시만 복잡해 아우 잠시만..

    입력받을때 한 행으로 입력받으니까.
    한 줄 입력받을때마다 X가 있는 인덱스는 true 처리해놓는다. (열 배열에 처리)
    한 줄 입력받을때 그 줄에 X가 한번이라도 있으면 true 처리해놓는다. (행 배열에 처리)

    행의 false의 개수와
    열의 false의 개수를 비교해서
    개수가 같은 수만큼 공통으로 두고, 나머지 개수만큼 더하면 결과값인거같은데.




     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs=br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        char[][] map = new char[N][M];

        boolean[] rows = new boolean[N];
        boolean[] cols = new boolean[M];

        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = str.charAt(m);
                if (map[n][m]=='X') {
                    cols[m]=true;
                    rows[n]=true;
                }
            }
        }

/*        System.out.println(Arrays.toString(rows));
        System.out.println(Arrays.toString(cols));*/

        int row_count=0;
        for (int n = 0; n < N; n++) {
            if (!rows[n]) {
                row_count++;
            }
        }
        int col_count=0;
        for (int m = 0; m < M; m++) {
            if (!cols[m]) {
                col_count++;
            }
        }

        int sub = Math.min(row_count, col_count);

        System.out.println(sub+(row_count-sub)+(col_count-sub));


    }
}
