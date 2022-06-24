package filtersAkka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class LowercaseTransformerAkka extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public LowercaseTransformerAkka(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previousmessage) throws Throwable {
        log.info("Received" + previousmessage);
        String message = (String) previousmessage;
        String output = message.toLowerCase();
        nextActor.tell(output, getSelf());
    }

}