package message_queue.pubsub_queue.handler;

import com.sun.istack.internal.NotNull;
import message_queue.pubsub_queue.model.Message;
import message_queue.pubsub_queue.model.Topic;
import message_queue.pubsub_queue.model.TopicSubscriber;

public class SubscriberWorker implements Runnable{
    // data members
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    //constructor
    public SubscriberWorker(@NotNull final Topic topic,@NotNull final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run(){
        synchronized (topicSubscriber){
            // We are running this loop forever so whenever it has any msgs it will start processing else it will wait
            do{
                int curOffset = topicSubscriber.getOffset().get();
                while(curOffset >= topic.getMessages().size()){
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // now when curOffset is less than message size it means there are some msgs to consume
                // Fetch the msg in the topic at current offset
                Message message = topic.getMessages().get(curOffset);
                try {
                    topicSubscriber.getSubscriber().consume(message);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            // To prevent any other thread changing the offset for some other thread use compare and set
                // Also for eg we just did reset offset then what offset we were  thinking it was not that so first compare then only do +1
            topicSubscriber.getOffset().compareAndSet(curOffset,curOffset+1);
            }while (true);
        }
    }

synchronized public void wakeUpIfNeeded(){
        synchronized (topicSubscriber){
            topicSubscriber.notify();
        }
}
}
