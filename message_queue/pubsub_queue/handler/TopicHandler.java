package message_queue.pubsub_queue.handler;

import com.sun.istack.internal.NotNull;
import message_queue.pubsub_queue.model.Topic;
import message_queue.pubsub_queue.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

// Topic Handler is handling a [particular topic in a queue
// SOLID principles followed here : Separation of concerns as this topic handler will handle everything related to a topic
public class TopicHandler {
    // It will have topic and list of subscriber workers
    private final Topic topic;
    // A map to store subscriber id and subscriber
    private final Map<String, SubscriberWorker> subscriberWorkers;


    public TopicHandler(Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    // publish method
    // It has to fetch all the subscribers of a topic, traverse through them and send message to all the subscribers
    public void publish(){
        // For each subscriber start all the workerers for that subscriber which will // consume the msg
        for(TopicSubscriber topicSubscriber: topic.getSubscribers()){
            startSubscriberWorker(topicSubscriber);
        }
    }

    // This func is to start workers of the subscriber
    public void startSubscriberWorker(@NotNull final TopicSubscriber topicSubscriber){
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        // Lazy creation we do not create the workers until it is needed
        if(!subscriberWorkers.containsKey(subscriberId)){
            final SubscriberWorker subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkers.put(subscriberId,subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        // The subscriber worker will be active till it has msgs to process after that it will go to sleep
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeeded();
    }
}
