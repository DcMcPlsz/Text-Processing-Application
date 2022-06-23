import java.util.concurrent.CountDownLatch;
import akka.*;
import akka.actor.UntypedAbstractActor;
import akka.event.LoggingAdapter;

public class LowercaseTransformer extends UntypedActor {
    
    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().System(), this);

    public LowercaseTransformer(ActorRef nextActor){
        this.nextActor = nextActor;
    }
     @Override
     public void onReceive(Object previousmessage) throws Throwable{
        log.info("Received"+ previousmessage);
        String message = (String) previousmessage;
        String output = message.toLowerCase();
        nextActor.tell(output, getSelf());
     }

} 