package ferry.booking.test;

import static ferry.booking.test.GoldenMaster.execute;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import ferry.booking.Program;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;

public class GoldenMasterTest {

    @Test
    public void compare_to_golden_master() throws IOException {

        GoldenMasterCase goldenMasterCase = new GoldenMasterCase("master");

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
