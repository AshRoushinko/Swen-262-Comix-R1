package model.parsefiles;

public class ParseSeries implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseIssue());
    }

    /**
     * Purpose - To get the Series name from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the series and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String seriesGet;//Creates an empty string that will be initialized with the series title and returned
        if(line.startsWith("\"")){//Check to see if the series has quotations
            //Find the index of the next quotation mark (This should be the end of the series title)
            int seriesEnd = line.substring(1).indexOf("\"")+1;
            //Create a new string that contains the series title (The text in between the first set of parenthesis)
            seriesGet = line.substring(1,seriesEnd);
            //Add the length of the series to the idx
            idx+=seriesGet.length()+2;//The plus 2 is for the parenthesis
        }
        else{
            //Find the index of the first comma which should mark the end of the series title
            int seriesEnd = line.indexOf(",");
            //Create a new string that contains the series title (The text before the first comma)
            seriesGet = line.substring(0,seriesEnd);
            //Add the length of the series to the idx
            idx+=seriesGet.length();
        }
        return new ParseData(idx, seriesGet);//Return a parse info object containing the parse information for the series
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
