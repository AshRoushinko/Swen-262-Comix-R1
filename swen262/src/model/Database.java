import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Database {
    String series, storytitle, publisher, creator;
        String issuenumber;
        String releasedate;
        public Database(String Series, String Storytitle, String Publisher,String Creator, String Issuenumber, String Releasedate){
            this.series = Series;
            this.storytitle = Storytitle;
            this.publisher = Publisher;
            this.creator = Creator;
            this.issuenumber = Issuenumber;
            this.releasedate = Releasedate;
        }
    public static void main(String[] args) {
        Database db = new Database(null, null, null, null, null, null);
        String path = "swen262/comics.csv";
        String line = "";
    try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine()) !=null){
            String[] values = line.split(",");
            db.series = values[0];
            db.issuenumber = values[1];
            db.storytitle = values[2];
            db.publisher = values[4];
            db.releasedate = values[5];
            System.out.println(db.series + " " + db.issuenumber);
            db.creator = values[8];
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (IOException e){
        e.printStackTrace();
    }
    catch (DateTimeParseException e){
        e.printStackTrace();
    }
    catch(NullPointerException e){
        e.printStackTrace();
    }
    catch(IllegalArgumentException e){
        e.printStackTrace();
    }   
}

}

