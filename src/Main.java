

import binarysearch.CuttingWood2805;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

       /* Scanner s=new Scanner(System.in);
        int n=Integer.parseInt(s.nextLine());

        //n이 짝수일때 홀수일때
        //아니 그..이게 뭔..알고리즘을 써야하당께..? 이게뭐노..
        //행이 2고정이라서 음...
        //열을 2칸씩 잘라서 1개씩일때 나머지
        //눕히는걸 기준으로 잡으면
        //눕히는걸 0개일때 ~
        //눕히는걸 1개일때 ~ 하...순서도 달라도 다른거야..어 이거 순열인가?오옹?!?!?
        //순열로오 어케넣을까아ㅏ아ㅏ아ㅏ음ㅁㅁㅁ..

        if(n%2==0) {//짝수일때
            for(int i=0; i<n/2; i++){ //i가 눕힌거 갯수라고 생각하면됨. 즉 i가 0개일때
                for(int x=0; x<n; x++){

                }
            }
        }else{//홀수일때

        }*/

       /* CuttingWood2805 cw=new CuttingWood2805();
        cw.badCode();*/

        int[] data={1, 3, 5, 7, 9, 11, 13, 15};

        int min=0; //주어진 배열의 최소 인덱스값
        int max=data.length-1; //주어진 배열의 최대 인덱스값
        int N=9; //찾는값

        while(min<max){

            System.out.println("min:"+min+" max:"+max);

            int mid=(min+max)/2;

            System.out.println("찾는값:"+N+" mid:"+mid+" data[mid]:"+data[mid]);
            System.out.println("-".repeat(15));

            //--------------------------------------------------//
            if(N<data[mid]) max=mid;
            else min=mid+1;
        }

        System.out.println(min);


    }




}