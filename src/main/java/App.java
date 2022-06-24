
import filters.*;
import pipes.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class App {
    // private ActorSystem system;

    public void TextFileReaderandSplitLinetoWord() {

    }

    public static void main(String[] args) throws Throwable {
        ActorSystem actorsystem = ActorSystem.create();
        // JavaTestKit endprobe = new JavaTestKit(system);
        // Akka.system().actorOf(TextFileReader.props());
        // ActorRef actorref = actorsystem.actorOf(Props.create(TextFileReader.class),
        // "actorref");
        // Exception in thread "main" java.lang.IllegalArgumentException: no matching
        // constructor found on class filters.TextFileReader for arguments []
        // actorref.tell("here", null);
        Pipe<String> file = new PipeImpl<String>();
        file.put("FileToBeRead.txt");
        TextFileReader fileReader = new TextFileReader(file);
        fileReader.reading();
        // fileReader.onReceive("FileToBeRead.txt");
    }
}