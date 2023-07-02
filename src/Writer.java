import java.util.concurrent.ThreadLocalRandom;

public class Writer implements Runnable {

    private final ReadWriteLock lock;
    private final int[] database;

    public Writer(ReadWriteLock lock, int[] database) {
        this.lock = lock;
        this.database = database;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int idx = ThreadLocalRandom.current().nextInt(database.length);
                int value = ThreadLocalRandom.current().nextInt();
                lock.acquireWriteLock();
                int oldValue = database[idx];
                database[idx] = value;
                System.out.println(Thread.currentThread().getName() + " updated value at " + idx + " from " + oldValue
                        + " to " + value);
                lock.releaseWriteLock();
                Thread.sleep(ThreadLocalRandom.current().nextInt(100,500));
            }
        } catch (InterruptedException e) {
            System.out.println("Shutting down " + Thread.currentThread().getName());
        }
    }
}
