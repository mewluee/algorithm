package 해시맵_Hashmap;

import java.io.*;
import java.util.*;

public class 영단어암기는괴로워_20920 {

    //방법1 : Hashmap 으로 중복제거한 후에 Entry list 정렬
    public static void main3(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hashmap = new HashMap();

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            if (input.length() < M) continue; //M이상의 단어만 암기
            hashmap.put(input, hashmap.getOrDefault(input, 0) + 1);
        }

        List<Map.Entry<String, Integer>> values = new ArrayList<>(hashmap.entrySet());
        Comparator<Map.Entry<String, Integer>> comparator =
                Comparator
                        //빈도높으면 앞에
                        .comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                        //길이가 길면 앞에
                        .thenComparing((Map.Entry<String, Integer> entry) -> entry.getKey().length(), Comparator.reverseOrder())
                        //알파벳 순으로 앞에
                        .thenComparing(Map.Entry<String, Integer>::getKey);

        values.sort(comparator);
        for (Map.Entry<String, Integer> v : values) {
            bw.append(v.getKey() + "\n");
        }
        bw.flush();
        bw.close();
    }

    //방법2 : arraylist 정렬 -> 시간 초과
    //리스트에서 indexOf로 검색하는 시간이 너무 오래걸린다.
    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Word> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            if (input.length() < M) continue;
            int index = list.indexOf(new Word(input));
            if (index >= 0) list.get(index).addCount();
            else list.add(new Word(input));
        }
        Collections.sort(list);
        for (Word word : list) {
            bw.append(word.str + "\n");
        }
        bw.flush();
        bw.close();
    }

    static class Word implements Comparable<Word> {
        String str;
        int count;

        public Word(String str) {
            this.str = str;
            this.count = 1;
        }

        void addCount() {
            this.count++;
        }

        @Override
        public int compareTo(Word o) {
            if (this.count == o.count) {
                if (this.str.length() == o.str.length()) {
                    return this.str.compareTo(o.str);
                }
                return -(this.str.length() - o.str.length());
            }
            return -(this.count - o.count);
        }

        @Override
        public boolean equals(Object obj) {
            return this.str.equals(((Word) obj).str);
        }
    }

    // 방법3 : 방법1에서 시간단축
    // list를 만들때 방법1에서는 Entry 전체를 했지만 방법3에서는 key로만 만들었다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<String> list;
        HashMap<String, Integer> hashmap = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            if (input.length() < M) continue;
            hashmap.put(input, hashmap.getOrDefault(input, 1) + 1);
        }

        list = new ArrayList<>(hashmap.keySet());
        list.sort((o1, o2) -> {
            int n1 = hashmap.get(o1);
            int n2 = hashmap.get(o2);
            if (n1==n2) {
                if(o1.length()==o2.length()) return o1.compareTo(o2);
                else return -(o1.length()-o2.length());
            } else {
                return -(n1-n2);
            }
        });
        for (String str : list) {
            bw.append(str + "\n");
        }
        bw.flush();
        bw.close();
    }
}

