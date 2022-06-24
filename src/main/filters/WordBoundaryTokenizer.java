//package pipefilter.filter;
import akka.*;
import akka.actor.UntypedAbstractActor;
import akka.event.LoggingAdapter;


public class WordBoundaryTokenization extends UntypedActor{
    
  private ActorRef nextActor;
  private LoggingAdapter log = Logging.getLogger(getContext().System(), this);

  public WordBoundaryTokenization(ActorRef nextActor) {
      this.nextActor = nextActor;
  }

  @Override
  public int onReceive(Object line) throws Throwable {
      log.info(" The message received is : " + line);
      String lines = "file.txt";
      String[] message = lines.split(" ");
      for (String s : message) {
          System.out.println(s);
      }
  }
}
