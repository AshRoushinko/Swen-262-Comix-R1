package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class defines a helper object for parsing CSV values
 * It gets returned by the methods that extract values from the CSV file.
 */
public class ParseData {

    //Create local fields to store the index and text
    private int idx;
    private String text;
    //Local field only used for creators
    private ArrayList<String> creators;

    /**
     * CONSTRUCTOR - This is the constructor for the parse info object
     * @param idx - Represents the current index of the line (In other words it is the index after the value taken from the line)
     * @param text - Represents a value taken from the line (This value will be added to a variable for making a comic object)
     */
    public ParseData(int idx, String text){
        this.idx = idx;
        this.text = text;
        this.creators = new ArrayList<>();
    }

    //SETTERS
    //------------------------------------------------------------------------------------------------------------------
    public void addCreator(String creator){
        this.creators.add(creator);
    }

    //GETTERS
    //------------------------------------------------------------------------------------------------------------------
    public String getText() {
        return text;
    }

    public int getIdx() {
        return idx;
    }

    public ArrayList<String> getCreators() {
        return creators;
    }

    //OTHER
    //------------------------------------------------------------------------------------------------------------------
    public void buildCreatorArray(){
        String[] tempCList = this.text.split("\\|\s");
        for (int x = 0; x< tempCList.length; x++){
            addCreator(tempCList[x]);
        }
    }
}
