package 트리_Tree;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class 전화번호목록_5052_실패 {

    private String str="https://www.acmicpc.net/problem/5052";

    class Tree{
        Tree left;
        String value;
        Tree right;

        public Tree(Tree left, String value, Tree right) {
            this.left = left;
            this.value = value;
            this.right = right;
        }
    }

    public void result2() throws IOException{
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

    }

    public void result() throws IOException {
        //트리사용하래.
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
            Arrays.sort(phonebook, (o1, o2)->o1.length()<o2.length()? -1 : 1);
            //-1 오름차순
            //System.out.println("후:"+Arrays.toString(phonebook));

            Tree root=new Tree(null, "",null);

            for(int n=0; n<N; n++){
                //System.out.println(">>n:"+n);
                String str=phonebook[n];

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
        bw.close();



    }


    public int searchTree(Tree root, String str){
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

    public int inputTree(Tree root, String str){
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

    public void bad() throws IOException {

        //문제 분석
        //일관성 있다는 건 한 번호가 다른 번호의 접두어인 경우가 아닌 경우.
        //문자열을 비교할때는 제일 긴 문자열을 가지고 비교하면 됌.
        //제일 긴 문자열이 아닌 것들은 해쉬셋에 저장하면될거같음.
        //조건1) 문자열 동일해야함.

        //조금 더 과정을
        //(1) 입력받을때마다 해쉬셋에 검색할까? >그럼 맥스값할필요없음!
        //(2) 아님 맥스값이 중복일때마다 처리하는 걸 따로할까?

        //일단 2번 방법으로 구현해보기. >> X

       /* BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));


        int test_case=Integer.parseInt(br.readLine());


        for(int i=0; i<test_case; i++){

            int maxindex=0;
            int maxlength=0;

            int N=Integer.parseInt(br.readLine());
            ArrayList<Integer> maxIndexsList=new ArrayList<Integer>();

            strArr=new String[N];

            //가장 긴 문자열 지정
            for(int n=0; n<N; n++){
                String str=br.readLine();
                strArr[n]=str;
                if(str.length()>maxlength) {
                    maxlength=str.length();
                    maxIndexsList.clear();
                    maxIndexsList.add(n);
                }else if(str.length()==maxlength){
                    maxlength=str.length();
                    maxIndexsList.add(n);
                }
            }
            System.out.println("긴문자열들 인덱스 확인:"+Arrays.toString(maxIndexsList.toArray()));

            //나머지는 집합에 저장
            hashSet=new HashSet<>(); //멤버필드로 뽑아내서 여기서 새로 생성.
            for(int n=0; n<N; n++){
                for(int m=0; m<maxIndexsList.size(); m++){
                    if(maxIndexsList.get(m)==n){ //긴 문자열들 인덱스 중 하나면 멈춤.
                        break;
                    }else if(m==maxIndexsList.size()-1){ //다 봤는데 아니면 집합에 추가함.
                        hashSet.add(strArr[n]);
                    }
                }
            }

           // System.out.println("가장 긴문자열:"+strArr[maxindex]);
            System.out.println(Arrays.toString(hashSet.toArray()));

            //가장 긴 문자열을 기준으로 해쉬셋에 저장되어있는지 확인.


        }
        bw.close();*/

        // 1번 방법으로 구현해보기 (시간 초과뜰거같은뎅 힝)

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));


        int test_case=Integer.parseInt(br.readLine());

        for(int i=0; i<test_case; i++) {


            boolean isConsistency=true; //일관성있다는건 중복이 없다는 거

            int N = Integer.parseInt(br.readLine());
            HashSet<String> hashSet=new HashSet<String>();

            for (int n = 0; n < N; n++) {
                String str = br.readLine();
                //입력이 들어올때마다 집합에 저장하기.
                //i가 0일때는 집합에 저장만 하기.
                //i가 0이 아닐때는 입력이 들어올때마다 문자열을 substring으로 잘라서 집합에 있는지 검사.
                if (n != 0) {
                    for (int m = 0; m < str.length(); m++) {
                        String subStr = str.substring(0, m + 1);
                        //bw.write("subSTR:"+subStr+"\n");
                        if (hashSet.contains(subStr)) {
                            //bw.write("NO\n");
                            isConsistency=false;
                            break;
                        }
                    }

                }
                hashSet.add(str);

            }
            if(isConsistency) {
                bw.write("YES\n");
            }else{
                bw.write("NO\n");
            }
        }
        bw.close();
    }


}
