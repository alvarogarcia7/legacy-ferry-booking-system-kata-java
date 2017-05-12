package ferry.booking.command;

import ferry.booking.booking.AvailableCrossing;
import ferry.booking.delivery.Console;
import ferry.booking.delivery.ProgramOutputter;
import ferry.booking.timetable.TimeTableService;

import java.util.List;

public class SearchCommand implements Command {
    private final ProgramOutputter programOutputter;
    private final TimeTableService timeTableService;
    private final String command;

    public SearchCommand(ProgramOutputter programOutputter, TimeTableService timeTableService, String command) {
        this.programOutputter = programOutputter;
        this.timeTableService = timeTableService;
        this.command = command;
    }

    public void run() {
        try {
            String parts[] = command.split(" ");
            int originPortId = Integer.parseInt(parts[1]);
            int destinationPortId = Integer.parseInt(parts[2]);
            String mins[] = parts[3].split(":");
            long time = Long.parseLong(mins[0]) * 60 + Long.parseLong(mins[1]);

            List<AvailableCrossing> search = timeTableService.getAvailableCrossings(time, originPortId,
                    destinationPortId);

            for (AvailableCrossing result : search) {
                programOutputter.console.printf("[%02d:%02d] %s to %s -  %s (JourneyId : %d, spaces left %d)", result.setOff / 60,
                        result.setOff % 60, result.originPort, result.destinationPort, result.ferryName,
                        result.journeyId, result.seatsLeft);
                programOutputter.console.println();
            }
        } catch (Exception e) {
            programOutputter.console.println("Search is [search x y hh:mm]");
            programOutputter.console.println("where: x - origin port id");
            programOutputter.console.println("where: y - destinationg port id");
            programOutputter.console.println("where: hh:mm - time to search after");

        }
    }
}
