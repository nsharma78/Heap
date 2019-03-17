/*
 * Given a list of strings with duplicate words, find first k max occurring words
 */

package main.java;
import java.util.*;

public class FirstKMaxOccurringWordsInWordsList {

    public static void main(String[] args) {
        root = new TrieNode();
        String[] words = { "code", "codes", "coder", "code", "codes", "code", "codes", "code", "coder", "coders"};
        int k = 3;
        System.out.println("The top k most frequent words in the given list are: " + firstKMaxOccurWords(words, k).toString());
    }

    private static TrieNode root;
    private static final int SIZE = 26;

    static class TrieNode {
        String word;
        int count;
        TrieNode[] children;

        public TrieNode() {
            children = new TrieNode[SIZE];
        }
    }

    static class HeapNode implements Comparable<HeapNode> {

        int count;
        String word;

        public HeapNode(int count, String word) {
            this.count = count;
            this.word = word;
        }

        @Override
        public int compareTo(HeapNode o) {
            if(this.count > o.count) return 1;
            if(this.count < o.count) return -1;
            return 0;
        }
    }

    private static List<String> firstKMaxOccurWords(String[] words, int k) {
        if (words == null || words.length == 0)
            return Collections.emptyList();

        List<String> result = new ArrayList<>();

        for(String word : words)
            insert(word);

        //create a priority queue of heapnodes
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(Collections.reverseOrder());

        preOrder(root, pq);

        while(k-- > 0 && !pq.isEmpty())
            result.add(pq.poll().word);
        return result;
    }

    public static void insert(String word) {
        TrieNode node = root;
        for(int i = 0;i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if(node.children[index] == null)
                node.children[index] = new TrieNode();
            node = node.children[index];
        }
        node.word = word;
        node.count++;
    }

    public static void preOrder(TrieNode root, PriorityQueue<HeapNode> pq) {
        TrieNode node = root;
        if(node == null)
            return;

        for(int i = 0; i < SIZE; i++) {
            if(node.children[i] != null) {
                if(node.children[i].word != null)
                    pq.add(new HeapNode(node.children[i].count, node.children[i].word));

                preOrder(node.children[i], pq);
            }
        }
    }
}
