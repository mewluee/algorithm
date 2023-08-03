package 토쓰2023next개발자;

public class 토쓰1_멋쟁이팬디지털 {

    public static void main(String[] args) {

        int answer=solution("1451232125",2);
        System.out.println(answer);
    }

    static String result;
    static String inputStr;
    public static int solution(String s, int N) {
        int[] inputArr=new int[N];
        int[] output=new int[N];
        boolean[] visited=new boolean[N];
        inputStr=s;
        result="-1";
        for (int i = 0; i < N; i++) {
            inputArr[i]=i+1;
        }
        //System.out.println(Arrays.toString(inputArr));
        permutation(inputArr, output, visited, 0, N);

        return Integer.parseInt(result);
    }

    public static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int r) {

        if(depth==r){
            String str=makeString(output);
            if(inputStr.contains(str)){
                //System.out.println("str:"+str);
                result=str;
                return;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if(!visited[i]){
                visited[i]=true;
                output[depth]=arr[i];
                permutation(arr, output, visited, depth+1, r);
                visited[i]=false;
            }
        }
    }

    public static void permutation2(int[] arr, int[] output, boolean[] visited, int depth, int r) {

        if(depth==r){
            String str=makeString(output);
            if(inputStr.contains(str)){
                System.out.println("str:"+str);
                result=str;
                return;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if(!visited[r-i-1]){
                visited[r-i-1]=true;
                output[depth]=arr[r-i-1];
                permutation2(arr, output, visited, depth+1, r);
                visited[r-i-1]=false;
            }
        }
    }

    private static String makeString(int[] output){
        StringBuilder sb=new StringBuilder();
        for (int el : output) {
            sb.append(el);
        }
        String result=sb.toString();
        //System.out.println(result);
        return result;
    }

    private static void checkString(String str){
        if(inputStr.contains(str)){
            result=str;
        }
    }
}
