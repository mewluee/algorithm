
import java.io.*;
import java.util.*;

public class Main {


    static class Tree{
        Tree left;
        String value;
        Tree right;

        public Tree(Tree left, String value, Tree right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }


    public static void main(String[] args) throws IOException {
       /* //트리사용하래.
        //Tree는 자식노드의 개수가 n개 가능
        //이진트리는 자식노드의 개수가 2개 고정
        //이진트리 사용해야지안흥ㄹ까..?
        //트리를 어떻게 사용해야할까?
        //탐색?
        //원래 모든 문자열을 하나씩 비교했는데
        //그 과정을 줄이기 위해서..?
        //정렬해서 저장해도 똑같다고 볼수있을까?
        //552456 > 245556
        //245
        //정렬하면안댈거같은뎅.
        //트리에 문자열 자체를 저장하기.
        //역시 이진트리가..답인거같은데...........

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));


        int test_case=Integer.parseInt(br.readLine());

        //알고리즘
        //트리에 넣기 전에
        //트리를 먼저 탐색하는거지.
        //탐색하고나서. 찾는값이 있으면 no출력
        //찾는값이 없으면 yes출력 > 그리고 트리에 집어넣기.
        //안됌..분리해야함..


        for(int i=0; i<test_case; i++){

            boolean isConsistency=true; //일관성있다는건 중복이 없다는 거
            int N=Integer.parseInt(br.readLine());
            String[] phonebook=new String[N];

            for(int n=0; n<N; n++){
                String str=br.readLine(); //한줄 받고
                phonebook[n]=str;
            }

            //System.out.println("전:"+Arrays.toString(phonebook));
            //길이가 짧은 것부터 앞으로 정렬하자.
            Arrays.sort(phonebook, Comparator.comparingInt(String::length));
            //-1 오름차순
            //System.out.println("후:"+Arrays.toString(phonebook));

            Tree root=new Tree(null, "",null);

            for(int n=0; n<N; n++){
                //System.out.println(">>n:"+n);
                String str=phonebook[n]; //정렬된 배열에서 하나씩 가져와서

                if(n==0){ //제일 첫번째 값은 루트로 만들어주기~
                    root.left=new Tree(null, "", null);
                    root.value=str;
                    root.right=new Tree(null, "",null);

                }else{ //제일 첫번째 값이 아니면 트리를 탐색하라! // 트리탐색은 재귀인가욤?
                    for(int m=0; m<str.length(); m++){
                        String subStr=str.substring(0,m+1);
                        if(searchTree(root, subStr)==1){
                            isConsistency=false;
                            break;
                        }
                    }
                    inputTree(root,str);
                }

            }

            if(isConsistency) {
                bw.write("YES\n");
                //System.out.println("YES");
            }else{
                bw.write("NO\n");
                //System.out.println("NO");
            }



        }
        bw.close();*/



        //문자열 잘라내기 2866
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

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

            //colStrs 만드러!
            String[] colStrs=new String[C];
            for(int c=0; c<C; c++){
                colStrs[c]="";
                for(int r=0; r<R; r++){
                    colStrs[c]=colStrs[c]+rowStrs[r].charAt(c);
                }
            }

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


    public static int searchTree(Tree root, String str){
        String rootStr=root.value;
        //System.out.println("[함수진입] root.value="+rootStr+" , 찾는값:"+str);

        if(rootStr.length()==0){ //여기까지왔는데 비어있는 노드라면
            return 0; //찾는게없어~
        }else{
            //System.out.println("에...");
            int num=rootStr.compareTo(str);
            if(num==0){ //찾음
                //System.out.println(">>root.value="+rootStr+" , 찾는값:"+str);
                return 1; //
            }else if(num<0){ //str값이 더 큰거니까 right노드로가야함.
                if(root.right!=null){
                    //System.out.println("right이동");
                    searchTree(root.right, str);
                }

            }else{
                if(root.left!=null){
                    //System.out.println("left이동");
                    searchTree(root.left, str);

                }
            }

        }

        return 0;
    }

    public static int inputTree(Tree root, String str){
        String rootStr=root.value;

        //System.out.println("[함수진입2] root.value="+rootStr+" , 찾는값:"+str);

        if(rootStr.length()==0){ //여기까지왔는데 비어있는 노드라면
            root.value=str; //여기에다가 값 저장하고.
            root.left=new Tree(null, "", null);
            root.right=new Tree(null, "", null);
            return 1; //노드입력함
        }else{
            //System.out.println("에...2");
            int num=rootStr.compareTo(str);
            if(num==0){ //찾음
                return 0; //
            }else if(num<0){ //str값이 더 큰거니까 right노드로가야함.
                if(root.right!=null){
                    //System.out.println("right이동");
                    inputTree(root.right, str);
                }

            }else{
                if(root.left!=null) {
                    //System.out.println("left이동");
                    inputTree(root.left, str);
                }
            }

        }

        return 0;

    }




}