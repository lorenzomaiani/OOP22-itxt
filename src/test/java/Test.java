
import controller.file.TextLoader;
import controller.file.TextSaver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class AppTest {

    private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
            + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            + " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex"
            + " ea commodo consequat. Duis aute irure dolor in reprehenderit in"
            + " voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,"
            + " sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private TextLoader loader = new TextLoader();
    private TextSaver saver = new TextSaver();

    @Test
    public void createAndRemoveAFile() {
        assertTrue(saver.createAFile());
        assertFalse(saver.createAFile());
        assertTrue(saver.removeFile());
        assertFalse(saver.removeFile());
        assertTrue(saver.createAFile());
        assertTrue(saver.removeFile());
    }

    @Test
    public void readFromFile() {
        saver.createAFile();
        assertNull(loader.readFromFile());
        saver.writeOnFile(TEXT);
        assertEquals(TEXT, loader.readFromFile());
        saver.removeFile();
    }

}
