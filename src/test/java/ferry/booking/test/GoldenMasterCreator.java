package ferry.booking.test;

import org.junit.Test;

import static ferry.booking.test.GoldenMaster.execute;

public class GoldenMasterCreator {

    @Test
    public void creation() {
        GoldenMasterCase master = new GoldenMasterCase("master");
        master.deletePreviousRun();
        execute(master.input(), master.master());
        master.acceptResults();
    }

}
