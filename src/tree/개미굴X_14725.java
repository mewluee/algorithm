package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 개미굴X_14725 {
    static int C;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        list = new ArrayList[16]; //0 제외 1~15 입력
        for (int i = 0; i < 16; i++) {
            list[i]=new ArrayList<>();
        }

        for (int c = 0; c < C; c++) {
            String[] input = br.readLine().split(" ");
            int T = Integer.parseInt(input[0]);
            for (int t = 1; t <= T; t++) {
                if (t == 1) {
                    String parent = "root";
                    String me = input[t];
                    Node node=new Node(parent, me);
                    if(list[t].contains(node)) continue;
                    list[t].add(node);
                }else{
                    String parent=input[t-1];
                    String me=input[t];
                    Node node=new Node(parent, me);
                    if(list[t].contains(node)) continue;
                    list[t].add(node);
                }
            }
        }

        for (int k = 0; k <16; k++) {
            Collections.sort(list[k]);
        }

        dfs(1, "root");
        System.out.println("gp");
    }

    static void dfs(int depth, String parent){

        for (int i = 0; i < list[depth].size(); i++) {
            System.out.println("depth:"+depth);
            Node node=list[depth].get(i);
            if (parent.equals(node.parent)) {
                System.out.println(node.me);
                dfs(depth+1, node.me);
            }
        }
    }

    static class Node implements Comparable<Node> {
        String parent;
        String me;

        public Node(String parent, String me) {
            this.parent = parent;
            this.me = me;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            //if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return parent.equals(node.parent) && me.equals(node.me);
        }

        @Override
        public int compareTo(Node otherNode) {
            if(me.compareTo(otherNode.me)==0)
                return parent.compareTo(otherNode.parent);
            else
                return me.compareTo(otherNode.me);
        }
    }
}
