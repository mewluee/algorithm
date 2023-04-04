package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 배열로우선순위큐만들기 {


    public static void main(String[] args) throws IOException {
        result();
    }

    static int[] result=new int[0];

    //따로 클래스 만들 필요는 없는 듯...?
    //값이 제일 작을수록 가치있는 거니까..이것만 제대로 하면..

    public static void result2() throws IOException {
        //우선 순위 큐로 구현하기!
        //우선순위큐가뭔데.........................

        //값에 가치가있다구..흑흑..
        //배열에서 한칸씩 당기면 그대로 들어가나..?으우웅ㅇ..?
        //우선순위 큐...큐..
        //큐는 입력한 순서대로 출력하는데
        //우선순위 큐는 가치가 높은것부터 먼저 출력된다..
        //근데 이걸 배열에..??
        //배열에 순서대로 넣지만 정렬은 하지 않는 다는 건가?
        //하지만 출력할때는 가치값에 따라 출력한다?
        //출력하고 나서 그대로 땡겨도 되나???요!?
        //사실 그냥 입력순서이니까 땡겨도 될거같긴해
        //내가 했던건 인덱스에 따라 트리 만든거니까...음..
        //보긴 보더라도 직접 생각은 한번 해야지...하늘아..! 아 보고싶다!!!!!!!
        //

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                //제거
                System.out.println(delete(input));
                //System.out.println("제거후:"+Arrays.toString(result));
            }else{
                //삽입
                insert(input);
                //System.out.println("삽입후:"+Arrays.toString(result));
            }
        }

    }

    public static void result() throws IOException {
        //힙은 완전이진트리를 기반으로 잡는다.
        //결과적으로 항상 말단(마지막요소)에 들어가고 정렬된다. > 그래서 배열에 저장이 가능하다.
        //삽입 : 배열의 마지막에
        //제거 : 배열의 첫번째 요소
        //최소힙이라서 정렬은 부모노드가 항상 값이 작아야한다.
        //하지만 sort로 할 필요는 없다(sort하면 기본 오름차순인데, 자식노드까지 정렬할 필요는 없기 때문이다)

        //숫자가 입력되면 배열에 삽입하는 거고
        //0이 입력되면 배열에서 제거하는 거다.

        //System.out.println(Arrays.toString(result));
        //System.out.println(getDepth(0)); //매개변수로 입력하는 값이 인덱스이다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                //제거
                System.out.println(delete(input));
                //System.out.println("제거후:"+Arrays.toString(result));
            }else{
                //삽입
                insert(input);
                //System.out.println("삽입후:"+Arrays.toString(result));
            }
        }



    }

    public static void insert(int x){
        //원본 배열 수정 가능!
        //System.out.println("삽입전:"+Arrays.toString(result));

        result = Arrays.copyOf(result, result.length + 1);
        result[result.length-1]=x;
        //System.out.println("삽입후:"+Arrays.toString(result));
        //말단에 넣고 다시 정렬필요
        //정렬은 늘 새로운 값과 부모노드의 값을 비교해서 높으면 자리를 바꾼다.
        //특정 인덱스의 부모노드 값 구하는 공식 찾아야한다.

        //정렬!
        insertSort();
        //System.out.println("정렬후:"+Arrays.toString(result));


    }

    public static int delete(int x){
        if (result.length == 0) {
            return 0;
        }else{
            int ans=result[0]; // 제일 첫번째 인덱스값
            //말단 노드와 제일 첫번째 노드 스위칭
            result[0]=result[result.length-1];
            result[result.length-1]=ans;

            //말단 노드 제거
            result = Arrays.copyOf(result, result.length - 1);

            //다시 정렬필요
            deleteSort();

            return ans;
        }
    }

    public static void insertSort(){
        int depth = getDepth(result.length-1);
        //System.out.println("깊이:"+depth);
        int index= result.length-1;
        int parentIndex;
        for (int d = 0; d < depth-1; d++) {
            //깊이-1만큼 비교해야한다.
            //늘 말단 노드에서 비교 시작
            if ((index) % 2 == 0) {
                //짝수면
                parentIndex=index/2-1;
            }else{
                parentIndex=(index-1)/2;
            }
            if (result[parentIndex] > result[index]) {
                //새로 넣은 말단의 값이 더 작을 경우 스위칭 해줘야한다.
                int temp = result[parentIndex];
                result[parentIndex] = result[index];
                result[index]=temp;
                index=parentIndex;
            }else{
                break;
            }
        }
    }

    public static void deleteSort(){
        //말단노드와 제일 첫번째 노드 스위칭하고 삭제한다.
        //즉 0번 인덱스에는 지금 말단노드값이 있으므로 밑으로 정렬시켜야 한다.

        //밑으로 정렬시킬때는, 자식노드들 중에서 최소값이 위로 올라가야한다.
        //좀 효율적으로 안되낭..?
        //나+자식1+자식2
        //정렬
        //제일왼쪽값
        //위로
        //나 아니면 누군데. 자식1과 자식2 중 비교해서 맞는값이랑 스위칭.
        //이게낫나?
        //아니면 진짜 자식1이랑 비교하고, 자식2랑 비교해서 .. 아ㅓ 위가 낫겟다..

        if(result.length==0) return; //삭제하고 난 트리가 값이 하나도 없을 경우 정렬할 필요가 없다!

        int treeDepth = getDepth(result.length - 1);
        //System.out.println("전체 트리 깊이:"+treeDepth);

        int index= 0;
        Integer childIndexLEFT = null;
        Integer childIndexRIGHT=null;
        //깊이가 4면 3번 비교
        //현재 노드의 깊이가 전체 노드의 깊이보다 작으면 > 그때 자식 노드 검사하기
        int depth = 1;

        while(depth<treeDepth){ //자식 노드가 있다는 뜻이다.
            int[] arrs=new int[1];
            arrs[0]=result[index];
            if (index * 2 + 1 < result.length) {
                childIndexLEFT=index*2+1;
                arrs = Arrays.copyOf(arrs, arrs.length + 1);
                arrs[1]=result[childIndexLEFT];
            }
            if (index * 2 + 2 < result.length) {
                childIndexRIGHT=index*2+2;
                arrs = Arrays.copyOf(arrs, arrs.length + 1);
                arrs[2]=result[childIndexRIGHT];
            }

            Arrays.sort(arrs);

            if(arrs[0]==result[index]) return; //더이상 정렬할 필요가 없다.
            else if (childIndexLEFT!=null && arrs[0] == result[childIndexLEFT]) {
                //스위칭 (최소값이 왼쪽자식에 있다는 거니까)
                int temp=result[childIndexLEFT];
                result[childIndexLEFT] = result[index];
                result[index]=temp;

                index=childIndexLEFT;
            } else if (childIndexRIGHT != null && arrs[0] == result[childIndexRIGHT]) {
                //스위칭 (최소값이 오른쪽자식에 있다는 거니까)
                int temp=result[childIndexRIGHT];
                result[childIndexRIGHT] = result[index];
                result[index]=temp;

                index=childIndexRIGHT;

            }
            depth = getDepth(index);
        }

    }

    //잘못구현했다. 배열의 길이를 기준으로 했다.
    //필요한건 노드..? 하지만 항상 맨 마지막 ....
    //하지만 삽입기준으로 항상 맨 마지막 노드에서 정렬시작한다.
    //또 하지만 삭제 기준으로 항상 루트 노드에서부터 정렬 시작한다.
    //같은 함수로 구현하고 싶었는데 정렬 시작점이 다르네..!
    //음....방법이 없을까..
    //
    public static int getDepth(int n){
        return (int) baseLog(n + 1, 2) + 1; //마지막에 +1해줘야한다.
    }

    public static double baseLog(double x, double base) {
        return Math.log10(x) / Math.log10(base);
    }
}
