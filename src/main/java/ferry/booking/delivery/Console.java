package ferry.booking.delivery;


import java.io.PrintStream;

public class Console {
    private PrintStream printStream;

    private Console(PrintStream printStream) {
        this.printStream = printStream;
    }

    public static Console to(PrintStream printStream) {
        return new Console(printStream);
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
}
