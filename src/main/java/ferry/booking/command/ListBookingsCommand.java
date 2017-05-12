package ferry.booking.command;

import ferry.booking.booking.Booking;
import ferry.booking.booking.JourneyBookingService;
import ferry.booking.delivery.Console;
import ferry.booking.delivery.ProgramOutputter;

import java.util.List;


public class ListBookingsCommand {
    private final ProgramOutputter programOutputter;
    private final JourneyBookingService bookingService;

    public ListBookingsCommand(ProgramOutputter programOutputter, JourneyBookingService bookingService) {
        this.programOutputter = programOutputter;
        this.bookingService = bookingService;
    }

    public void run() {
        List<Booking> bookings = bookingService.getAllBookings();
        programOutputter.console.println("Bookings:");
        programOutputter.console.println("---------");
        for (Booking b : bookings) {
            programOutputter.console.printf("journey %d - passengers %d", b.journeyId, b.passengers);
        }
        programOutputter.console.println();
    }
}
