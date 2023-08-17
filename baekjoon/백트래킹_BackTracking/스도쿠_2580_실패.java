package 백트래킹_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class 스도쿠_2580_실패 {

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < 9; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(inputs[j]);
                if (num == 0) {
                    queue.add(new Point(i, j));
                }
                map[i][j] = num;
            }
        }

        while (!queue.isEmpty()){
            Point point=queue.poll();
            //System.out.println("p:"+point.x+","+point.y);

            checkRow(point);
            if(map[point.x][point.y]!=0) continue;
            checkCol(point);
            if(map[point.x][point.y]!=0) continue;
            checkSquare(point);
            if(map[point.x][point.y]==0) {
                queue.add(point);
            }
        }


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void checkRow(Point point) {

        int[] numArr = new int[10];
        for (int i = 0; i < 9; i++) {
            numArr[map[point.x][i]]++;
        }
        //System.out.println(Arrays.toString(numArr));
        if (numArr[0] == 1) {
            for (int i = 1; i < 10; i++) {
                if (numArr[i] == 0) {
                    map[point.x][point.y] = i;
                }
            }
        }
        //System.out.println(Arrays.toString(map[point.x]));
    }

    static void checkCol(Point point) {
        int[] numArr = new int[10];
        for (int i = 0; i < 9; i++) {
            numArr[map[i][point.y]]++;
        }
        //System.out.println(Arrays.toString(numArr));
        if (numArr[0] == 1) {
            for (int i = 1; i < 10; i++) {
                if (numArr[i] == 0) {
                    map[point.x][point.y] = i;
                }
            }
        }
        //printMap(map);
    }

    static void checkSquare(Point point) {
        Point start = new Point((point.x / 3) * 3, (point.y / 3) * 3);
        //System.out.println("start:"+start.x+","+start.y);
        int[] numArr = new int[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numArr[map[start.x+i][start.y+j]]++;
            }
        }
        //System.out.println(Arrays.toString(numArr));
        if (numArr[0] == 1) {
            for (int i = 1; i < 10; i++) {
                if (numArr[i] == 0) {
                    map[point.x][point.y] = i;
                }
            }
        }
        //printMap(map);
    }

    static void printMap(int[][] arr) {
        IntStream.range(0, 9)
                .forEach(e -> System.out.println(Arrays.toString(arr[e])));
        System.out.println("-----");
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
