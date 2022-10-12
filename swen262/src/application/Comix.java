import controller.App;
import model.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Comix {

    public static void main(String[] args) {

        App comixApp = new App();
        comixApp.init();
        comixApp.run();

    }
}
