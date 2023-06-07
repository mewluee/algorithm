package permutation;

import java.util.Scanner;
import java.util.stream.IntStream;

public class 백트래킹조합 {

    static boolean[] visited;
    static int n;
    static int[] arrs = new int[]{1, 2, 3, 4, 5};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size=scanner.nextInt();

        n=arrs.length;
        visited=new boolean[n];

        combination(size,0);

    }

    public static void combination(int size, int start) {
        if(size==0){
            print();
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i]=true;
            combination(size-1,i+1);
            visited[i]=false;
        }
    }

    public static void print() {
        IntStream.range(0,n)
                .filter(e->visited[e])
                .forEach(e->System.out.print(arrs[e]+" "));
        System.out.println();
    }
}
