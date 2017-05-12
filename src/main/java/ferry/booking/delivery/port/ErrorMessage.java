package ferry.booking.delivery.port;

public class ErrorMessage {
    private String[] payload;

    public ErrorMessage(String[] payload) {
        this.payload = payload;
    }

    public void print(UserCommunication console) {
        console.display(payload);
    }
}
