package basic;

import implement.StandInLine1138;

import java.util.Arrays;

public class Permutation {

    public static void main(String args[]) {
        basic();
    }

    // 기본 순열(중복x) 3자리, 5개의 카드 > 5x4x3 = 60개
    public static void basic() {
        int[] arrs = new int[]{1, 2, 3, 4, 5};
        int[] anws = new int[3];

        int count=0;

        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs.length; j++) {
                for (int k = 0; k < arrs.length; k++) {
                    if (i == j || j == k || k == i) continue;
                    anws[0] = arrs[i];
                    anws[1] = arrs[j];
                    anws[2] = arrs[k];
                    count++;
                    System.out.println(Arrays.toString(anws));
                }
            }
        }

        System.out.println(count);

    }
}
