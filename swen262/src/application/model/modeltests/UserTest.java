package model.modeltests;

import model.User;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private User underTest;
    private ByteArrayOutputStream bout;
    private PrintStream out;

    @Before
    public void runBeforeTests() {
        underTest = new User();
        bout = new ByteArrayOutputStream();
        out = new PrintStream(bout);
        System.setOut(out);
    }

    @Test
    public void getComicTest() {

    }
    @Test
    public void getSeriesTest() {

    }
}
