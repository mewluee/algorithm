package floydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Floyd11404 {

    private final static int MAX_NUMBER=200001;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] map = new int[n+1][n+1];

        IntStream.range(0,n+1)
                .forEach(e-> Arrays.fill(map[e], MAX_NUMBER));

        for (int i = 0; i < m; i++) {
            int[] lines = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if(map[lines[0]][lines[1]]>lines[2])
                map[lines[0]][lines[1]]=lines[2];
        }

        System.out.println(Arrays.deepToString(map));
        System.out.println("------------------------------");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i==j) continue;
                for (int k = 1; k <= n; k++) {
                    if(i==k || j==k) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        System.out.println(Arrays.deepToString(map));

    }
}
