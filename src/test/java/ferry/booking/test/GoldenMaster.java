package ferry.booking.test;

import ferry.booking.Program;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class GoldenMaster {

    @Test
    public void creation() {
        GoldenMasterCase master = new GoldenMasterCase("master");
        master.deletePreviousRun();
        execute(master.input(), master.master());
        master.acceptResults();
    }

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

}
