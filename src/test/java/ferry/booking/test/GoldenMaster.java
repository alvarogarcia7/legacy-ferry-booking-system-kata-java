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
