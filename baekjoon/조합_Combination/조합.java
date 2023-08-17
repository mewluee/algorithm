package 조합_Combination;

import java.util.Arrays;

public class 조합 {

    public static void main(String[] args) {
        int[] arrs = new int[]{1, 2, 3, 4, 5};
        int[] anws = new int[3];

        for (int i = 0; i < arrs.length; i++) {
            anws[0] = arrs[i];
            for (int j = i+1; j < arrs.length; j++) {
                anws[1] = arrs[j];
                for (int k = j+1; k < arrs.length; k++) {
                    anws[2] = arrs[k];
                    System.out.println(Arrays.toString(anws));
                }
            }
        }
    }

    /* 출력
        [1, 2, 3]
        [1, 2, 4]
        [1, 2, 5]
        [1, 3, 4]
        [1, 3, 5]
        [1, 4, 5]
        [2, 3, 4]
        [2, 3, 5]
        [2, 4, 5]
        [3, 4, 5]
        총 개수 = 10
    */
}
