package filters;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pipes.Pipe;
import pipes.PipeImpl;

public class RemoveNonAlphaNumericWord {

    private Pipe<LinkedList<String>> words;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    public RemoveNonAlphaNumericWord(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public Pipe<LinkedList<String>> removing() throws InterruptedException {

        List<String> temp = words.nextOrNullIfEmptied().stream().map(word -> word.replaceAll("[^A-Za-z0-9]", ""))
                .collect(Collectors.toList());

        LinkedList<String> results = new LinkedList<String>();
        for (String word : temp) {
            results.add(word);
        }

        output.put(results);
        return output;
    }
}
