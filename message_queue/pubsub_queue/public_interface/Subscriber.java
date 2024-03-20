package message_queue.pubsub_queue.public_interface;
import message_queue.pubsub_queue.model.Message;

// Subscriber interface is for all subscribers of the topics of the queue
public interface Subscriber {
    // To get the id of the subscriber
    String getId();
    // This method will be called so that subscriber can consume the msg from the queue
    // ASK : WHAT IS INTERRUPTED EXCEPTION ??
    void consume(Message message) throws InterruptedException;
}
