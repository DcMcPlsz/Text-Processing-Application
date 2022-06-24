package pipes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PipeImpl<T> implements Pipe<T> {
    // instantiate a linkedlist implementation and assign it to the queue interface
    // so, the linkedlist can use the functionality declared by the queue interface
    private Queue<T> buffer = new LinkedList<T>();

    // new pipe is always open for writting new value
    private boolean isOpenForWriting = true;

    // new pipe is will not will read before creating
    private boolean hasReadLastObject = false;

    @Override
    public synchronized boolean put(T obj) {
        if (!isOpenForWriting) {
            throw new RuntimeException(new IOException("pipe is closed"));
            // throw exception when there is value putting in the closed pipe
        } else if (obj == null) {
            throw new IllegalArgumentException("cannot put null in pipe");
            // since null is reserved for pipe-empty sentinel value
        }

        // add the value into queue and get the result of adding
        boolean wasAdded = buffer.add(obj);
        notify(); // notify the pipe that is waiting for value
        return wasAdded; // return the result of adding
    }

    @Override
    public synchronized T nextOrNullIfEmptied() throws InterruptedException {
        if (hasReadLastObject) { // cannot read from a closed pipe
            throw new NoSuchElementException("pipe is closed and empty");
            // since close pipe will never contain any further values
        }

        while (buffer.isEmpty()) {
            wait();
            // if the pipe is not close but empty then will wait
            // wait until whether the pipe is closed or putting new value
        }

        // take the value of queue and store it into generic variable
        T obj = buffer.remove();
        if (obj == null) { // if the value is null
            // that mean the pipe just tonggling from writting new value
            hasReadLastObject = true;
            // then toggle the pipe to close after the pipe value is read
        }
        return obj; // return the pipe value
    }

    @Override
    public synchronized void closeForWriting() { // function to close the pipe
        isOpenForWriting = false; // tonggle the pipe from writting new value
        buffer.add(null); // add null to the pipe so that no value will be accepted
        notify(); // notify the pipe that is waiting for value
    }
}