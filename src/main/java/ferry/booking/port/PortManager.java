package ferry.booking.port;


import ferry.booking.ferry.Ferries;
import ferry.booking.ferry.Ferry;

import java.util.ArrayList;
import java.util.List;

public class PortManager {

    private final Ports ports;
    private final Ferries ferries;

    public PortManager(Ports ports, Ferries ferries) {
        this.ports = ports;
        this.ferries = ferries;
    }

    public List<Port> portModels() {
        for (Ferry ferry : ferries.all()) {
            for (Port port : ports.all()) {
                if (port.id == ferry.homePortId) {
                    port.addBoat(10, ferry);
                }
            }
        }
        return ports.all();
    }
}
