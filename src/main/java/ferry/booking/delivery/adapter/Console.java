package ferry.booking.delivery.adapter;


import ferry.booking.delivery.port.UserCommunication;

import java.io.PrintStream;

public class Console implements UserCommunication {
    private PrintStream printStream;

    public static Console to(PrintStream printStream) {
        return new Console(printStream);
    }

    private Console(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void println(String message) {
        printStream.println(message);
    }

    public void printf(String format, Object... values) {
        printStream.printf(format, values);
    }

    public void println() {
        printStream.println();
    }

    @Override
    public void display(String[] message) {
        for (String line : message) {
            this.println(line);
        }
    }

    @Override
    public void display(String message) {
        this.println(message);
    }
}
