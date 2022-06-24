package filters;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pipes.Pipe;
import pipes.PipeImpl;

public class RemoveNonAlphaNumericWord {

    private Pipe<LinkedList<String>> words;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    // constructor
    public RemoveNonAlphaNumericWord(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public Pipe<LinkedList<String>> removing() throws InterruptedException {
        // mapped out each word and remove non alpha numeric word then store it into a
        // list of string
        List<String> temp = words.nextOrNullIfEmptied().stream().map(word -> word.replaceAll("[^A-Za-z0-9]", ""))
                .collect(Collectors.toList());
        words.closeForWriting(); // close the pipe after done using

        // convert the list of string to linkedlist of string
        LinkedList<String> results = new LinkedList<String>();
        for (String word : temp) {
            results.add(word);
        }

        output.put(results); // put the result into pipe
        output.closeForWriting(); // close the pipe after done using
        return output;
    }
}
