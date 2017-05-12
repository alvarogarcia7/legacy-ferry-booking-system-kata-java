package ferry.booking.command;

import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Stream;

public class Commands {
    public static Stream<Class<? extends Command>> all(String commandDescription) {
        Set<Class<? extends Command>> commands = new Reflections().getSubTypesOf(Command.class);
        Stream<Class<? extends Command>> commandClasses = commands.stream();
        return commandClasses;
    }

//    {
//        Command command = new UnknownCommand(out);
//        if (commandDescription.startsWith("search")) {
//            command = new SearchCommand(commandDescription, timeTableService, out);
//        } else if (commandDescription.startsWith("book")) {
//            command = new BookCommand(commandDescription, bookingService, out);
//        } else if (commandDescription.startsWith("list ports")) {
//            command = new ListPortsCommand(ports, out);
//        } else if (commandDescription.startsWith("list bookings")) {
//            command = new ListBookingsCommand(bookingService, out);
//        }
//        command.run();
//    }
}
