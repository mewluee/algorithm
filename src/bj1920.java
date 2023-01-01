import java.util.Arrays;
import java.util.Scanner;

public class bj1920 {

    static int[] arrN;
    static int[] arrX;
    void result(){
        //n에 x값이 있는지 검색. n이 검색풀 x가 검색대상
        Scanner input=new Scanner(System.in);
        int n=input.nextInt(); //n값

        arrN=new int[n]; //n배열
        for(int i=0; i<n; i++){
            arrN[i]=input.nextInt();
        }

        //System.out.println("1)n:"+n+", arrN:"+Arrays.toString(arrN));

        input.nextLine(); //한박자 쉬고~

        int x=input.nextInt(); //x값

        arrX=new int[x]; //x배열
        for(int i=0; i<x; i++){
            arrX[i]=input.nextInt();
        }

        //System.out.println("2)x:"+x+", arrX:"+Arrays.toString(arrX));

        /*int tmp;
        //1. 버블정렬
        for(int k=0; k<arrN.length; k++){
            for(int h=0; h<arrN.length-1-k; h++){
                if(arrN[h]>arrN[h+1]){
                    tmp=arrN[h];
                    arrN[h]=arrN[h+1];
                    arrN[h+1]=tmp;
                }
            }
        }*/
        Arrays.sort(arrN);

        //System.out.println("3) 버블정렬 결과값:"+Arrays.toString(arrN));


        //2. 이분탐색
        // 인덱스값 (처음+끝)/2 한 값을 IF문에 넣고

        for(int b=0; b<arrX.length; b++){

            int result=subtree(0,arrN.length-1, arrX[b]);
            System.out.println(result);
        }


    }

    static int subtree(int start, int end, int find){
        if(start>end) return 0;

        int index=(start+end)/2;
        if(arrN[index]==find){
            return 1;
        }else if(arrN[index]<find){ // 찾는값이 더 크다면~
            return subtree(index+1,end,find);
        }else{ // 찾는 값이 작다면~
            return subtree(start,index-1,find);
        }
    }

}
