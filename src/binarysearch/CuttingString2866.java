package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CuttingString2866 {

    private String site="https://www.acmicpc.net/problem/2866";

    // 이건 해쉬셋 씀 -> 그래도시간초과
    public void result() throws IOException {


        //문자열 잘라내기 2866
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine()," ");

        int R=Integer.parseInt(stringTokenizer.nextToken()); //행의 개수
        int C=Integer.parseInt(stringTokenizer.nextToken()); //열의 개수

        String[] rowStrs=new String[R];
        int count=0;

        for(int r=0; r<R; r++){
            rowStrs[r]=br.readLine();
        }

        while(rowStrs.length>0){

            //System.out.println(">>rowStrs1:"+Arrays.toString(rowStrs));

            //첫줄없애!
            rowStrs= Arrays.copyOfRange(rowStrs,1,rowStrs.length);
            R--;
            HashSet<String> hashSet=new HashSet<String>();

            //colStrs 만드러!
            String[] colStrs=new String[C];
            for(int c=0; c<C; c++){
                colStrs[c]="";
                for(int r=0; r<R; r++){
                    colStrs[c]=colStrs[c]+rowStrs[r].charAt(c);
                }
                //System.out.println("colStrs:"+colStrs[c]);
                hashSet.add(colStrs[c]);

            }
            if(hashSet.size()!=colStrs.length){
                System.out.println(count);
                return;
            }else{
                count++;
            }

           /* //System.out.println("corStrs:"+Arrays.toString(colStrs));

            //밑에 메서드 호출함.
            if(checkDoubleStrings(colStrs)){ //중복이면 count출력하고 멈춘다.
                System.out.println(count);
                return;
            }else{
                count++;
                //System.out.println(">>rowStrs2:"+Arrays.toString(rowStrs));

            }*/

        }

        System.out.println(count);

        //모냐...해시를 사용한 집합과 맵..???으으으ㅜㅇㅇ...으웅ㅇ...
        //이거 모르게꼬..
    }

    // 이분탐색 씀 -> 시간초과
    public void result2() throws IOException {


        //문자열 잘라내기 2866
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine()," ");

        int R=Integer.parseInt(stringTokenizer.nextToken()); //행의 개수
        int C=Integer.parseInt(stringTokenizer.nextToken()); //열의 개수

        String[] rowStrs=new String[R];
        int count=0;

        for(int r=0; r<R; r++){
            rowStrs[r]=br.readLine();
        }

        while(rowStrs.length>0){

            //System.out.println(">>rowStrs1:"+Arrays.toString(rowStrs));

            //첫줄없애!
            rowStrs=Arrays.copyOfRange(rowStrs,1,rowStrs.length);
            R--;
            HashSet<String> hashSet=new HashSet<String>();

            //colStrs 만드러!
            String[] colStrs=new String[C];
            for(int c=0; c<C; c++){
                colStrs[c]="";
                for(int r=0; r<R; r++){
                    colStrs[c]=colStrs[c]+rowStrs[r].charAt(c);
                }
                //System.out.println("colStrs:"+colStrs[c]);
                //hashSet.add(colStrs[c]);

            }
            /*if(hashSet.size()!=colStrs.length){
                System.out.println(count);
                return;
            }else{
                count++;
            }*/

            //System.out.println("corStrs:"+Arrays.toString(colStrs));

            //밑에 메서드 호출함.
            if(checkDoubleStrings(colStrs)){ //중복이면 count출력하고 멈춘다.
                System.out.println(count);
                return;
            }else{
                count++;
                //System.out.println(">>rowStrs2:"+Arrays.toString(rowStrs));

            }

        }

        System.out.println(count);

        //모냐...해시를 사용한 집합과 맵..???으으으ㅜㅇㅇ...으웅ㅇ...
        //이거 모르게꼬..
    }


    //중복하면 true반환
    public static boolean checkDoubleStrings(String[] strs){

        Arrays.sort(strs);

        for(int i=0; i<strs.length; i++){
            //이분탐색 구현

            int min=0;
            int max=strs.length;
            while(min<max){
                int mid=(min+max)/2;

                //i가 찾는 값 mid가 구한값
                if(strs[i].compareTo(strs[mid])>0){ //i-mid니까 양수면 i가 더 큰거임. mid값을 올려야함. min증가!
                    min=mid+1;
                }else if(i!=mid && strs[i].equals(strs[mid])){
                    return true;
                }else{
                    max=mid-1;
                }
            }
        }

       /* for(int n=0; n<strs.length-1; n++){
            for(int m=n+1; m<strs.length; m++){
                if(strs[n].equals(strs[m])) {
                    //System.out.println("n, m: "+strs[n]+", "+strs[m]);
                    return true;
                }
            }
        }*/

        return false;
    }

    //rotation해보기. >> 로테이션 하고 hashset으로 중복검사하니까 맞음..후..인생..
    public void result3() throws IOException {
        //문자열 잘라내기 2866
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine()," ");

        int R=Integer.parseInt(stringTokenizer.nextToken()); //행의 개수
        int C=Integer.parseInt(stringTokenizer.nextToken()); //열의 개수

        int count=0;
        /*char[][] rotation90Strs=new char[C][R];

        for(int r=0; r<R; r++){
            String one= br.readLine();
            for(int c=C-1; c>=0; c--){
                rotation90Strs[c][r]=one.charAt(c);
            }
        }

        System.out.println(Arrays.deepToString(rotation90Strs));*/

        String[] strs=new String[C];
        Arrays.fill(strs,"");
        for(int r=0; r<R; r++){
            String one= br.readLine();
            for(int c=C-1; c>=0; c--){
                strs[c]=strs[c]+one.charAt(c);
            }
        }

        while(strs[0].length()>1){


            cuttingString(strs);

            if(checkString2(strs)){
                System.out.println(count);
                return;
            }else{
                count++;
            }


        }

        System.out.println(count);

    }

    public boolean checkString2(String[] strs){
        HashSet<String> hashSet=new HashSet<>();

        for(int i=0; i<strs.length; i++){
            hashSet.add(strs[i]);
        }

        //System.out.println("size , length "+hashSet.size()+" "+strs.length );

        if(hashSet.size()==strs.length){
            return false;
        }else{
            return true;
        }
    }

    public void cuttingString(String[] strs){
        for(int i=0; i<strs.length; i++){
            char[] chars=Arrays.copyOfRange(strs[i].toCharArray(),1,strs[i].length());
            strs[i]=new String(chars);
        }
    }

}
