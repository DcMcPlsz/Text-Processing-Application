package filters;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pipes.Pipe;
import pipes.PipeImpl;

public class LowercaseTransformer {

    private Pipe<LinkedList<String>> words;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    public LowercaseTransformer(Pipe<LinkedList<String>> words) {
        this.words = words;
    }

    public Pipe<LinkedList<String>> transforming() throws InterruptedException {

        List<String> temp = words.nextOrNullIfEmptied().stream().map(word -> word.toLowerCase())
                .collect(Collectors.toList());

        LinkedList<String> results = new LinkedList<String>();
        for (String lowerCaseWord : temp) {
            results.add(lowerCaseWord);
        }

        output.put(results);
        return output;
    }

}