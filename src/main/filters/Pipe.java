
/**
 * A pipe interface, queue that two adjacent filters use to share
 * data.
 *
 * Two adjacent filters have a Producer/Consumer relationship.
 * Thus pipe need implemented as blocking queues.
 *
 * The type of the Pipe is determined by the
 * output/input type of the filters it serves.
 *
 * Note that:
 * The output type of a filter = input type of the next filter.
 *
 * @param <T> the type of data the Pipe holds
 */
public interface Pipe<T> {
    T take() throws InterruptedException;

    void put(T t) throws InterruptedException;
}
