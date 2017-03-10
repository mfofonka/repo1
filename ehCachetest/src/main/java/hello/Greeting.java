package hello;

import java.io.Serializable;

public class Greeting implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7083908649768943941L;
    private long id;
    private String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Greeting() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}