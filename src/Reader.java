import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    private final ReadWriteLock lock;
    private final int[] database;
    public Reader(ReadWriteLock lock, int[] database) {
        this.lock = lock;
        this.database = database;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int idx = ThreadLocalRandom.current().nextInt(database.length);
                lock.acquireReadLock();
                System.out.println(Thread.currentThread().getName() + " read data at " + idx + " = " + database[idx]);
                lock.releaseReadLock();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,2000));
            }
        } catch (InterruptedException e) {
            System.out.println("Shutting down " + Thread.currentThread().getName());
        }
    }
}
