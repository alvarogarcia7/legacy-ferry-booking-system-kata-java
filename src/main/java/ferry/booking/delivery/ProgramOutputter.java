package ferry.booking.delivery;

import ferry.booking.port.Port;

import java.util.List;

public class ProgramOutputter {
    private final TimeTablePrinter timeTablePrinter;
    public final Console console;

    public static ProgramOutputter aNew(TimeTablePrinter timeTablePrinter, Console console) {
        return new ProgramOutputter(timeTablePrinter, console);
    }

    public ProgramOutputter(TimeTablePrinter timeTablePrinter, Console console) {
        this.timeTablePrinter = timeTablePrinter;
        this.console = console;
    }

    public void printPorts(List<Port> ports) {
        console.println("Ports:");
        console.println("------");
        for (Port port : ports) {
            console.printf("%d - %s", port.id, port.name);
            console.println();
        }
        console.println();
    }
}
