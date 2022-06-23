import java.util.concurrent.CountDownLatch;
import akka.*;
import akka.actor.UntypedAbstractActor;
import akka.event.LoggingAdapter;

public class RemoveNonAlphaNumericWord extends UntypedActor {

      private ActorRef nextActor;
      private LoggingAdapter log = Logging.getLogger(getContext().System(), this);

    public RemoveNonAlphaNumericWord(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previousmessage) throws Throwable{
        log.info("The message is received: "+previousmessage);
        
        String message = (String) previousmessage;
        String output = message.replaceAll("[^A-Za-z0-9]", "");
        nextActor.tell(output, getSelf());                           
    }
}
