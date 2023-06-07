package permutation;

import java.util.Arrays;

public class 중복조합 {


    public static void main(String[] args) {
        int[] arrs = new int[]{1, 2, 3, 4, 5};
        int[] anws = new int[3];
        int count=0;

        for (int i = 0; i < arrs.length; i++) {
            anws[0] = arrs[i];
            for (int j = i; j < arrs.length; j++) {
                anws[1] = arrs[j];
                for (int k = j; k < arrs.length; k++) {
                    anws[2] = arrs[k];
                    count++;
                    System.out.println(Arrays.toString(anws));
                }
            }
        }
        System.out.println(count);
    }

    /* 출력
        [1, 1, 1] [1, 1, 2] [1, 1, 3] [1, 1, 4] [1, 1, 5]
        [1, 2, 2] [1, 2, 3] [1, 2, 4] [1, 2, 5]
        [1, 3, 3] [1, 3, 4] [1, 3, 5]
        [1, 4, 4] [1, 4, 5]
        [1, 5, 5]

        [2, 2, 2] [2, 2, 3] [2, 2, 4] [2, 2, 5]
        [2, 3, 3] [2, 3, 4] [2, 3, 5]
        [2, 4, 4] [2, 4, 5]
        [2, 5, 5]

        [3, 3, 3] [3, 3, 4] [3, 3, 5]
        [3, 4, 4] [3, 4, 5]
        [3, 5, 5]

        [4, 4, 4] [4, 4, 5]
        [4, 5, 5]

        [5, 5, 5]
        총 개수 = 35
    */
}
