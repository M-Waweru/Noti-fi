package broadcast;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Scheduler {

    private boolean isDone = false;

    void schedulePublish(final LeafNode leafNode, long delay, final Item item) {
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + "\n" +
                        "Thread's name: " + Thread.currentThread().getName());
                try {
                    System.out.println(item.toString());
                    leafNode.publish(item);
                } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException |
                        SmackException.NotConnectedException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture scheduledFuture = executor.schedule(repeatedTask, delay, TimeUnit.MILLISECONDS);
        while (!isDone) {
            if (scheduledFuture.isDone()) {
                executor.shutdown();
                isDone = true;
                System.out.println("Task done...................");
            }
        }
    }
}
