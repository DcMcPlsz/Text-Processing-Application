package filters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pipes.Pipe;
import pipes.PipeImpl;

public class WordCounter {

    private Pipe<String[]> words;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();
    // private int output = 0;

    public WordCounter(Pipe<String[]> words) {
        this.words = words;
    }

    public void counting() throws InterruptedException {

        HashMap<String, Integer> counter = new HashMap<String, Integer>();

        for (String word : words.nextOrNullIfEmptied()) {
            if (!counter.containsKey(word)) {
                counter.put(word, 1);
            } else {
                counter.put(word, counter.get(word) + 1);
            }
        }
        System.out.println(counter);
    }

}
