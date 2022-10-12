package controller;

import model.Comic;
import model.Database;
import model.Series;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Iterator;

public class Command {

    String type;
    String info;
    Collection<Series> db;

    public Command(String type, String info, Collection<Series> db){
        this.type = type;
        this.info = info;
        this.db = db;
    }

    public String run(){

        if (type.equals("1")){
            return searchDB(info);
        }
        return "";
    }

    /**
     * This method handles the search operation.
     * Param - content: includes a string representing a name or partial name of the comic(s) to search for.
     * Return - The Book(s) that were found in the search.
     */
    private String searchDB(String content){
        Iterator<Series> sCollection = db.iterator();
        String show = "";
        while(sCollection.hasNext()){
            Iterator<Comic> cCollection = sCollection.next().getComics().iterator();
            while(cCollection.hasNext()){
                Comic curr = cCollection.next();
                if (curr.getTitle().contains(content)){
                    show = show+"\n"+curr.toString();
                }
            }
        }
        return "S"+show;
    }

    private void addFromDB(){

    }

    private void addFromInput(){

    }

    private void remove(){

    }

    private void edit(){

    }

    private void mark(){

    }

    private void browse(){

    }

    private void storeProfile(){

    }

}
