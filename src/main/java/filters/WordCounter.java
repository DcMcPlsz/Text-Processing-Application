package filters;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WordCounter extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public WordCounter(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previous) throws Throwable {
        log.info(" The message received is : " + previous);

        String[] message = (String[]) previous;
        int output = 0;
        for (String s : message) {
            output++;
        }

        // return output;
    }
}
