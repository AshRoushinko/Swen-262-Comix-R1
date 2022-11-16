package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.w3c.dom.*;
import com.opencsv.CSVWriter;
import model.Comic;
import model.ComixDatabase;
import model.Series;
import model.User;
import view.Result;
import view.SearchResult;
import view.StoreResult;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.*;

//Purpose - A command that allows the user to store their collection in a file of their choice
public class StoreProfile extends Command{
    //------------------------------------------------------------------------------------------------------------------
    private Collection<Comic> storeResult;
    private String resultString;
    //------------------------------------------------------------------------------------------------------------------
    public StoreProfile(CommandType type, String info, ComixDatabase db, User uc) {
        super(type, info, db, uc);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public void init(CommandType commandType, String info, ComixDatabase db, User uc) {

    }
    //------------------------------------------------------------------------------------------------------------------
    //RESULT METHODS
    @Override
    public Collection<Comic> run() throws IOException {
        File file = new File(info);
        if (commandType==CommandType.STOREPROFILESELECTCSV){
            FileWriter csvOutput = new FileWriter(file);
            CSVWriter writer = new CSVWriter(csvOutput);
            String[] header = {"ID","SERIES","ISSUE","TITLE","DESCRIPTION","PUBLISHER","RELEASEDATE","FORMAT","ADDDATE","CREATORS","VALUE","GRADED","GRADEDVALUE","SLABBED","SIGNED","NUMSIGNED","AUTHENTICATED","NUMAUTHENTICATED"};
            writer.writeNext(header);

            Iterator<Series> seriesIterator = uc.getComiccollection().iterator();
            while(seriesIterator.hasNext()){
                Iterator<Comic> comicIterator = seriesIterator.next().getComics().iterator();
                while(comicIterator.hasNext()){
                    Comic currComic = comicIterator.next();
                    String[] comicData = {currComic.getID(),currComic.getSeries(),currComic.getIssue(),currComic.getTitle(),currComic.getDescription(),currComic.getPublisher(),currComic.getReleaseDate(),currComic.getFormat(),currComic.getAddDate(),currComic.getCreatorsParseString(),currComic.getValue()+"",currComic.isGraded()+"",currComic.getGradedValue()+"",currComic.isSlabbed()+"",currComic.isSigned()+"",currComic.getSignedNum(),currComic.isAuthenticated()+"",currComic.getAuthenticatedNum()};
                    writer.writeNext(comicData);
                }
            }
            writer.close();
        }
        else if (commandType==CommandType.STOREPROFILESELECTXML){
            OutputStream os = null;
            os = new FileOutputStream(file);
            Iterator<Series> seriesIterator = uc.getComiccollection().iterator();
            while(seriesIterator.hasNext()){
                Iterator<Comic> comicIterator = seriesIterator.next().getComics().iterator();
                while(comicIterator.hasNext()){
                    Comic currComic = comicIterator.next();
                    String[] comicData = {currComic.getID(),currComic.getSeries(),currComic.getIssue(),currComic.getTitle(),currComic.getDescription(),currComic.getPublisher(),currComic.getReleaseDate(),currComic.getFormat(),currComic.getAddDate(),currComic.getCreatorsParseString(),currComic.getValue()+"",currComic.isGraded()+"",currComic.getGradedValue()+"",currComic.isSlabbed()+"",currComic.isSigned()+"",currComic.getSignedNum(),currComic.isAuthenticated()+"",currComic.getAuthenticatedNum()};
                    Properties comicProp = new Properties();
                    comicProp.setProperty("ID",comicData[0]);
                    comicProp.setProperty("SERIES",comicData[1]);
                    comicProp.setProperty("ISSUE",comicData[2]);
                    comicProp.setProperty("TITLE",comicData[3]);
                    comicProp.setProperty("DESCRIPTION",comicData[4]);
                    comicProp.setProperty("PUBLISHER",comicData[5]);
                    comicProp.setProperty("RELEASEDATE",comicData[6]);
                    comicProp.setProperty("FORMAT",comicData[7]);
                    comicProp.setProperty("ADDDATE",comicData[8]);
                    comicProp.setProperty("CREATORS",comicData[9]);
                    comicProp.setProperty("VALUE",comicData[10]);
                    comicProp.setProperty("GRADED",comicData[11]);
                    comicProp.setProperty("GRADEDVALUE",comicData[12]);
                    comicProp.setProperty("SLABBED",comicData[13]);
                    comicProp.setProperty("SIGNED",comicData[14]);
                    comicProp.setProperty("SIGNEDNUM",comicData[15]);
                    comicProp.setProperty("AUTHENTICATED",comicData[16]);
                    comicProp.setProperty("AUTHENTICATEDNUM",comicData[17]);
                    try {
                        comicProp.storeToXML(os, "Dynamic Property File");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{

        }

        Result storeResultVisitor = new StoreResult();
        setResultString(getResult(storeResultVisitor));
        return storeResult;
    }

    @Override
    public Collection<Comic> getCollection() {
        return storeResult;
    }

    @Override
    public String getResult(Result result) {
        return result.visit(this);
    }

    @Override
    public String undo() {
        return null;
    }

    @Override
    public void setResultString(String s) {
        resultString = s;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
