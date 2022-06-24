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

    // constructor
    public WordBoundaryTokenizer(Pipe<LinkedList<String>> lines) {
        this.lines = lines;
    }

    public Pipe<LinkedList<String>> splitting() throws InterruptedException {
        // mapped out each line then split it with space
        // data structure: list<String[]>
        // then get turn the result form array to list
        // data structure: list<list<String>>
        // then flat map the nested list and form a new list with the elements
        // data structure: list<String>
        List<String> temp = lines.nextOrNullIfEmptied().stream().map(line -> Arrays.asList(line.split(" ")))
                .flatMap(List::stream).collect(Collectors.toList());
        lines.closeForWriting(); // close the pipe after done using

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
