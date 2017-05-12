package ferry.booking.command;

import ferry.booking.delivery.ProgramOutputter;
import ferry.booking.port.Ports;

public class ListPortsCommand implements Command {
    private ProgramOutputter outputter;
    private Ports ports;

    public ListPortsCommand(ProgramOutputter outputter, Ports ports) {
        this.outputter = outputter;
        this.ports = ports;
    }

    public void run() {
        outputter.printPorts(ports.all());
    }
}
