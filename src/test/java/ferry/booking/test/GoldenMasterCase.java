package ferry.booking.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by alvaro on 12/05/17.
 */
class GoldenMasterCase {

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
