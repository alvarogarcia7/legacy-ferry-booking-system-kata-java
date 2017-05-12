package ferry.booking.command;

import ferry.booking.booking.Booking;
import ferry.booking.booking.JourneyBookingService;
import ferry.booking.delivery.Console;
import ferry.booking.delivery.ProgramOutputter;

public class BookCommand implements Command {
    private final ProgramOutputter programOutputter;
    private final JourneyBookingService bookingService;
    private String line;

    public BookCommand(ProgramOutputter programOutputter, JourneyBookingService bookingService, String line) {
        this.programOutputter = programOutputter;
        this.bookingService = bookingService;
        this.line = line;
    }


    public void run() {
        try {
            String parts[] = line.split(" ");
            int journeyId = Integer.parseInt(parts[1]);
            int passengers = Integer.parseInt(parts[2]);

            if (bookingService.canBook(journeyId, passengers)) {
                Booking booking = new Booking();
                booking.journeyId = journeyId;
                booking.passengers = passengers;
                bookingService.book(booking);

                programOutputter.console.println("Booked");
            } else {
                programOutputter.console.println("Cannot book that journey");
            }
        } catch (Exception e) {
            programOutputter.console.println("Book is [book x y]");
            programOutputter.console.println("where x - journey id");
            programOutputter.console.println("where y - number of passenger");
        }

    }
}
