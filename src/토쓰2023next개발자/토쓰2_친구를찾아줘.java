package 토쓰2023next개발자;

public class 토쓰2_친구를찾아줘 {

    public static void main(String[] args) {

        int[][] relationships={
                {1,2},{2,3},{2,6},{3,4},{4,5}
        };
        int answer=solution(relationships, 2,3);
        System.out.println(answer);
    }

    static boolean[][] map;
    static int result;
    static int newfriend;

    static int solution(int[][] relationships, int target, int limit) {
        result = 0;
        newfriend=0;
        map=new boolean[101][101];
        boolean[] visited=new boolean[101];
        for (int i = 0; i < relationships.length; i++) {
            int[] one=relationships[i];
            map[one[0]][one[1]]=true;
            map[one[1]][one[0]]=true;
            //양방향
        }
        dfs(visited, limit, 0, target);
        return result+newfriend;
    }

    static void dfs(boolean[] visited, int limit, int count, int number){
        //System.out.println("num:"+number+",count:"+count);
        visited[number]=true;
        if(count>limit){
            return;
        }
        if(count==1){
            result+=5;
        }
        if(count>1){
            result+=10;
            newfriend++;
        }
        for(int i=1; i<=100; i++){
            if(!visited[i] && map[number][i]){
                dfs(visited, limit, count+1, i);
            }
        }

    }

}
