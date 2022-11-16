package model;

import java.util.ArrayList;
import java.util.Iterator;
//Purpose - Represents a Comic Object
public class Comic {
    //------------------------------------------------------------------------------------------------------------------
    private String series, issue, title, description, releaseDate, format, addDate, publisher, id;
    private ArrayList<String> creators;
    private double value;
    private int signatures;
    private int authenticatedsignatures;
    private int gradeValue;
    //------------------------------------------------------------------------------------------------------------------
    private Boolean isGraded;
    private Boolean isSlabbed;
    private Boolean isSigned;
    private Boolean isAuthenticated;
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
        isSigned = false;
        isAuthenticated = false;
        signatures = 0;
        authenticatedsignatures = 0;
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

    public void setIsGraded(){
        isGraded = true;
    }
    public void setIsSigned(){
        isSigned = true;
    }
    public void setIsSlabbed(){
        isGraded = true;
    }
    public void setIsAuthenticated(){
        isAuthenticated = true;
    }
    public void setGradeValue(int d){
        gradeValue = d;
    }
    public void setNumSigned(int num){
        this.signatures = num;
    }
    public void setNumAuthenticated(int num){
        this.authenticatedsignatures = num;
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

    public String getCreatorsParseString(){
        String returnString = "";
        for (int i = 0; i < creators.size()-1; i++){
            returnString = returnString+creators.get(i)+"|";
        }
        returnString = returnString+creators.get(creators.size()-1);
        return returnString;
    }
    public String getGradedValue(){
        return this.gradeValue+"";
    }
    public String getSignedNum(){
        return ""+signatures;
    }
    public String getAuthenticatedNum(){
        return ""+authenticatedsignatures;
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

    //OTHER METHODS FOR MARK COMMAND--------------------------------
    public Boolean isGraded(){
        return isGraded;
    }
    public Boolean isSlabbed(){
        return isSlabbed;
    }
    public Boolean isSigned(){
        return isSigned;
    }
    public Boolean isAuthenticated(){
        return isAuthenticated;
    }
    public Boolean grade(String num){
        if (isGraded()){
            return false;
        }
        gradeValue = Integer.parseInt(num);
        value = 0.1*gradeValue;
        isGraded = true;
        if (isSigned()) {
            for (int x = 0; x < signatures; x++){
                value = value*1.05;
            }
        }
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
    public Boolean sign(){
        isSigned = true;
        signatures++;
        if(isGraded()){
            value = value*1.05;
        }
        return true;
    }
    public Boolean authenticate(){//TODO make sure the comic is already signed
        if (isSigned()){
            if (isGraded()){
                if (signatures>authenticatedsignatures){
                    authenticatedsignatures++;
                    isAuthenticated = true;
                    value = value*1.2;
                    return true;
                }
                return false;
            }
            else{
                if (signatures>authenticatedsignatures){
                    authenticatedsignatures++;
                    isAuthenticated = true;
                    return true;
                }
                return false;
            }
        }
        else{
            return false;
        }
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
    public Boolean unsign(){
        if (isSigned()){
            if (signatures==1){
                isSigned = false;
            }
            if (isAuthenticated()){
                if (authenticatedsignatures==signatures){
                    if (authenticatedsignatures==1){
                        isAuthenticated = false;
                    }
                    authenticatedsignatures--;
                    if (isGraded){
                        value = value/1.20;
                    }
                }
            }
            else{
                signatures--;
                if (isGraded){
                    value = value/1.05;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean unAuthenticate(){
        if (isAuthenticated()){
            if (authenticatedsignatures==1){
                isAuthenticated = false;
            }
            else{

            }
            authenticatedsignatures--;
            value = value/1.2;
            return true;
        }
        else{
            return false;
        }
    }
}
