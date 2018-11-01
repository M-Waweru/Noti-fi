package broadcast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class AppExecutors {
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor diskIO;
    private final Executor networkIO;

    private AppExecutors(Executor diskIO, Executor networkIO) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
    }

    static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3));
            }
        }
        return sInstance;
    }

    Executor getDiskIO() {
        return diskIO;
    }

    Executor getNetworkIO() {
        return networkIO;
    }
}
