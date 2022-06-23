import java.util.concurrent.CountDownLatch;
import akka.*;
import akka.actor.*;
import akka.event.*;

public class WordCounter extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().System(), this);

    public WordCounter(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public int onReceive(Object previous) throws Throwable {
        log.info(" The message received is : " + previous);

        String[] message = (String[]) previous;
        int output = 0;
        for (String s : message) {
            output++;
        }

        return output;
    }
}
