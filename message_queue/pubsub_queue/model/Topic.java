package message_queue.pubsub_queue.model;

import com.sun.istack.internal.NotNull;


import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;


    // Constructor of topic
    public Topic(@NotNull final String topicName,@NotNull final String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }
    // method to add a message to a topic
    public synchronized void addMessage(@NotNull final Message message){
        messages.add(message);
    }

    // method to add new subscribers
    public synchronized void addSubscribers(@NotNull final TopicSubscriber subscriber){
        subscribers.add(subscriber);
    }

    public String getTopicId(){
        return this.topicId;
    }

    public String getTopicName(){
        return this.topicName;
    }

    public List<TopicSubscriber> getSubscribers(){
        return this.subscribers;
    }

    public List<Message> getMessages(){
        return this.messages;
    }
}
