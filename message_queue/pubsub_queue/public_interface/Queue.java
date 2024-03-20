package message_queue.pubsub_queue.public_interface;

import com.sun.istack.internal.NotNull;
import message_queue.pubsub_queue.handler.TopicHandler;
import message_queue.pubsub_queue.model.Message;
import message_queue.pubsub_queue.model.Topic;
import message_queue.pubsub_queue.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    // Arguments
    private final Map<String, TopicHandler> topicHandlers;

    // Constructor
    public Queue(){
        this.topicHandlers = new HashMap<>();
    }

    // Diff apis for all the functionalities we want from our queue
    public Topic createTopic(@NotNull final String topicName){
        // Create a ne wtopic by passing the topic name and topic id
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        // Since not able to set getters in Topic class so below func not working
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: "+ topic.getClass().getName());
        return topic;
    }

    // A func to make a subscriber subscribe to a topic
    public void subscribe(@NotNull final Subscriber subscriber, @NotNull final Topic topic){
        topic.addSubscribers(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + "subscribed to topic: " + topic.getClass().getName());
    }

    // a method to publish a message to a topic
    public void publish(@NotNull final Topic topic ,@NotNull final Message message){
        // Whenever we have to publish a message we first add that message to the topic then send out that msg to all the
        // SUBSCRIBERS

        topic.addMessage(message);
        System.out.println(message.getMsg() + "published to topic" + topic.getTopicName());
        // Now send this msg to all subscribers
        // This thread just publishes the message abd vanishes away it is not a oersistent thread
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    //reset offset
    public void resetOffset(@NotNull final Topic topic, @NotNull final Subscriber subscriber, @NotNull final int offset){
        // fetch all the subscribers for that topic and reset its offset
        for(TopicSubscriber topicSubscriber: topic.getSubscribers()){
            topicSubscriber.getOffset().set(offset);
            System.out.println(topicSubscriber.getSubscriber().getId() + "offset has been reset to"+ offset);
            new Thread(()-> topicHandlers.get(topic.getTopicId()).startSubscriberWorker(topicSubscriber)).start();
            break;
        }
    }
}
