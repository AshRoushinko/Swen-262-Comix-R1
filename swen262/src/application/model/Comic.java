package model;

public class Comic {

    private String series, title, description, publicationDate, format, addDate, publisher;
    private String[] creators;
    private String issue;
    private String volume;
    private double value;

    private Boolean isGraded;
    private Boolean isSlabbed;

    public Comic(String series, String title, String issue, String volume, String publicationDate){
        this.series = series;
        this.title = title;
        this.issue = issue;
        this.volume = volume;
        this.publicationDate = publicationDate;
        isGraded = false;
        isSlabbed = false;
    }

    //SETTERS


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

    public void setCreators(String[] creators) {
        this.creators = creators;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    //GETTERS --------------------------------
    public double getValue() {
        return value;
    }

    public String getAddDate() {
        return addDate;
    }

    public String[] getCreators() {
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

    public String getVolume() {
        return volume;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getSeries() {
        return series;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString(){
        return "Series: "+this.series+", Volume: "+this.volume+", Issue: "+this.issue+", Title: "+this.title+", Description: "+this.description+", Publisher: "+this.publisher+", Publication Date: "+this.publicationDate+", Format: "+this.format+", Add Date: "+this.addDate+", Creators: "+this.creators;
    }
}
