
public class Message {

    private String id;
    private String message;
    private String author;
    private long timestamp;

    public Message(String id, String message, String author, long timestamp) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.timestamp = timestamp;
    }

    public Message() {
        id = "";
        message = "";
        author = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTimestamp(long timestamp) {

        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public long getTimestamp() {

        return timestamp;
    }
}
