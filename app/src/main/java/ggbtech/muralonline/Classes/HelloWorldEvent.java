package ggbtech.muralonline.Classes;

/**
 * Created by AEDI on 13/10/16.
 */
public class HelloWorldEvent {

    private final String message;

    public HelloWorldEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
