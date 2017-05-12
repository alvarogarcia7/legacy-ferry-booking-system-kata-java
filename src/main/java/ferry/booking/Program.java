package ferry.booking;

import ferry.booking.booking.AvailableCrossing;
import ferry.booking.booking.Booking;
import ferry.booking.booking.Bookings;
import ferry.booking.booking.JourneyBookingService;
import ferry.booking.command.BookCommand;
import ferry.booking.command.ListPortsCommand;
import ferry.booking.command.UnknownCommand;
import ferry.booking.delivery.Console;
import ferry.booking.delivery.ProgramOutputter;
import ferry.booking.delivery.TimeTablePrinter;
import ferry.booking.ferry.Ferries;
import ferry.booking.ferry.FerryAvailabilityService;
import ferry.booking.port.Port;
import ferry.booking.port.PortManager;
import ferry.booking.port.Ports;
import ferry.booking.timetable.TimeTableService;
import ferry.booking.timetable.TimeTableViewModelRow;
import ferry.booking.timetable.TimeTables;

import java.io.*;
import java.util.List;

public class Program {

    public static TimeTableService timeTableService;
    private static JourneyBookingService bookingService;
    public static Ports ports;
    private static FerryAvailabilityService ferryService;
    private static Console out;
    private static TimeTablePrinter timeTablePrinter;
    private static ProgramOutputter programOutputter;

    public static void wireUp() {
        TimeTables timeTables = new TimeTables();
        Ferries ferries = new Ferries();
        Bookings bookings = new Bookings();
        ports = new Ports();
        ferryService = new FerryAvailabilityService(timeTables, new PortManager(ports, ferries));
        bookingService = new JourneyBookingService(timeTables, bookings, ferryService);
        timeTableService = new TimeTableService(timeTables, bookings, ferryService);
    }

    public static void main(String[] args) {
        start(System.in, System.out);
    }

    public static void start(InputStream input, PrintStream output) {
        start(output);
        commandLoop(input);
    }

    public static void mainWithTestData(PrintStream ps) {
        start(ps);
        testCommands();
    }

    public static void start(PrintStream ps) {
        out = Console.to(ps);
        timeTablePrinter = TimeTablePrinter.console(out);
        programOutputter = ProgramOutputter.aNew(timeTablePrinter, out);
        wireUp();

        timeTablePrinter.displayWelcomeMessage();

        List<Port> allPorts = ports.all();
        List<TimeTableViewModelRow> timeTable = timeTableService.getTimeTable(allPorts);

        timeTablePrinter.displayTimetable(allPorts, timeTable);
    }

    private static void testCommands() {
        doCommand("help");
        doCommand("list ports");
        doCommand("search 2 3 00:00");
        doCommand("search 2 3 00:00");
        doCommand("book 10 2");
        doCommand("search 2 3 00:00");
        doCommand("book 10 10");
        doCommand("book 10 1");
        doCommand("search 1 2 01:00");
        doCommand("book 4 2");
        doCommand("book 6 8");
        doCommand("search 1 2 01:00");
        doCommand("search 1 3 01:00");
        doCommand("search 1 3 01:30");
        doCommand("book 5 16");
        doCommand("book 16 16");
        doCommand("search 1 3 00:00");
        doCommand("list bookings");
    }

    private static void commandLoop(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            line = br.readLine();
            while (!(line == null || line.toLowerCase().equals("quit"))) {
                doCommand(line);
                line = br.readLine();
            }
        } catch (IOException e) {
        }
    }

    private static void doCommand(String command) {
        if (command.startsWith("search")) {
            search(command);
        } else if (command.startsWith("book")) {
            new BookCommand(programOutputter, bookingService, command).run();
        } else if (command.startsWith("list ports")) {
            new ListPortsCommand(programOutputter, ports).run();
        } else if (command.startsWith("list bookings")) {
            List<Booking> bookings = bookingService.getAllBookings();
            out.println("Bookings:");
            out.println("---------");
            for (Booking b : bookings) {
                out.printf("journey %d - passengers %d", b.journeyId, b.passengers);
            }
            out.println();
        } else {
            new UnknownCommand(programOutputter).run();
        }
    }

    private static void search(String line) {
        try {
            String parts[] = line.split(" ");
            int originPortId = Integer.parseInt(parts[1]);
            int destinationPortId = Integer.parseInt(parts[2]);
            String mins[] = parts[3].split(":");
            long time = Long.parseLong(mins[0]) * 60 + Long.parseLong(mins[1]);

            List<AvailableCrossing> search = timeTableService.getAvailableCrossings(time, originPortId,
                    destinationPortId);

            for (AvailableCrossing result : search) {
                out.printf("[%02d:%02d] %s to %s -  %s (JourneyId : %d, spaces left %d)", result.setOff / 60,
                        result.setOff % 60, result.originPort, result.destinationPort, result.ferryName,
                        result.journeyId, result.seatsLeft);
                out.println();
            }
        } catch (Exception e) {
            out.println("Search is [search x y hh:mm]");
            out.println("where: x - origin port id");
            out.println("where: y - destinationg port id");
            out.println("where: hh:mm - time to search after");
        }
    }
}
