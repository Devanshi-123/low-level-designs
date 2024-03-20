package message_queue.pubsub_queue.model;


public class Message {
    private final String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }

}
