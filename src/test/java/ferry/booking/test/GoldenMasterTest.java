package ferry.booking.test;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import ferry.booking.Program;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;

public class GoldenMasterTest {

    private static void execute(Path input, Path output) {
        PrintStream ps = null;
        try {
            File file = new File(output.toString());
            file.createNewFile();
            ps = new PrintStream(file);
            Program.start(readFile2(input), ps);
        } catch (IOException e) {
        } finally {
            ps.close();
        }
    }

    private static InputStream readFile2(Path file) {
        FileReader reader = null;
        try {
            reader = new FileReader(file.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new ReaderInputStream(reader);
    }

    private static String readFile(Path path) throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        return new String(encoded, Charset.defaultCharset());
    }

    @Test
    public void compare_to_golden_master() throws IOException {

        GoldenMasterCase goldenMasterCase = new GoldenMasterCase("master");

        execute(goldenMasterCase.input(), goldenMasterCase.execution());
        String master = readFile(goldenMasterCase.master());
        String tests = readFile(goldenMasterCase.execution());
        assertEquals(tests, master);
    }

}
