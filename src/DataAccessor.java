import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataAccessor {
    public static void main(String[] args) throws InterruptedException {
        int dbSize = ThreadLocalRandom.current().nextInt(5, 21);
        int[] database = new int[dbSize];
        for (int i = 1 ; i < dbSize; ++i) {
            database[i] = i;
        }
        System.out.println("Created initial database: " + Arrays.toString(database));

        ReadWriteLock lock = new ReadWriteLock();

        // 5 - 10 readers, 1 - 3 writers
        int readers = ThreadLocalRandom.current().nextInt(5, 11);
        int writers = ThreadLocalRandom.current().nextInt(1,4);

        System.out.println("Creating " + readers + " readers and " + writers + " writers");

        int numThreads = readers + writers;
        List<Thread> threads = new ArrayList<>(numThreads);
        for (int i = 0; i < readers; ++i) {
            Thread thread = new Thread(new Reader(lock, database));
            thread.setName("Reader-" + (i + 1));
            threads.add(thread);
        }
        for (int i = 0; i < writers; ++i) {
            Thread thread = new Thread(new Writer(lock, database));
            thread.setName("Writer-" + (i + 1));
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.start();
        }

        // allow interactions for at least 20 seconds
        Thread.sleep(20_000);

        // interrupt everything
        for (Thread t : threads) {
            t.interrupt();
        }

        // wait for all threads to stop (should be nearly immediate)
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All operations stopped successfully. Final state: " + Arrays.toString(database));
    }
}
