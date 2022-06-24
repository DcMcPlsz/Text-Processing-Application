package filters;

import java.util.LinkedList;

import pipes.Pipe;
import pipes.PipeImpl;

public class RootWordsConverter {

    private Pipe<LinkedList<String>> words;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    // constructor
    public RootWordsConverter(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public Pipe<LinkedList<String>> converting() throws InterruptedException {
        LinkedList<String> result = new LinkedList<String>(); // create a linkedlist for output
        Stemmer stemmer = new Stemmer(); // create a instance of stemmer

        // loop through the linkedlist of words
        for (String word : words.nextOrNullIfEmptied()) {
            // for each word add into the stemmer
            stemmer.add(word.toCharArray(), word.length());
            // remove the root words for each word
            stemmer.stem();
            // add the result into the temporary linkedlist
            result.add(stemmer.toString());
        }
        words.closeForWriting(); // close the pipe after done using

        output.put(result); // put the result into pipe
        output.closeForWriting(); // close the pipe after done using
        return output;
    }

}
