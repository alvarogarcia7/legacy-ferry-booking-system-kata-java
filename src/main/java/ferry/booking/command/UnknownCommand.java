package ferry.booking.command;

import ferry.booking.delivery.ProgramOutputter;

import java.util.ArrayList;
import java.util.List;

public class UnknownCommand implements Command {
    private ProgramOutputter programOutputter;

    public UnknownCommand(ProgramOutputter programOutputter) {
        this.programOutputter = programOutputter;
    }

    public void run() {
        List<String> message = new ArrayList<String>();
        message.add("Commands are: [search x y hh:mm] book, or list bookings");
        message.add("  search x y hh:mm");
        message.add("  book x y");
        message.add("  list bookings");
        message.add("  list ports");
        message.add("");
        message.add("Book is [book x y]");
        message.add("where x - journey id");
        message.add("where y - number of passenger");
        message.add("");
        message.add("Search is [search x y hh:mm]");
        message.add("where: x - origin port id");
        message.add("where: y - destinationg port id");
        message.add("where: hh:mm - time to search after");
        programOutputter.console.display(message.toArray(new String[0]));
    }
}
