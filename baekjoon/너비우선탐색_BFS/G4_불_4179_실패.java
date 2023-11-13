package 너비우선탐색_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_불_4179_실패 {


    //문제: 골드4 4179 불!
    //알고리즘: bfs
    //요약:
    //미로 탈출
    //불에 타기전에 탈출 여부, 얼마나 빨리 탈출하는지
    //지훈과 불 -> 매 분, 한칸씩 수평 또는 수직으로 이동 (=4방향 이동)
    //불은 각 지점에서 네방향으로 확산
    //미로의 가장자리에 접한 공간에서 탈출 가능 -> 제일 가장자리를 이동해도 된다, 단 # 인지 . 인지 검사작업 필요
    //벽은 통과 못함
    //R 미로 행 C 미로 열
    //# 벽 . 이동가능 J 지훈 위치 F 불
    //탈출 불가능 -> IMPOSSIBLE
    //탈출 가능 -> 가장 빠른 탈출시간 출력

    //풀이1:
    //가장 빠른 탈출시간 BFS (조건 만족, 가중치 다 동일)
    //맵에다가 그대로 표시하면서 할건지..BFS니까 큐
    //큐에다가 담아야하니까 모든 맵을 복사해서 넣고 다녔다.
    //메모리 초과...
    //하지만 모든 반례 모음은 다 맞았다.

    //풀이2:
    //불이 확산되는 형태는 경우의 수가 나눠지는 것이 아니다.(즉, 상하좌우 중에 선택이 아니라, 상하좌우로 무조건 감.)
    //맵에 시간을 적어서 몇 분에 어디에 불이 확산됬는지 표현할 수 있다.
    //

    public static void main4(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] origin = new int[R][C];
        int result = 0;

        int[] dx = {-1, 1, 0, 0}; //상하 좌우
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> fires = new LinkedList<>();

        for (int r = 0; r < R; r++) {
            char[] arr = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (arr[c] == 'J') {
                    queue.add(new int[]{r, c, 1});
                    origin[r][c] = 0;
                }
                if (arr[c] == 'F') {
                    fires.add(new int[]{r, c, 0});
                    origin[r][c] = 1;
                }
                if (arr[c] == '#') origin[r][c] = -1;
            }
        }
