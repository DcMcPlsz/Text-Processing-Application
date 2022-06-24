package filters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import pipes.Pipe;
import pipes.PipeImpl;

public class TextFileReader {
    // private ActorRef nextActor;
    // private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    // public TextFileReader(ActorRef nextActor) {
    // this.nextActor = nextActor;
    // }

    private Pipe<String> file;
    private Pipe<LinkedList<String>> output = new PipeImpl<LinkedList<String>>();

    public TextFileReader(Pipe<String> file) {
        this.file = file;
    }

    public Pipe<LinkedList<String>> reading() throws InterruptedException, FileNotFoundException {
        // log.info("The file is received" + previousmessage);
        LinkedList<String> lines = new LinkedList<String>();
        File fileObject = new File(file.nextOrNullIfEmptied());
        Scanner fileReader = new Scanner(fileObject);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            lines.add(data);
        }
        fileReader.close();
        output.put(lines);
        return output;
        // nextActor.tell(lines, getSelf());

    }

}
