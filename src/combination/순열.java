package combination;

import java.util.Arrays;

public class 순열 {

    public static void main(String[] args) {
        int[] arrs = new int[]{1, 2, 3, 4, 5};

        int[] anws = new int[3];
        int count = 0;

        for (int i = 0; i < arrs.length; i++) {
            anws[0] = arrs[i];
            for (int j = 0; j < arrs.length; j++) {
                if (i == j) continue;
                anws[1] = arrs[j];
                for (int k = 0; k < arrs.length; k++) {
                    if (j == k || i == k) continue;
                    anws[2] = arrs[k];
                    count++;
                    System.out.println(Arrays.toString(anws));
                }
            }
        }
        System.out.println(count);
    }

    /* 출력
        [1, 2, 3] [1, 2, 4] [1, 2, 5]
        [1, 3, 2] [1, 3, 4] [1, 3, 5]
        [1, 4, 2] [1, 4, 3] [1, 4, 5]
        [1, 5, 2] [1, 5, 3] [1, 5, 4]
        [2, 1, 3] [2, 1, 4] [2, 1, 5]
        [2, 3, 1] [2, 3, 4] [2, 3, 5]
        [2, 4, 1] [2, 4, 3] [2, 4, 5]
        [2, 5, 1] [2, 5, 3] [2, 5, 4]
        [3, 1, 2] [3, 1, 4] [3, 1, 5]
        [3, 2, 1] [3, 2, 4] [3, 2, 5]
        [3, 4, 1] [3, 4, 2] [3, 4, 5]
        [3, 5, 1] [3, 5, 2] [3, 5, 4]
        [4, 1, 2] [4, 1, 3] [4, 1, 5]
        [4, 2, 1] [4, 2, 3] [4, 2, 5]
        [4, 3, 1] [4, 3, 2] [4, 3, 5]
        [4, 5, 1] [4, 5, 2] [4, 5, 3]
        [5, 1, 2] [5, 1, 3] [5, 1, 4]
        [5, 2, 1] [5, 2, 3] [5, 2, 4]
        [5, 3, 1] [5, 3, 2] [5, 3, 4]
        [5, 4, 1] [5, 4, 2] [5, 4, 3]
        총 개수 = 60
     */

}