//        print(R, origin);
//        System.out.println("위에가 초기값");
        while (!queue.isEmpty()) {
            //System.out.println("---------------");
            int[] nowJ = queue.poll();
            //System.out.println("j:" + Arrays.toString(nowJ));

            if (nowJ[0] == 0 || nowJ[0] == R - 1 || nowJ[1] == 0 || nowJ[1] == C - 1) {
                result = nowJ[2];
                break;
            }

            //System.out.println("불확산전======");
            //print(R, origin);
            while(!fires.isEmpty()){
                int[] nowF = fires.poll();
                //System.out.println("f:" + Arrays.toString(nowF));
                for (int d = 0; d < 4; d++) {
                    if (nowF == null) continue;
                    int xf = nowF[0] + dx[d];
                    int yf = nowF[1] + dy[d];
                    if (0 <= xf && xf < R && 0 <= yf && yf < C && origin[xf][yf] == 0) {
                        fires.add(new int[]{xf, yf, nowF[2] + 1});
                        origin[xf][yf] = nowF[2] + 1;
                    }
                }
            }

            //System.out.println("불확산후======");
            //print(R, origin);

            for (int d = 0; d < 4; d++) {

                int xj = nowJ[0] + dx[d];
                int yj = nowJ[1] + dy[d];
                if (0 <= xj && xj < R && 0 <= yj && yj < C && origin[xj][yj] != -1) {
                    if (nowJ[2] < origin[xj][yj] || origin[xj][yj] == 0) { //이동가능
                        queue.add(new int[]{xj, yj, nowJ[2] + 1});
                        origin[nowJ[0]][nowJ[1]] = -1; //방문처리
                    }
                }
            }
        }

        if (result == 0)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(result);
    }

    static void print(int R, int[][] origin) {
        for (int r = 0; r < R; r++) {
            System.out.println(Arrays.toString(origin[r]));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] origin = new int[R][C];
        int result = 0;

        int[] dx = {-1, 1, 0, 0}; //상하 좌우
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> fires = new LinkedList<>();

        for (int r = 0; r < R; r++) {
            char[] arr = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (arr[c] == 'J') {
                    queue.add(new int[]{r, c, 0});
                    origin[r][c] = 0;
                }
                if (arr[c] == 'F') {
                    fires.add(new int[]{r, c});
                    origin[r][c] = 1;
                }
                if (arr[c] == '#') origin[r][c] = -1;
            }
        }

        //불 맵부터 만들기
        while (!fires.isEmpty()) {
            int[] now = fires.poll();

            for (int d = 0; d < 4; d++) {
                int x = now[0] + dx[d];
                int y = now[1] + dy[d];
                if (x < 0 || R <= x || y < 0 || C <= y) continue;
                if (origin[x][y] != 0) continue;
                fires.add(new int[]{x, y, origin[now[0]][now[1]] + 1});
                origin[x][y] = origin[now[0]][now[1]] + 1;
            }
        }

        for (int r = 0; r < R; r++) {
            System.out.println(Arrays.toString(origin[r]));
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            origin[now[0]][now[1]] = -1; //방문처리

            if (now[0] == 0 || now[0] == R - 1 || now[1] == 0 || now[1] == C - 1) {
                result = now[2];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int x = now[0] + dx[d];
                int y = now[1] + dy[d];
                if (x < 0 || R <= x || y < 0 || C <= y) continue;
                if (origin[x][y] == -1) continue;
                if (now[2] < origin[x][y] || origin[x][y] == 0) { //이동가능
                    queue.add(new int[]{x, y, now[2] + 1});
                }
            }
        }

        if (result == 0)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(result);


    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] origin = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        int[] start = {0, 0};
        for (int r = 0; r < R; r++) {
            char[] arr = br.readLine().toCharArray();
            for (int c = 0; c < C; c++) {
                if (arr[c] == 'J') start = new int[]{r, c};
                if (arr[c] == '#') visited[r][c] = true;
                origin[r][c] = arr[c];
            }
        }

        boolean isEscape = false;
        int result = 0;
        int[] dx = {-1, 1, 0, 0}; //상하 좌우
        int[] dy = {0, 0, -1, 1};
        Queue<Step> queue = new LinkedList<>();
        visited[start[0]][start[1]] = true;

        queue.add(new Step(0, origin, start, visited));
        while (!queue.isEmpty()) {
            Step now = queue.poll();

            if (now.position[0] == 0 || now.position[0] == R - 1 || now.position[1] == 0 || now.position[1] == C - 1) {
                isEscape = true;
                result = now.minute;
                break;
            }

            List<int[]> fires = new ArrayList<>();
            char[][] copymap = new char[R][C];
            boolean[][] copyvisited = new boolean[R][C];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    copyvisited[r][c] = now.visited[r][c];
                    copymap[r][c] = now.map[r][c];
                    if (now.map[r][c] == 'F') fires.add(new int[]{r, c});
                }
            }

            for (int[] fire : fires) {
                for (int d = 0; d < 4; d++) {
                    int x = fire[0] + dx[d];
                    int y = fire[1] + dy[d];
                    if (x < 0 || R <= x || y < 0 || C <= y) continue;
                    if (now.map[x][y] == '#' || now.map[x][y] == 'F') continue;
                    copymap[x][y] = 'F'; //J있어도 불확산.
                }

            }


            for (int d = 0; d < 4; d++) {
                int x = now.position[0] + dx[d];
                int y = now.position[1] + dy[d];

                if (x < 0 || R <= x || y < 0 || C <= y) continue;
                if (copymap[x][y] != '.') continue;
                if (visited[x][y]) continue;
                copyvisited[x][y] = true;
                queue.add(new Step(now.minute + 1, copymap, new int[]{x, y}, copyvisited));
            }
        }

        if (isEscape) System.out.println(result + 1);
        else System.out.println("IMPOSSIBLE");

    }

    static class Step {
        int minute;
        char[][] map;
        int[] position;
        boolean[][] visited;

        public Step(int minute, char[][] map, int[] position, boolean[][] visited) {
            this.minute = minute;
            this.map = map;
            this.position = position;
            this.visited = visited;
        }
    }
}
