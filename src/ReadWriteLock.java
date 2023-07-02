import java.util.HashSet;

public class ReadWriteLock {

    private final HashSet<Long> readers;
    long writer;

    public ReadWriteLock() {
        readers = new HashSet<>();
        writer = -1L;
    }

    public synchronized void acquireReadLock() throws InterruptedException {
        while (writer != -1L) {
            wait();
        }
        readers.add(Thread.currentThread().getId());
    }

    public synchronized void releaseReadLock() {
        if (!readers.contains(Thread.currentThread().getId())) {
            throw new IllegalStateException("Thread: " + Thread.currentThread().getId() +
                    " never held the read lock");
        }
        readers.remove(Thread.currentThread().getId());
        // wake up any waiting writer
        notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while (!readers.isEmpty() || (writer != -1 && (writer != Thread.currentThread().getId()))) {
            wait();
        }
        writer = Thread.currentThread().getId();
    }

    public synchronized void releaseWriteLock() {
        if (writer == -1) {
            throw new IllegalStateException("Thread: " + Thread.currentThread().getId() +
                    " attempted to release a writer lock held by no thread");
        }
        if (writer != Thread.currentThread().getId()) {
            throw new IllegalStateException("Thread: " + Thread.currentThread().getId() +
                    " attempted to release a writer lock held by a different thread");
        }
        writer = -1;
        // multiple readers might be blocked, notifying only 1 is not sufficient
        notifyAll();
    }
}
