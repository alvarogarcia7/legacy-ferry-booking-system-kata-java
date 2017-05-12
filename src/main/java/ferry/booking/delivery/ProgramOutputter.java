package ferry.booking.delivery;

import ferry.booking.delivery.adapter.Console;
import ferry.booking.delivery.port.UserCommunication;

public class ProgramOutputter {
    private final TimeTablePrinter timeTablePrinter;
    public final UserCommunication console;

    public static ProgramOutputter aNew(TimeTablePrinter timeTablePrinter, Console console) {
        return new ProgramOutputter(timeTablePrinter, console);
    }

    public ProgramOutputter(TimeTablePrinter timeTablePrinter, Console console) {
        this.timeTablePrinter = timeTablePrinter;
        this.console = console;
    }
}
