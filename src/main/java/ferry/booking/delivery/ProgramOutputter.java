package ferry.booking.delivery;

import ferry.booking.delivery.adapter.Console;
import ferry.booking.delivery.port.ErrorMessage;
import ferry.booking.delivery.port.HelpMessage;
import ferry.booking.delivery.port.UserCommunication;
import ferry.booking.delivery.port.UserMessage;
import ferry.booking.port.Port;

import java.util.ArrayList;
import java.util.List;

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

    public void printPorts(List<Port> ports) {
        List<String> message = new ArrayList<String>();
        message.add("Ports:");
        message.add("------");
        for (Port port : ports) {
            message.add(String.format("%d - %s", port.id, port.name));
        }
        message.add("");
        console.display(message.toArray(new String[0]));
    }

    public void print(HelpMessage helpMessage) {
        helpMessage.print(console);
    }

    public void print(UserMessage userMessage) {
        userMessage.print(console);
    }

    public void print(ErrorMessage errorMessage) {
        errorMessage.print(console);
    }
}
