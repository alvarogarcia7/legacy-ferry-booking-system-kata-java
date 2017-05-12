package ferry.booking.command;

import ferry.booking.booking.AvailableCrossing;
import ferry.booking.delivery.ProgramOutputter;
import ferry.booking.timetable.TimeTableService;

import java.util.ArrayList;
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
                List<String> message = new ArrayList<>();
                message.add(String.format("[%02d:%02d] %s to %s -  %s (JourneyId : %d, spaces left %d)", result.setOff / 60,
                        result.setOff % 60, result.originPort, result.destinationPort, result.ferryName,
                        result.journeyId, result.seatsLeft));
                programOutputter.console.display(message.toArray(new String[0]));
            }
        } catch (Exception e) {
            List<String> message = new ArrayList<>();

            message.add("Search is [search x y hh:mm]");
            message.add("where: x - origin port id");
            message.add("where: y - destinationg port id");
            message.add("where: hh:mm - time to search after");
            programOutputter.console.display(message.toArray(new String[0]));
        }
    }
}
