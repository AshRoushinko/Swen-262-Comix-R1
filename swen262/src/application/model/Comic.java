package model;

import java.util.ArrayList;
import java.util.Iterator;
//Purpose - Represents a Comic Object
public class Comic {
    //------------------------------------------------------------------------------------------------------------------
    private String series, issue, title, description, releaseDate, format, addDate, publisher, id;
    private ArrayList<String> creators;
    private double value;
    //------------------------------------------------------------------------------------------------------------------
    private Boolean isGraded;
    private Boolean isSlabbed;
    //------------------------------------------------------------------------------------------------------------------
    //Constructor
    /**
     * @param id - A string representing the id of the comic
     * @param series - A string representing the series the comic is in
     * @param issue - A string representing the issue number of the comic
     * @param title - A string representing the title of the comic
     * @param description - A string representing the description of the comic
     * @param publisher - A string representing the publisher of the comic
     * @param releaseDate - A string representing the release date of the comic
     * @param format - A string representing the format of the comic
     * @param addDate - A string representing the add date of the comic
     * @param creators - An Arraylist of strings representing the creator(s) of the comic
     */
    public Comic(String id, String series, String issue, String title, String description,
                 String publisher, String releaseDate, String format, String addDate, ArrayList<String> creators){
        this.id = id;
        this.series = series;
        this.issue = issue;
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.format = format;
        this.addDate = addDate;
        this.creators = creators;
        this.value = 0.0;
        isGraded = false;
        isSlabbed = false;
    }
    //SETTERS-----------------------------------------------------
    public void setSeries(String series){
        this.series = series;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreators(ArrayList<String> creators) {
        this.creators = creators;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setReleaseDate(String publicationDate) {
        this.releaseDate = publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    //GETTERS --------------------------------
    public String getID(){
        return id;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getValue() {
        return value;
    }

    public String getAddDate() {
        return addDate;
    }

    public ArrayList<String> getCreators() {
        return creators;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    public String getIssue() {
        return issue;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getSeries() {
        return series;
    }

    public String getTitle() {
        return title;
    }

    //VIEW METHODS ---------------------------------
    public String creatorsToString(){
        String cString = "";

        int x = 1;//Creator number

        Iterator<String> cIter = creators.iterator();

        while(cIter.hasNext()){
            cString = cString+"\n"+x+":"+cIter.next();
            x+=1;
        }
        return cString;
    }
    @Override
    public String toString(){
        String returnString = "--------------------\nCOMIC ID: "+this.id+"\nSERIES: "+this.series+"\nISSUE: "+this.issue+"\nTITLE: "+this.title+"\nDESCRIPTION: "+this.description+"" +
                "\nPUBLISHER: "+this.publisher+"\nRELEASE DATE: "+this.releaseDate+"\nFORMAT: "+this.format+"" +
                "\nADD DATE: "+this.addDate+"\n";
        if (value==0.0){
            returnString = returnString+"VALUE: No Value\n";
        }
        else{
            returnString = returnString+"VALUE: "+this.value+"\n";
        }
        returnString = returnString+"------CREATORS------"+creatorsToString()+"\n--------------------\n====================\n";
        return returnString;
    }

    //OTHER METHODS --------------------------------
    private Boolean isGraded(){
        return isGraded;
    }
    private Boolean isSlabbed(){
        return isSlabbed;
    }
    public Boolean grade(String num){
        if (isGraded()){
            return false;
        }
        int gradeValue = Integer.parseInt(num);
        value = 0.1*gradeValue;
        isGraded = true;
        return true;
    }
    public Boolean slab(){
        if (isSlabbed()){
            return false;
        }
        else{
            if (isGraded()){
                value = value*2;
                isSlabbed = true;
                return true;
            }
            else{
                return false;
            }
        }
    }
    public Comic copy(){
        Comic copy = new Comic(id,series,issue,title,description,publisher,releaseDate,addDate,format,creators);
        if (isGraded()){
            copy.isGraded = true;
        }
        if (isSlabbed()){
            copy.isSlabbed = true;
        }
        return copy;
    }
    public Boolean ungrade(){
        isGraded = false;
        value = 0.0;
        return true;
    }
    public Boolean unslabb(){
        isSlabbed = false;
        value = value/2.0;
        return true;
    }
}
