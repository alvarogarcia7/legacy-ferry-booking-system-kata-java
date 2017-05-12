package ferry.booking.test;

import static ferry.booking.test.GoldenMaster.execute;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class GoldenMasterTest {

    @Test
    public void compare_case_master_to_golden_master() throws IOException {
        executeCase(new GoldenMasterCase("master"));
    }

    @Test
    public void compare_case_availability_to_golden_master() throws IOException {
        executeCase(new GoldenMasterCase("availability"));
    }

    private void executeCase(GoldenMasterCase goldenMasterCase) throws IOException {
        execute(goldenMasterCase.input(), goldenMasterCase.execution());
        String master = readFile(goldenMasterCase.master());
        String tests = readFile(goldenMasterCase.execution());
        assertEquals(tests, master);
    }

    private static String readFile(Path path) throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, Charset.defaultCharset());
    }

}
