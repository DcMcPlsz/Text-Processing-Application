package filters;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

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
        for (int i = 0; i < message.length; i++) {
            stemmer.add(message[i].toCharArray(), message[i].length());
            stemmer.stem();
            String stem = stemmer.toString();
            output[i] = stem;
        }
        nextActor.tell(output, getSelf());
    }
}
