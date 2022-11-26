package com.example.dictionary;

public class Trie {
    static class TrieNode{
        boolean endOfWord;
        String meaning;
        TrieNode[] children;
        TrieNode(){
            endOfWord = false;
            meaning = "";
            children = new TrieNode[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    };


    // for insert operation
    static TrieNode root;

    static void insert(String word, String meaning){
        TrieNode temp = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i)-'a';
            if(temp.children[index] == null){
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];
        }
        temp.endOfWord = true;
        temp.meaning = meaning;
    }


    // for search operation
    static String search(String word){
        TrieNode temp = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i)-'a';
            if(temp.children[index] == null){
                return "Word not found";
            }
            temp = temp.children[index];
        }
        // for that child find all the valid words
        // recursion to find all valid words
        return temp.meaning;
    }


    // main function
    public static void main(String[] args) {
        root = new TrieNode();
        insert("IntelliJ", "IDE to develop applications");
        insert("car", "Wheeled vehicle used to travel");
        insert("pen", "A writing implement with a point from which ink flows");

        System.out.println(search("kishan"));
        System.out.println(search("pen"));
        System.out.println(search("car"));
    }

    // suggestions
    // try finding the word with the help of search
    // then find all valid words for children
}
