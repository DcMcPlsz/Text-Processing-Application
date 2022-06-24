package filters;

import java.util.LinkedList;

import pipes.Pipe;
import pipes.PipeImpl;

public class RootWordsConverter {

    private Pipe<LinkedList<String>> words;
    private Pipe<String[]> output = new PipeImpl<String[]>();

    public RootWordsConverter(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public Pipe<String[]> converting() throws InterruptedException {

        LinkedList<String> temp = new LinkedList<String>();
        Stemmer stemmer = new Stemmer();

        for (String word : words.nextOrNullIfEmptied()) {
            stemmer.add(word.toCharArray(), word.length());
            stemmer.stem();
            temp.add(stemmer.toString());
        }

        String[] results = temp.toArray(new String[temp.size()]);

        output.put(results);
        return output;
    }

}
