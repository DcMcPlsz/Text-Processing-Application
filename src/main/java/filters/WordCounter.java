package filters;

import java.util.HashMap;
import java.util.LinkedList;

import pipes.Pipe;

public class WordCounter {

    private Pipe<LinkedList<String>> words;

    // constructor
    public WordCounter(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public void counting() throws InterruptedException {
        // create a hashmap as its key = word, value = word count
        HashMap<String, Integer> counter = new HashMap<String, Integer>();

        // loop through the linkedlist of word
        for (String word : words.nextOrNullIfEmptied()) {
            // if the hashmap didn't have this key then
            // add in the key and word count increased by 1
            if (!counter.containsKey(word)) {
                counter.put(word, 1);
            } else {
                // else means hashmap have this key then
                // get the word count with key and increased by 1
                counter.put(word, counter.get(word) + 1);
            }
        }
        words.closeForWriting(); // put the result into pipe

        // finally then print out the result
        System.out.println(counter);
    }

}
