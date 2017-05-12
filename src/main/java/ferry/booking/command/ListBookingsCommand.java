package ferry.booking.command;

import ferry.booking.booking.Booking;
import ferry.booking.booking.JourneyBookingService;
import ferry.booking.delivery.ProgramOutputter;

import java.util.ArrayList;
import java.util.List;


public class ListBookingsCommand implements Command {
    private final ProgramOutputter programOutputter;
    private final JourneyBookingService bookingService;

    public ListBookingsCommand(ProgramOutputter programOutputter, JourneyBookingService bookingService) {
        this.programOutputter = programOutputter;
        this.bookingService = bookingService;
    }

    public void run() {
        List<Booking> bookings = bookingService.getAllBookings();

        List<String> message = new ArrayList<>();
        message.add("Bookings:");
        message.add("---------");
        StringBuilder stringBuilder = new StringBuilder();
        for (Booking b : bookings) {
            stringBuilder.append(String.format("journey %d - passengers %d", b.journeyId, b.passengers));
        }
        message.add(stringBuilder.toString());
        programOutputter.console.display(message.toArray(new String[0]));
    }
}
