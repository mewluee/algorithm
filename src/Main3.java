import java.lang.reflect.Array;
import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        solution(6,6,new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
    }

    static int[][] base;

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};

        base = new int[rows][columns];

        for (int r = 0; r < rows; r++) {
            int[] arrs=new int[columns];
            for (int c = 0; c < columns; c++) {
                arrs[c]=(r*columns)+c+1;
            }
            base[r]=arrs;
        }

        System.out.println(Arrays.deepToString(base));

        for (int i = 0; i < queries.length; i++) {
            move(queries[i]);
        }

        return answer;
    }

    public static void move(int[] qr){

    }

    public static void leftToRight(int X, int[] qr){
        // 8 9 10 →
        // X 8 9 // 10
    }

    public static void upToDown(int X, int[] qr){
        // 10 16 22 28 ↓
        // X 10 16 22 // 28
    }

    public static void rightToLeft(int X, int[] qr){
        // 26 27 28 ←
        // 27 28 X // 26


    }

    public static void downToUp(int X, int[] qr){
        // 8 14 20 26 ↑
        // 14 20 26  //
    }
}
