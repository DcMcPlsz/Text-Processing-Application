package filters;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class LowercaseTransformer extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public LowercaseTransformer(ActorRef nextActor) {
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