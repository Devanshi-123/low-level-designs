package message_queue.pubsub_queue;

import message_queue.pubsub_queue.model.Message;
import message_queue.pubsub_queue.model.Topic;
import message_queue.pubsub_queue.public_interface.Queue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // We need a queue , topics and some sleeping subscribers in it
        // Step 1 : Create a new queue
        final Queue queue = new Queue();
        // From that queue create new topics
        final Topic topic1 = queue.createTopic("topic 1");
        final Topic topic2 = queue.createTopic("topic 2");
        // Create new subscribers
        // Why have we created sleeping subscribers and not subscribers
        final SleepingSubscriber subscriber1 = new SleepingSubscriber("sub1",10000);
        final SleepingSubscriber subscriber2 = new SleepingSubscriber("sub2",10000);
        // Subscribe above subscribers to topic 1
        queue.subscribe(subscriber1, topic1);
        queue.subscribe(subscriber2, topic1);
        // Create one more subscriber and subscribe it to topic 2
        final SleepingSubscriber subscriber3 = new SleepingSubscriber("sub3",5000);
        queue.subscribe(subscriber3,topic2);

        // publish msg1 and 2 to topic 1
        queue.publish(topic1, new Message("msg1"));
        queue.publish(topic1, new Message("msg2"));

        //publish msg 3 in topic 2
        queue.publish(topic2, new Message("msg3"));

        Thread.sleep(15000);

        // Again publish some msgs to both the topics
        queue.publish(topic1, new Message("msg5"));
        queue.publish(topic2, new Message("msg4"));

        // reset the offset of first subscriber in first topic to 0
        queue.resetOffset(topic1, subscriber1, 0);
    }
}