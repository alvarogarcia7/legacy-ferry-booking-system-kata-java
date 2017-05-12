package ferry.booking.command;

import ferry.booking.delivery.Console;
import ferry.booking.delivery.ProgramOutputter;

public class UnknownCommand {
    private ProgramOutputter programOutputter;

    public UnknownCommand(ProgramOutputter programOutputter) {
        this.programOutputter = programOutputter;
    }

    public void run() {
        Console out = this.programOutputter.console;
        out.println("Commands are: [search x y hh:mm] book, or list bookings");
        out.println("  search x y hh:mm");
        out.println("  book x y");
        out.println("  list bookings");
        out.println("  list ports");
        out.println();
        out.println("Book is [book x y]");
        out.println("where x - journey id");
        out.println("where y - number of passenger");
        out.println();
        out.println("Search is [search x y hh:mm]");
        out.println("where: x - origin port id");
        out.println("where: y - destinationg port id");
        out.println("where: hh:mm - time to search after");
    }
}
