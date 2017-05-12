package ferry.booking.delivery;

public class ErrorMessage {
    private String[] payload;

    public ErrorMessage(String[] payload) {
        this.payload = payload;
    }

    public void print(Console console) {
        for (String message : payload) {
            console.println(message);
        }
    }
}
