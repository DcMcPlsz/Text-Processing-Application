package pipes;

public interface Pipe<T> { // create a interface for pipe
    public boolean put(T obj);

    public T nextOrNullIfEmptied() throws InterruptedException;

    public void closeForWriting();
}
