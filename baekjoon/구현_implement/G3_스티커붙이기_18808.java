package 구현_implement;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_스티커붙이기_18808 {

    //문제: 골드5 2174 로봇 시뮬레이션
    //알고리즘:구현
    //요약:
    //직사각형 노트북에 스티커 붙이기
    //스티커는 다 연결되어 있다.
    //노트북의 위쪽, 왼쪽부터 스티커 채워가기
    //붙일 수 없으면, 시계방향으로 90도 회전해서 다시 위쪽,왼쪽부터 붙여보기, 4번 반복
    //4번해도 안되면 스티커 버리기
    //다 붙인 후 몇개의 칸이 스티커로 붙여졌는지 구하기
    //풀이1:
    //스티커가 연결되어 있다 -> BFS를 생각함.
    //BFS로 검사해서 조건문에 따라 map을 true로 처리할려고했는데 너무 데이터가 많이 들어감.+회전 표현?
    //풀이2:
    //기본적으로 스티커로 입력된 배열(10x10)이 작기 때문에, 배열로 데이터를 저장해도 괜찮을 것 같음.
    //회전도 표현해야한다.
    //
    static int[][] map;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        int K = Integer.parseInt(st.nextToken()); //스티커 개수
        map = new int[N][M];
        int result = 0;
        //빈공간(0), 붙이는 중(1), 이미 붙인 곳은(-1)
        int[] start = null;
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[A][B];
            int count = 0;
            for (int a = 0; a < A; a++) {
                int[] inputs = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int b = 0; b < B; b++) {
                    if (inputs[b] == 1) {
                        count++;
                        sticker[a][b] = true;
                        if (start == null) start = new int[]{a, b};
                    }
                }
            }
            //System.out.println("현재 스티커:");
            //printSticker(A, sticker);
//			System.out.println("회전 후:");
//			boolean[][] rotated=rotationArray(A,B,sticker);
//			System.out.println("한번 더회전 후:");
//			boolean[][] rotated2=rotationArray(B,A,rotated);

            boolean isAttached = false;

            for (int c = 0; c < 4; c++) {
                for (int n = 0; n < N; n++) {
                    if (isAttached) break;
                    for (int m = 0; m < M; m++) {
                        //System.out.println("["+n+", "+m+"]");

                        if (isAttached(n, m, A, B, sticker)) {
                            //System.out.println("붙일 수 있다!");
                            isAttached = true;
                            //System.out.println("붙이기 전:");
                            //printMap(N);
                            attachSticker(n, m, A, B);
                            //System.out.println("붙이기 후:");
                            //printMap(N);
                            break;
                        } else {
                            //System.out.println("붙일 수 없다!");
                            //System.out.println("롤백 전:");
                            //printMap(N);
                            rollbackMap(n, m, A, B);
                            //System.out.println("롤백 후:");
                            //printMap(N);
                        }

                    }
                }

                if (isAttached) break;
                sticker = rotationArray(A, B, sticker);
                int dummy = A;
                A = B;
                B = dummy;
            }

            if (isAttached) result += count;
        }
        System.out.println(result);
    }

    static void rollbackMap(int n, int m, int A, int B) {
        if (N < A || M < B || N < n + A || M < m + B) return;
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < B; b++) {
                //System.out.println("["+a+", "+b+"]");
                if (map[n + a][m + b] == 1) { //붙이던 1을 0으로.
                    map[n + a][m + b] = 0;
                }
            }
        }
    }

    static void attachSticker(int n, int m, int A, int B) {
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < B; b++) {
                if (map[n + a][m + b] == 1) { //붙이던 1을 -1으로.
                    map[n + a][m + b] = -1;
                }
            }
        }
    }

    static boolean isAttached(int n, int m, int A, int B, boolean[][] sticker) {
        if (N < A || M < B || N < n + A || M < m + B) return false;
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < B; b++) {
                if (!sticker[a][b]) continue;
                if (sticker[a][b] && map[n + a][m + b] != 0) {
                    //스티커는 붙여야하는데, 붙일 수 없는 공간일 때
                    return false; //붙일 수 없다!
                }
                map[n + a][m + b] = 1;
            }
        }

        return true;
    }

    static boolean[][] rotationArray(int A, int B, boolean[][] origin) {
        boolean[][] rotated = new boolean[B][A];
        for (int a = 0; a < A; a++) {
            for (int b = 0; b < B; b++) {
                rotated[b][A - 1 - a] = origin[a][b];
            }
        }
        //System.out.println("회전 성공:");
        //printSticker(B, rotated);
        return rotated;
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //행
        int M = Integer.parseInt(st.nextToken()); //열
        int K = Integer.parseInt(st.nextToken()); //스티커 개수
        int[][] map = new int[N][M];
        int[] start = null;
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[][] sticker = new boolean[A][B];
            for (int a = 0; a < A; a++) {
                int[] inputs = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int b = 0; b < B; b++) {
                    if (inputs[b] == 1) {
                        sticker[a][b] = true;
                        if (start == null) start = new int[]{a, b};
                    }
                }
                printSticker(A, sticker);

                int[] dx = {0, 0, -1, 1};
                int[] dy = {-1, 1, 0, 0};

                for (int n = 0; n < N; n++) {
                    for (int m = 0; m < M; m++) {
                        if (map[n][m] == 0) {
                            Queue<int[]> queue = new LinkedList<>();
                            queue.add(start);
                            boolean[][] visited = new boolean[A][B];
                            visited[start[0]][start[1]] = true;
                            boolean attached = true; //기본 붙일 수 있다
                            while (!queue.isEmpty()) {
                                int[] now = queue.poll();
                                boolean check = false; //기본 갈 수 있냐 없냐 확인, 갈 수 있다가 기본
                                for (int d = 0; d < 4; d++) {
                                    int x = now[0] + dx[d];
                                    int y = now[1] + dy[d];

                                    if (x < 0 || A <= x || y < 0 || B <= y) continue;
                                    if (visited[x][y]) continue;
                                    if (!sticker[x][y] || map[n + x][m + y] > 0) continue;
                                    //스티커가 false이면 가면안됨. 패스
                                    //맵이 true이면 가면안됨. 패스
                                    if (sticker[x][y] && map[n + x][m + y] > 0) {
                                        check = true; //갈 수 없다.
                                        break;
                                    }
                                    //스티커로는 가야하는데 true인데, map도 true이면 스티커를 붙일 수 없는 공간임.
                                    //끝내버려야한다.
                                    //즉, 스티커도 true이고
                                    visited[x][y] = true;
                                    queue.add(new int[]{x, y});
                                    map[x][y]++;
                                }
                                if (check) {
                                    attached = false; //붙일 수 없다.
                                    break;
                                }
                            }

                        }
                    }
                }
            }
            System.out.println(Arrays.toString(start));
        }
    }

    static void printSticker(int N, boolean[][] map) {
        for (int n = 0; n < N; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
        System.out.println("---------------");
    }

    static void printMap(int N) {
        for (int n = 0; n < N; n++) {
            System.out.println(Arrays.toString(map[n]));
        }
        System.out.println("---------------");
    }

}
