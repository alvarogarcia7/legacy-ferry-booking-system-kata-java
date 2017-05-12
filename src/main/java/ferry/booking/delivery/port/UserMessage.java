package ferry.booking.delivery.port;

import ferry.booking.delivery.adapter.Console;

public class UserMessage {
    private String message;

    public UserMessage(String message) {
        this.message = message;
    }

    public void print(UserCommunication console) {
        console.display(this.message);
    }
}
