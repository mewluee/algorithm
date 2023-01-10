package dfsExample1987;

import java.io.*;
import java.util.*;

public class DfsExample1987 {

    class Country{
        private String name;
        private int[] myAddress;
        private ArrayList<int[]> neighbors;

        public Country(String name, int[] myAddress, int R, int C) {
            this.name = name;
            this.myAddress=myAddress;
            this.neighbors=new ArrayList<>();
            setNeighbor(R,C);
        }

        private void setNeighbor(int R, int C){
            //위로 이동 (행-1) (열)
            if(0<= myAddress[0]-1 && myAddress[0]-1 <R )
                this.neighbors.add( new int[]{myAddress[0]-1,myAddress[1]} );
            //아래로 이동 (행+1) (열)
            if(0<= myAddress[0]+1 && myAddress[0]+1 <R )
                this.neighbors.add( new int[]{myAddress[0]+1,myAddress[1]} );
            //왼쪽으로 이동 (행) (열-1)
            if(0<= myAddress[1]-1 && myAddress[1]-1 <C)
                this.neighbors.add( new int[]{myAddress[0],myAddress[1]-1} );
            //오른쪽으로 이동 (행) (열+1)
            if(0<= myAddress[1]+1 && myAddress[1]+1 <C)
                this.neighbors.add( new int[]{myAddress[0],myAddress[1]+1} );

        }

        public String getName(){
            return name;
        }

        public int[] getNeighbor(int index){
            return neighbors.get(index);
        }

        public ArrayList<int[]> getNeighbors(){
            return neighbors;
        }

        public String toString(){
            String result;
            result=String.format("나라이름: %s 이웃들 수:%s \n",name,neighbors.size());
            for(int[] one:neighbors){
                result+="["+one[0]+","+one[1]+"]\n";
            }

            return result;
        }

        public int[] getMyAddress(){
            return myAddress;
        }
    }

    public void result() throws IOException {

        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

        int T=Integer.parseInt(bf.readLine());

        for(int test_case=1; test_case<=T; test_case++){

            String[] arrStr=bf.readLine().split(" ");
            int R = Integer.parseInt(arrStr[0]); //행
            int C = Integer.parseInt(arrStr[1]); //열

            // list와 array중 선택 > 중간에 값을 더하거나 빼지 않으니까 접근속도가 더 빠른 array선택
            //String[] map=new String[R]; // 행의 개수 입력
            // 열은 따로 만들지 X >> String.charAt으로 접근가능
            // [ CAAB , ADCB , ... ]

            Country[][] map=new Country[R][C];

            //행의 개수만큼 readLine으로 입력받음
            for(int r=0; r<R; r++){
                String mapStr=bf.readLine();
                for(int c=0; c<C; c++){
                    //System.out.println("r:"+r+" ,c:"+c+"------");
                    map[r][c]=new Country(String.valueOf(mapStr.charAt(c)),new int[]{r,c},R,C);
                    //System.out.println(map[r][c].toString());
                    //System.out.println("-----------------");
                }
            }




            //HashSet<String> tripCountry=new HashSet<String>();// 알파벳 담아서 확인하기 > 정답 개수
            // 종료 조건 > 갈수있는 노드들의 알파벳이 result에 중복될 때. > contains함수쓰기 or add함수쓰면 중복일때 false가 반환
            // 연결 노드 저장 데이터 타입은..뭘 써야 할까..음..
            // 사용자 지정 변수 class Country
            // 멤버 필드1 : String name 국가이름
            // 멤버 필드2 : int[][] neighbor 인접국가 노드 위치
            // >> Arraylist로 바꿈



            //DFS구현하기
            //1.시작점을 기준으로 연결된 노드 확인
            //2.일단 한 노드로 이동하고 연결된 노드 확인(이미 간곳은 X)
            // >> 지나온 노드라면 안가고
            // >> 안 간 노드면 간다
            //3.새로운 노드가없으면 스택에서 하나씩 꺼내서 뒤로 간다. >> 백트래킹
            //4.이동조건 > 위(행-1) 아래(행+1) 왼쪽(열-1) 오른쪽(열+1)
            //5.스택에 넣어야하는 건 중복값이 아니어야하니까 백준 문제기준으로 abcd..알파벳.
            //6.흐름
            // 일단 이동했을때의 값이 스택에 있는지 없는지 확인

            //수도코드는 위처럼 짜놓고 실제 코드는 이상하게 짬;
            //문제점: 백트래킹 안함.
            //일단 입에 넣어보고 아니면 뱉어야한다. push / pop
            //내가 한 코드는

            int r=0,c=0;
            int max=0;

            ArrayList<int[]> neighbors=map[r][c].getNeighbors(); // 이웃들 데이터 가져오기

            for(int i=0; i<neighbors.size(); i++){
                Stack<int[]> trip=new Stack<>();
                HashSet<String> tripCountry=new HashSet<String>();

                trip.push(map[r][c].getMyAddress()); // 시작 위치 push (stack)
                tripCountry.add(map[r][c].getName()); // 시작 나라 이름 add (hashset)

                /*System.out.print(">>>trip:");
                System.out.println(">>>tripCountry:"+tripCountry.toString());*/

                findRoute(neighbors.get(i)[0],neighbors.get(i)[1],trip,tripCountry,map);

                if(max<tripCountry.size()){
                    max=tripCountry.size();
                }
                System.out.println(max);
                System.out.println(">>>tripCountry:"+tripCountry.toString());
                for(int[] one:trip){
                    System.out.println(map[one[0]][one[1]].getName());
                }

            }

            System.out.println("#"+test_case+" "+max);


        }



    }

    private void findRoute(int r, int c,Stack<int[]> trip, HashSet<String> tripCountry,Country[][] map){


            //System.out.println("**메서드 진입함 r:"+r+", c:"+c);
            // 좌표 r,c를 외부에서 넣어줌
            //System.out.println("**trip[0]:"+trip.peek()[0]+", trip[1]:"+trip.peek()[1]);
            if(trip.peek()[0]!=r || trip.peek()[1]!=c){ // stack에서 꺼낸 최근 int[][]값 != 이웃의 int[][] 값 >> 바로 전 여행지가 아닐때
                //System.out.println("***조건(1)");
                if(!tripCountry.contains(map[r][c].getName())){ // hashset에 여행한 나라가 아닐 경우~
                    //System.out.print("***조건(2)");
                    //System.out.println(">>>country:"+map[r][c].getName());
                    trip.push(map[r][c].getMyAddress());
                    tripCountry.add(map[r][c].getName());

                    ArrayList<int[]> neighbors=map[r][c].getNeighbors(); // 추가했다면 좌표 r,c 이웃들 데이터 가져오기

                    for(int i=0; i<neighbors.size(); i++){
                        findRoute(neighbors.get(i)[0],neighbors.get(i)[1],trip,tripCountry,map);
                    }


                }
            }


    }
}
