package ferry.booking.delivery;

import ferry.booking.delivery.adapter.Console;
import ferry.booking.delivery.port.UserCommunication;

public class ProgramOutputter {
    public final UserCommunication console;
    private final TimeTablePrinter timeTablePrinter;

    public ProgramOutputter(TimeTablePrinter timeTablePrinter, Console console) {
        this.timeTablePrinter = timeTablePrinter;
        this.console = console;
    }

    public static ProgramOutputter aNew(TimeTablePrinter timeTablePrinter, Console console) {
        return new ProgramOutputter(timeTablePrinter, console);
    }
}
