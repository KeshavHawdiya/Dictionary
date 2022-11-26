package com.example.dictionary;

import java.util.HashMap;
import java.util.Map;

public class DictionaryUsingHashMap {

    private HashMap<String, String> dictionary;                      // here dictionary treated as reference not an object
    public DictionaryUsingHashMap(){                                 // Constructor
        dictionary = new HashMap<String, String>();                  // creating obj for that dictionary reference
        addListOfWords();                                            // Calling below method via constructor to fetch meanings
    }

    public boolean addWord(String word, String meaning){
        dictionary.put(word, meaning);                               // by HashMap.put ---putting word&meanings in map of dictionary with help of addWord function
        return true;
    }

    // to provide suggestions on typing --- some additional functionality
    public String[] getSuggestions(String word){
        String[] suggestions = new String[5];
        int i =0;
        for(Map.Entry<String, String> entry : dictionary.entrySet()){
            int firstIndex = 0;
            int lastIndex = Math.min(word.length(), entry.getKey().length());
            if(word.compareTo(entry.getKey().substring(firstIndex, lastIndex)) == 0){
                suggestions[i++] = entry.getKey();
            }
            if(i==4) break;
        }
        return suggestions;
    }

    public String findMeaning(String word){
        if(!dictionary.containsKey(word)){
            return "Given word not found";
        }
        else return dictionary.get(word);
    }

    // add some words & their meanings by this method below
    private void addListOfWords(){
        addWord("aeroplane", "An aircraft that has a fixed wing and is powered by propellers or jets");
        addWord("accept", "Consider or hold as true");
        addWord("class", "A collection of things sharing a common attribute");
        addWord("common", "Belonging equality to");
        addWord("dictionary", "A reference book contains an alphabetical list of words with info about them");
        addWord("enlightenment", "Education that results in understanding and the spread of knowledge");
        addWord("folk", "People in general (often used in the plural)");
        addWord("home", "A place to take shelter");
        addWord("project", "Any piece of work that is undertaken or attempted");
        addWord("proficient", "Having or showing knowledge and skill");
        addWord("syntax", "The grammatical arrangement of words in sentences");
        addWord("tractor", "A wheeled vehicle with large wheels, used in farming");
        addWord("remedy", "A medicine or Treatment for a disease");
        addWord("school", "An educational institution");
        addWord("student", "A learner who is enrolled in an educational institution");
        addWord("teacher", "A person whose occupation is teaching");
        addWord("tuition", "A fee paid for instructions");
        addWord("yield", "Production of a certain amount");
    }
}
