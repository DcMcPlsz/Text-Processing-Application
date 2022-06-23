package filters;

import java.util.concurrent.CountDownLatch;
import akka.*;
import akka.actor.*;
import akka.event.*;

public class RootWordsConverter extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void WordCounter(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previous) throws Throwable {
        log.info(" The message received is : " + previous);

        String[] message = (String[]) previous;
        String[] output = { "" };
        Stemmer stemmer = new Stemmer();
        for (String s : message) {
            stemmer.add(word.toCharArray(), word.length());
            stemmer.stem();
            String stem = stemmer.toString();
            output[s] = stem;
        }
        nextActor.tell(output, getSelf());
    }
}
