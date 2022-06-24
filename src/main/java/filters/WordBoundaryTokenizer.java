package filters;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WordBoundaryTokenizer extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void WordBoundaryTokenizern(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object line) throws Throwable {
        log.info(" The message received is : " + line);
        String lines = "FileToBeRead.txt";
        String[] message = lines.split(" ");
        for (String s : message) {
            nextActor.tell(s, getSelf());
            System.out.println(s);
        }
    }
}
