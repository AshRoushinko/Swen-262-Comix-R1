package model;

public interface Database {

    public void sort(String filters);//FOR BROWSING DATABASE

    public void parse(String filename);

    public void display();

    public Series getSeries(String name);

    public Comic getComic(int id);

}
