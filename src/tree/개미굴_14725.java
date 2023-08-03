package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class 개미굴_14725 {

    /*
    트리구조 HashMap으로 해보기
    static class Floor {
        Map<String, Floor> room = new HashMap<>();
    }
     */

    static int C;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        Node root=new Node("ROOT", new ArrayList<>());

        for (int c = 0; c < C; c++) {
            String[] input = br.readLine().split(" ");
            int T = Integer.parseInt(input[0]);
            Node node=root;
            for (int t = 1; t <= T; t++) {
                Node child=new Node(input[t], new ArrayList<>());
                int index=node.child.indexOf(child);
                if(index<0){
                    node.child.add(child);
                }else{
                    child=node.child.get(index);
                }
                node=child;
            }
        }

        //System.out.println(dfs(root));
        dfs2(root, 0);
        System.out.println(sb.toString());
    }

    static void dfs2(Node now, int depth){
        if(!now.me.equals("ROOT")){
            sb.append("--".repeat(depth-1));
            sb.append(now.me+"\n");
        }
        Collections.sort(now.child);
        for (int j = 0; j < now.child.size(); j++) {
            dfs2(now.child.get(j), depth+1);
        }
    }

    static String dfs(Node root){

        Stack<Node> stack=new Stack<>();
        StringBuilder sb=new StringBuilder();

//        Collections.sort(root.child);
//        for (int i = 0; i < root.child.size(); i++) {
//            stack.push(root.child.get(i));
//        }
        stack.push(root);

        while (!stack.isEmpty()) {
            Node now=stack.pop();
            //System.out.print(now.me+"\n");
            //sb.append(now.me+"\n");
            if(now.me.equals("ROOT")){
                //sb.append(now.me+"\n");
            }else{
                sb.append("--");
                sb.append(now.me+"\n");
            }

            Collections.sort(now.child);
            for (int j = 0; j < now.child.size(); j++) {
                stack.push(now.child.get(j));
                System.out.print("--");
                sb.append("--");
            }
        }

        return sb.toString();
    }

    static class Node implements Comparable<Node> {
        String me;
        ArrayList<Node> child;

        public Node(String me, ArrayList<Node> child) {
            this.me = me;
            this.child = child;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            개미굴_14725.Node node = (개미굴_14725.Node) obj;
            return me.equals(node.me);
        }


        @Override
        public int compareTo(Node o) {
            return me.compareTo(o.me);
        }
    }
}
