package ferry.booking.delivery;

public class HelpMessage {
    private String[] payload;

    public HelpMessage(String[] payload) {
        this.payload = payload;
    }

    public void print(Console console) {
        for (String line : payload) {
            console.println(line);
        }
    }
}
