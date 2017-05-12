package ferry.booking.delivery;

import ferry.booking.delivery.adapter.Console;
import ferry.booking.port.Port;
import ferry.booking.timetable.TimeTableViewModelRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimeTablePrinter {
    private Console console;
    private Console out;

    public static TimeTablePrinter console(Console console) {
        return new TimeTablePrinter(console);
    }

    public TimeTablePrinter(Console console) {
        this.console = console;
        this.out = this.console;
    }

    public void displayTimetable(List<Port> ports, List<TimeTableViewModelRow> rows) {
        for (Port port : ports) {
            printPortHeader(port.name);
            List<TimeTableViewModelRow> items = new ArrayList<TimeTableViewModelRow>();
            for (TimeTableViewModelRow x : rows) {
                if (x.originPort.equals(port.name)) {
                    items.add(x);
                }
            }
            Collections.sort(items, new Comparator<TimeTableViewModelRow>() {

                @Override
                public int compare(TimeTableViewModelRow tt1, TimeTableViewModelRow tt2) {
                    return tt1.startTime.compareTo(tt2.startTime);
                }
            });

            for (TimeTableViewModelRow item : items) {
                out.printf("| %-8s | %-13s | %-13s | %-18s | %-8s |", item.startTime, item.destinationPort,
                        item.journeyLength, item.ferryName, item.arrivalTime);
                out.println();
            }
        }
    }

    private void printPortHeader(String portName) {
        this.console.println();
        this.console.println("Departures from " + portName);
        this.console.println();
        this.console.println(" --------------------------------------------------------------------------");
        this.console.printf("| %-8s | %-13s | %-13s | %-18s | %-8s |", "Time", "Destination", "Journey Time", "Ferry",
                "Arrives");
        this.console.println();
        this.console.println(" --------------------------------------------------------------------------");
    }

    public void displayWelcomeMessage() {
        this.console.println("Welcome to the Ferry Finding System");
        this.console.println("=======");
        this.console.println("Ferry Time Table");
    }
}
