package 플로이드워셜_FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class 플로이드_11404 {

    private final static int MAX_NUMBER = 98765432;
    //100번 더할수있으니까 비용최대x100

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] map = new int[n + 1][n + 1];

        IntStream.range(0, n + 1)
                .forEach(e -> Arrays.fill(map[e], MAX_NUMBER));

        for (int i = 0; i < m; i++) {
            int[] lines = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (map[lines[0]][lines[1]] > lines[2])
                map[lines[0]][lines[1]] = lines[2];
        }

        //k가 앞에 나와야한다.
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if(i==j) continue; //i==k, j==k 는 비교할 필요 없다. 어처피 최소값이라서.
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(map[i][j]==MAX_NUMBER?0+" ":map[i][j]+" ");
            }
            System.out.println();
        }

    }
}
