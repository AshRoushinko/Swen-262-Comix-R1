package model;

import java.util.concurrent.Flow.Publisher;

public class Comic {

    private String publisher, title, issue, publicationDate, creators, characters, description;
    private int volume;
    private double value;

    public Comic(String Publisher, String Title, int Volume, String Issue, String PublicationDate){
        this.publisher = Publisher;
        this.title = Title;
        this.volume = Volume;
        this.issue = Issue;
        this.publicationDate = PublicationDate;
    }

}
