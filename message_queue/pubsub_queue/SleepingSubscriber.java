package message_queue.pubsub_queue;

import message_queue.pubsub_queue.model.Message;
import message_queue.pubsub_queue.public_interface.Subscriber;

public class SleepingSubscriber implements Subscriber {
    private final String id;
    private final int SleepTimeInMillis;


    public SleepingSubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        SleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    // Why we did not pass @NotNull and final here in the parameters
    public void consume(Message message)throws InterruptedException{
        System.out.println("Subscriber" + id + "started consuming"+ message.getMsg());
        Thread.sleep(SleepTimeInMillis);
        System.out.println("Subscriber"+ id + "done consuming" + message.getMsg());
    }

}
