package model.parsefiles;

public class ParseReleaseDate implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseFormat());
    }

    /**
     * Purpose - to get the ReleaseDate from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the ReleaseDate and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String releaseGet;//Creates an empty string that will be initialized with the release date and returned
        int releaseEnd;//Declares the integer that will represent the index following the end of the release date.
        if (line.startsWith("\"")){//Check to see if it starts with a quote
            releaseEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the release date value
            releaseGet = line.substring(1,releaseEnd);//Set the release date string (Starting value is one to remove the quoteation mark)
            idx+=releaseEnd+2;//Get the index after the release date value (add two to remove the closing quotation mark and the comma)
        }
        else if (line.startsWith(",")){//Check to see if there is no release date value
            releaseGet = "";
            idx+=1;//Add one to remove the comma
        }
        else{//The date has no quotations and contains 4 numbers EX: 1988
            releaseEnd = line.indexOf(",");//Getting the index of the comma after the release date
            releaseGet = line.substring(0,releaseEnd);//Creating the substring
            idx+=releaseEnd+1;//updating the idx to the index after the end of the release date(idx is added by one for comma)
        }
        return new ParseData(idx, releaseGet);//Return a parse info object containing the parse information for the ReleaseDate
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
