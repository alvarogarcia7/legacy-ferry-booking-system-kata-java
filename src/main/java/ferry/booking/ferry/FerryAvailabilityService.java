package ferry.booking.ferry;

import ferry.booking.port.Port;
import ferry.booking.port.PortManager;
import ferry.booking.timetable.TimeTable;
import ferry.booking.timetable.TimeTableEntry;
import ferry.booking.timetable.TimeTables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FerryAvailabilityService {

    private final TimeTables timeTables;
    private final PortManager portManager;

    public FerryAvailabilityService(TimeTables timeTables, PortManager portManager) {
        this.timeTables = timeTables;
        this.portManager = portManager;
    }

    private static void boatReady(TimeTableEntry timetable, Port destination, FerryJourney ferryJourney) {
        if (ferryJourney.ferry == null) {
            FerryManager.addFerry(timetable, ferryJourney);
        }
        Ferry ferry = ferryJourney.ferry;

        long time = FerryModule.timeReady(timetable, destination);
        destination.addBoat(time, ferry);
    }

    public Ferry nextFerryAvailableFrom(int portId, long time) {
        List<Port> ports = portManager.PortModels();
        List<TimeTableEntry> allEntries = new ArrayList<>();
        for (TimeTable tt : timeTables.all()) {
            allEntries.addAll(tt.entries);
        }
        Collections.sort(allEntries, new Comparator<TimeTableEntry>() {

            @Override
            public int compare(TimeTableEntry tte1, TimeTableEntry tte2) {
                return Long.compare(tte1.time, tte2.time);
            }
        });

        for (TimeTableEntry entry : allEntries) {
            FerryJourney ferry = FerryManager.createFerryJourney(ports, entry);
            if (ferry != null) {
                boatReady(entry, ferry.destination, ferry);
            }
            if (entry.originId == portId) {
                if (entry.time >= time) {
                    if (ferry != null) {
                        return ferry.ferry;
                    }
                }
            }
        }

        return null;
    }
}
