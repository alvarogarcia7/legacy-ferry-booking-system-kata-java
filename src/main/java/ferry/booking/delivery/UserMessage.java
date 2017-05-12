package ferry.booking.delivery;

public class UserMessage {
    private String message;

    public UserMessage(String message) {
        this.message = message;
    }

    public void print(Console console) {
        console.println(this.message);
    }
}
