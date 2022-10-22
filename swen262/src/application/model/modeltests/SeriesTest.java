package model.modeltests;

import model.Comic;
import model.Series;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SeriesTest {
    private Series underTest;
    private ByteArrayOutputStream bout;
    private PrintStream out;

    @Before
    public void runBeforeTests() {
        underTest = new Series("Test Series",new ArrayList<Comic>());
        bout = new ByteArrayOutputStream();
        out = new PrintStream(bout);
        System.setOut(out);
    }
    /**
    @Test
    public void testName() {

    }*/

}
