package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Comic {

    private String series, issue, title, description, releaseDate, format, addDate, publisher, id;
    private ArrayList<String> creators;
    private double value;

    private Boolean isGraded;
    private Boolean isSlabbed;

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
        //TODO implement value
        this.value = 0.0;
        isGraded = false;
        isSlabbed = false;
    }

    //SETTERS-----------------------------------------------------
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
    public String toString(){//TODO Make this look better
        return "ISSUE: "+this.issue+"\nTITLE: "+this.title+"\nDESCRIPTION: "+this.description+"" +
                "\nPUBLISHER: "+this.publisher+"\nRELEASE DATE: "+this.releaseDate+"\nFORMAT: "+this.format+"" +
                "\nADD DATE: "+this.addDate+"\nVALUE: "+this.value+"\n------CREATORS------"+creatorsToString()+"\n--------------------\n";
    }
}
