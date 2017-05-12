package ferry.booking.delivery;

public class TimeTablePrinter {
    private Console console;

    public static TimeTablePrinter console(Console console) {
        return new TimeTablePrinter(console);
    }

    public TimeTablePrinter(Console console) {
        this.console = console;
    }

    public void displayWelcomeMessage() {
        this.console.println("Welcome to the Ferry Finding System");
        this.console.println("=======");
        this.console.println("Ferry Time Table");
    }
}
