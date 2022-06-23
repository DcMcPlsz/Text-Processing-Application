import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.LinkedList;
import akka.*;
import akka.actor.UntypedAbstractActor;
import akka.event.LoggingAdapter;

public class TextFileReader extends UntypedActor {
    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().System(), this);

    public RemoveNonAlphaNumericWord(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previousmessage) throws Throwable {
        log.info("The file is received");
        LinkedList<String> lines = new LinkedList<String>();
        try {
            // File fileObject = new File("FileToBeRead.txt");
            Scanner fileReader = new Scanner(previousmessage);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                lines.add(data);
                System.out.println(data);
            }
            fileReader.close();
            return lines; // code here the output
            nextActor.tell(lines, getSelf());
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
