package ferry.booking.test;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ferry.booking.Program;
import org.apache.commons.io.input.ReaderInputStream;
import org.junit.Test;

public class GoldenMasterTest {

//    @Test
    public void generate_golden_master() {
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

    static class GoldenMasterCase {

        private String caseDescription;

        public GoldenMasterCase(String caseDescription) {
            this.caseDescription = caseDescription;
        }

        private Path output() {
            return Paths.get("src/test/resources/" + this.caseDescription + ".output.txt").toAbsolutePath();
        }

        public Path input() {
            return Paths.get("src/test/resources/" + this.caseDescription + ".input.txt").toAbsolutePath();
        }

        public Path execution() {
            return Paths.get("src/test/resources/" + this.caseDescription + ".execution.txt").toAbsolutePath();
        }

        public Path master() {
            return Paths.get("src/test/resources/" + this.caseDescription + ".master.txt").toAbsolutePath();
        }

        public void acceptResults() {
            try {
                Files.move(execution(), master());
            } catch (IOException e) {
            }
        }

        public void deletePreviousRun() {
            try {
                Files.delete(output());
                Files.delete(master());
            } catch (IOException e) {
            }
        }
    }
}
