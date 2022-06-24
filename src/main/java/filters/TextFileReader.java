package filters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import pipes.Pipe;
import pipes.PipeImpl;

public class TextFileReader {

    private Pipe<String> file;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    // constructor
    public TextFileReader(Pipe<String> file) {
        this.file = file;
    }

    public Pipe<LinkedList<String>> reading() throws InterruptedException, FileNotFoundException {
        LinkedList<String> lines = new LinkedList<String>(); // create a linkedlist for lines

        // create a file and passing in the value of the file pipe
        File fileObject = new File(file.nextOrNullIfEmptied());
        file.closeForWriting(); // close the pipe after done using

        Scanner fileReader = new Scanner(fileObject); // create a scanner

        // loop through every line of the file
        while (fileReader.hasNextLine()) {
            lines.add(fileReader.nextLine());// add each line into the linkedlist
        }

        fileReader.close();// close the scanner after done using
        output.put(lines); // put the result into pipe
        output.closeForWriting(); // close the pipe after done using
        return output;

    }

}
