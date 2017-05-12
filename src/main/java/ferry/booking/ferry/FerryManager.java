package ferry.booking.ferry;

import ferry.booking.port.Port;
import ferry.booking.timetable.TimeTableEntry;

import java.util.List;

public class FerryManager {

    public static FerryJourney createFerryJourney(List<Port> ports, TimeTableEntry timetable) {
        if (ports == null) {
            return null;
        }

        if (timetable == null) {
            return null;
        }

        FerryJourney fj = new FerryJourney();
        for (Port port : ports) {
            if (port.id == timetable.originId) {
                fj.origin = port;
            }
            if (port.id == timetable.destinationId) {
                fj.destination = port;
            }
        }
        return fj;
    }

    public static void addFerry(TimeTableEntry timetable, FerryJourney journey) {
        journey.ferry = journey.origin.getNextAvailable(timetable.time);
    }

    public static int getFerryTurnaroundTime(Port destination) {
        if (destination.id == 3) {
            return 25;
        }
        if (destination.id == 2) {
            return 20;
        }
        return 15;
    }
}
