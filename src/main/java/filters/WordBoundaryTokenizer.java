package filters;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import pipes.Pipe;
import pipes.PipeImpl;

public class WordBoundaryTokenizer {

    private Pipe<LinkedList<String>> lines;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    public WordBoundaryTokenizer(Pipe<LinkedList<String>> lines) {
        this.lines = lines;
    }

    public Pipe<LinkedList<String>> splitting() throws InterruptedException {
        List<String> temp = lines.nextOrNullIfEmptied().stream().map(line -> Arrays.asList(line.split(" ")))
                .flatMap(List::stream).collect(Collectors.toList());
        LinkedList<String> results = new LinkedList<String>();
        for (String word : temp) {
            results.add(word);
        }

        output.put(results);
        return output;
    }
}
