package message_queue.pubsub_queue.model;

import message_queue.pubsub_queue.public_interface.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class TopicSubscriber {
    private final AtomicInteger offset;
    private final Subscriber subscriber;

    public TopicSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
        this.offset = new AtomicInteger(0);
    }

    public Subscriber getSubscriber(){
        return this.subscriber;
    }

    public AtomicInteger getOffset(){
        return this.offset;
    }
}
