package model;

import java.util.ArrayList;
import java.util.Collection;

public class Database {

    private Collection<Series> comiccollection;

    public Database(String path){
        init(path);
    }

    private void init(String path) {

        //TODO parse the database file into a collection of series. A collection of series is a collection of Comicbooks with a series name
        this.comiccollection = new ArrayList<>();

        Comic c1 = new Comic("Mazing Man", "Manone", "test1", "issue1", "date1");
        Comic c2 = new Comic("Mazing Man", "NotMan", "test2", "issue2", "date2");
        Comic c3 = new Comic("Mazing Man", "YellowEagle", "test3", "issue3", "date3");
        Collection<Comic> cc1 = new ArrayList<>();
        cc1.add(c1);
        cc1.add(c2);
        cc1.add(c3);
        Series s1 = new Series("Mazing Man", cc1);

        Comic c4 = new Comic("Other", "Green", "test1", "issue1", "date1");
        Comic c5 = new Comic("Other", "Yellow", "test2", "issue2", "date2");
        Comic c6 = new Comic("Other", "GreenBlue", "test3", "issue3", "date3");
        Collection<Comic> cc2 = new ArrayList<>();
        cc2.add(c4);
        cc2.add(c5);
        cc2.add(c6);
        Series s2 = new Series("Other", cc2);
        this.comiccollection.add(s1);
        this.comiccollection.add(s2);
    }

    public Collection<Series> getComiccollection() {
        return comiccollection;
    }

    /**
        int linenum = 0;

        String line = "";
        String currentSeriesName = "";
        Collection<Comic> currentSeries = null;
        int lineIdx = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));

            while((line=br.readLine())!=null){
                if (linenum<3){
                    linenum+=1;
                }
                else{
                    String seriesTitleget = getSeriesTitle(line);// Gets seriesTitle
                    String[] seriesTitlegetSplit = seriesTitleget.split("::");
                    String seriesTitle = seriesTitlegetSplit[0];
                    System.out.println(seriesTitlegetSplit[0]+"  :  "+seriesTitlegetSplit[1]);
                    lineIdx += Integer.parseInt(seriesTitlegetSplit[1])+1;
                    String nLine = line.substring(lineIdx);
                    String[] values = nLine.split(",");
                    //System.out.println(values[0]+"   :   "+lineIdx);
                    for (int x = 0; x<values.length; x++){
                        System.out.println(values[x]+" place: "+x);
                    }
                    String issue = values[0];
                    lineIdx += issue.length()-1;
                    String title = getTitle(values[1]);
                    lineIdx += title.length()-1;
                    String description = values[2].replaceAll("\'","");
                    lineIdx += description.length()-1;
                    String publisher = values[3].replaceAll("\'","");
                    lineIdx += publisher.length()-1;
                    String releaseDateT = getDate(nLine, lineIdx);
                    //String releaseDate = values[4].replaceAll("\'","")+", "+values[5].replaceAll("\'","");
                    //String format = values[6].replaceAll("\'","");
                    //String addDate = values[7].replaceAll("\'","")+", "+values[8].replaceAll("\'","");;
                    //String iCreators = values[9].replaceAll("\'","");
                    //String[] creators = iCreators.split("\\|");
                    lineIdx = 0;
                    System.out.println(releaseDateT+"  TEST");
                    if (values[0].equals(currentSeriesName)){

                    }
                    else{
                        if (currentSeries==null){
                            currentSeriesName = values[0];
                            //currentSeries.add(new Comic());
                        }
                        else{
                            this.comiccollection.add(new Series(currentSeriesName, currentSeries));
                        }
                        currentSeriesName = values[0];
                    }
                    System.out.println(line);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (DateTimeParseException e){
            e.printStackTrace();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    private String getSeriesTitle(String line){
        if (line.charAt(0) == '"'){
            String eLine = line.substring(1);
            int titleIdx = eLine.indexOf("\"");
            return eLine.substring(0,titleIdx)+"::"+titleIdx;
        }
        else{
            String eLine = line;
            int titleIdx = eLine.indexOf(",");
            return line.substring(0,titleIdx)+"::"+titleIdx;
        }
    }

    private String getTitle(String s){
        if (s.equals("")){
            return s;
        }
        else{
            //System.out.println(s);
            if (s.charAt(0) == '"'){
                return s.substring(1);
            }
            else{
                return s;
            }
        }
    }

    private String getDate(String s, int idx){
        String s1 = s.substring(idx);
        System.out.println(s1);
        int i = s1.indexOf('"');
        return s1.substring(0,i);
    }**/

}



