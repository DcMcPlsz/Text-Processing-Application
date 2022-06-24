package filters;

import java.util.LinkedList;
import java.util.Scanner; // Import the Scanner class to read text files

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class TextFileReader extends UntypedActor {
    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public TextFileReader(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previousmessage) throws Throwable {
        log.info("The file is received");
        LinkedList<String> lines = new LinkedList<String>();
        // File fileObject = new File("FileToBeRead.txt");
        Scanner fileReader = new Scanner((Readable) previousmessage);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            lines.add(data);
            System.out.println(data);
        }
        fileReader.close();
        // return lines; // code here the output
        nextActor.tell(lines, getSelf());

    }

}
