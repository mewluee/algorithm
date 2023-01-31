package binarysearch;

public class Blogging {

    //기본 이분탐색(while문으로 구현)
    public void result(){

        int[] data={1, 3, 5, 7, 9, 11, 13, 15};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=8; //찾는값
        int result=-1; //int형은 null로 초기화 할수없다(그래서 null인지 비교도 불가능)
        //인덱스값이 저장되므로 초기값을 -1로 지정한다

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            if(N<data[mid]) max=mid-1;
            else if(N==data[mid]) result=mid;
            else min=mid+1;
        }

        if(result==-1){
            System.out.println("값을 찾을 수 없습니다.");
        }else{
            System.out.println(result);
        }

    }

    // lower bound 구현
    public void result2(){
        int[] data={7, 9, 11, 13, 15, 17, 19, 21};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=8; //찾는값

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            //--------------------------------------------------//
            if(N<=data[mid]) max=mid; // (4) = (1) + (2)
            else min=mid+1; // (5) = (3)
        }

        System.out.println(max);
    }
}
