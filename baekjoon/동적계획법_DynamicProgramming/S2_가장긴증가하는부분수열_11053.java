package 동적계획법_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_가장긴증가하는부분수열_11053 {
    //문제: 실버2 가장 긴 증가하는 부분 수열
    //요약:
    //수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열 구하기
    //{10,20,10,30,20,50} -> {10,20,30,50}

    //풀이1 (main2) LIS - dp
    //늘 dp로 풀려고 할 때마다, dp 배열의 의미를 잡고 넘어가야 잘 풀렸다.
    //"내가 정의한 dp배열"은 주어진 값보다 작은 숫자의 개수라고 생각하면 된다.
    //예를 들어 백준처럼 주어진 배열이 nums {10, 20, 10, 30, 20, 50} 일때
    //m=0일때, dp[m]의 의미는 nums[m]=10보다 작은 숫자의 개수이다.

    //첫번째 for문(n)은 수열의 모든 인덱스를 검사한다.
    //두번째 for문(m)은 매번 0부터 n보다 작은 인덱스들을 검사한다. m = 0 ~ (n-1)
    //nums[n]의 숫자보다 작은 숫자일때마다 dp[n]=dp[m] +1 한다.
    //그전에 memorization 해둔 작은 숫자의 개수를 참고해서 갱신하는 것이다.
    //dp[m]은 nums[m] 보다 작은 숫자이기 때문에, nums[m]은 카운트에 포함되지 않는다.
    //그런데 dp[n]을 구하는 거니까 nums[n]보다 작은 nums[m]도 카운트해서 +1이 되는 것이다.
    //결과적으로 dp[n]=dp[m]+1 이 성립된다.

    //여기서 30보다 작은 숫자가 중복일 때는 의문이 들 수 있다.
    //배열이 nums {10, 20, 10, 30, 20, 50} 일때 30보다 작은 숫자가 10, 20, 10으로 3개니까 dp[3]이 3가 되는 것 아니냐?

    //n=3일때 nums[3]=30이고, for(m)에 따라 각각 (10, 30), (20, 30), (10, 30)을 순차적으로 비교할 것이다.
    //m=0 일때 (10<30) 으로 dp[3]=dp[0]+1=1이 된다.
    //m=1 일때 (20<30) 으로 dp[3]=dp[1]+1=2이 된다.
    //m=2 일때 (10<30) 으로 dp[3]=dp[2]+1=1이 된다. 하지만, Math.max(dp[m] + 1, dp[n])으로 그 전에 저장된 2와 비교해서 최대값으로 갱신 되기 때문에 dp[3]=2로 정해진다.

    //마지막으로 출력값은 수열의 길이니까 +1을 해서 뽑는다.
    //{10, 20, 30} 3개 + {50} 1개 이런느낌이다.

    //풀이2 (main) LIS - binary search
    //dp로는 확 와닿던 풀이가, binary search를 적용하니까 난해했었다.
    //더해서, Arrays.binarySearch(int[] a, int fromIndex, int toIndex, int key)에 대한 공부도 필요했다는 점이 시간을 더 쓰게 했다.

    //Arrays.binarySearch는
    //키를 찾으면 해당 키가 있는 인덱스를 반환한다.
    //키를 못찾으면 -insertion point -1값을 반환한다. (insertion point : key보다 greater한 최초의 위치)

    //(예시) 배열 { 5 5 5 5 5 }
    //   1. 10를 찾고자 한다. 10은 배열에 없다.
    //   2. 10보다 큰 최초의 위치를 찾는다.
    //      모든 값이 10보다 작기 때문에 계속 탐색 위치가 오른쪽으로 갈 것이다
    //      (low값이 계속 증가한다. low=mid+1)
    //   3. 5-5(low) -1 = -6을 반환한다.
    // 왜?
    // sorting 상태가 유지되어야 하므로, 유지되는 위치를 반환해준다고 생각하면 된다.

    //binary search방법은 arr배열의 완성상태를 봐야한다.
    //주어진 배열이 nums {10, 20, 10, 30, 20, 50} 이라고 가정한다.
    //i=0일때, 10을 arr에서 찾게 될 것이다.
    //arr { 0 0 0 0 0 0 }인 상태이므로 Arrays.binarySearch()의 반환값은 -7일 것이다.

    //하지만 여기서 length=0이라는 숫자가 주어지게 된다. ⚡⚡⚡ ⚡
    //int index = Arrays.binarySearch(arr, 0, length, nums[i]);
    //from(0) ~ to(0) 으로 low는 0이므로 -0-1=-1을 반환하게 된다.
    //그리고 index가 음수(찾을 수 없으니까)라서 조건문에 따라 index= -(-1+1) = 0으로 바뀌게 된다.
    //결과적으로 arr[0] = 10이 들어가게 되서 arr {10 0 0 0 0 0}이 완성된다.

    //그리고 index(0) == length(0) 이어서 length은 1을 증가하고 다음 사이클로 진행하게 된다.

    //이런식으로 계속 for문이 진행되면서,
    //arr은 순차적으로 {10 0 0 0 0 0} ➡ {10 20 0 0 0 0} ➡ {10 20 30 0 0 0} ➡ {10 20 30 50 0 0}으로 채워지게 된다.

    //즉, length값으로 arr의 입력위치를 제어한다.
    //arr에 이미 있는 값일 경우 양수로 인덱스가 반환되서 똑같은 위치에 똑같은 숫자를 넣게 된다.

    //마지막으로 출력값은 length이다.
    //arr 배열의 완성상태가 증가하는 부분 수열 자체이기 때문에, 입력된 length 까지가 길이가 된다.
    //arr {10 20 30 50 0 0}, length=4 (index=3까지 입력되고, length는 증가되서 4인 상태)


    //dp방법 풀이 - main2
    public static void main2(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] dp = new int[N];
        int length = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < n; m++) {
                if (nums[m] < nums[n]) dp[n] = Math.max(dp[m] + 1, dp[n]);
            }
            if (length < dp[n]) length = dp[n];
        }
        System.out.println(length + 1);
    }

    //이분탐색방법 풀이 - main
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        //System.out.println(Arrays.toString(nums));
        int[] arr = new int[N];
        int length = 0;

        int[] test=new int[]{0,0,0,0,0,0};
        int length2=6;
        int index3=Arrays.binarySearch(test, 0, length2, 10);
        System.out.println("테스트:"+index3);

        for (int i = 0; i < N; i++) {
            //System.out.println("-----nums[i]:"+nums[i]+", length:"+length);
            int index = Arrays.binarySearch(arr, 0, length, nums[i]);
            //System.out.println("index:"+index);
            if (index < 0) {
                index = -(index + 1); // 이분탐색에서 찾지 못한 경우의 삽입 위치 계산
            }
            //이분탐색에서 찾았으면 찾은 인덱스에 똑같은 값을 넣는 것이다.
            arr[index] = nums[i];

            if (index == length) {
                length++;
            }
            //System.out.println(Arrays.toString(dp));
        }
        System.out.println(length);
    }
}
