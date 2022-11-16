package model;
//Purpose - An Interface that defines all necessary functionality for database type objects (User and Comix Database)
public interface Database {

    public void sort();//FOR BROWSING DATABASE

    public void parse(String filename);//Parses the Database

    public void display();//Displays Every Comic in the database

    public Series getSeries(String name);//Returns a Series object that matches the name provided

    public Comic getComic(String id);//Returns a comic object that matches the id provided

}
