package filtersAkka;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WordCounterAkka extends UntypedActor {

    private ActorRef nextActor;
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private int output = 0;

    public WordCounterAkka(ActorRef nextActor) {
        this.nextActor = nextActor;
    }

    @Override
    public void onReceive(Object previous) throws Throwable {
        log.info(" The message received is : " + previous);

        String[] message = (String[]) previous;
        for (int i = 0; i < message.length; i++) {

            int count = 1;
            if (message[i] != null)
                System.out.println("Word: " + message[i] + " Count: " + count);

            for (int j = i + 1; j < message.length; j++) {
                if (message[j] != null) {
                    if (message[i].equals(message[j])) {
                        count++;
                        if (message[j] != null) {
                            System.out.println("Word: " + message[j] + " Count: " + count);
                            message[j] = null;
                        }

                    }
                }

            }

            output++;
        }

        nextActor.tell(output, getSelf());

    }

    public int getNumCount() {
        return output;
    }

}
