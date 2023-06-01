package hashset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class NumberCard10815 {


    public static void main(String[] args) throws IOException{


        anotherSolution();

    }

    public static void hashSetSolution() throws IOException {
        HashSet<Integer> hashSet = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Arrays.stream(br.readLine().split(" "))
                .forEach(e->hashSet.add(Integer.parseInt(e)));

        int M = Integer.parseInt(br.readLine());

        Arrays.stream(br.readLine().split(" "))
                .mapToInt(e->hashSet.contains(Integer.parseInt(e))?1:0)
                .forEach(e->System.out.print(e+" "));

    }

    public static void anotherSolution() throws IOException{

        /*
         * 이분탐색?
         * 1. 배열로 숫자카드 입력해두기
         * 2. 입력받은 숫자를 배열로 입력받고 정렬해두기
         * 3. 정렬한 배열에서 숫자카드 하나씩 이분탐색 하기
         * */


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nList = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        int M = Integer.parseInt(br.readLine());

        int[] mList=Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.stream(mList)
                .map(e->dd(nList, e))
                .forEach(e->System.out.print(e+" "));


    }

    public static int dd(int[] mList, int search) {
        int min=0;
        int max=mList.length-1; //-1..........너이자식....

        while (min <= max) {
            int mid = (min + max) / 2;

            if(mList[mid]==search) return 1;
            else if(mList[mid]>search) max=mid-1;
            else min=mid+1;
        }

        return 0;
    }

}
