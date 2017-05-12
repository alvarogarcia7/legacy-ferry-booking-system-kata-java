package ferry.booking.delivery.port;

public class UserMessage {
    private String message;

    public UserMessage(String message) {
        this.message = message;
    }

    public void print(UserCommunication userCommunication) {
        userCommunication.display(this.message);
    }
}
