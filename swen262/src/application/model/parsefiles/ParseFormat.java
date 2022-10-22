package model.parsefiles;

public class ParseFormat implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseAddDate());
    }

    /**
     * Purpose - to get the Format from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Format and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        //Can start with: A letter, A quote, or a comma which means there is no format

        String formatGet;//Creates an empty string that will be initialized with the format and returned
        int formatEnd;//Declares the integer that will represent the index following the end of the format.
        if (line.startsWith("\"")){//Check to see if it starts with a quote
            formatEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the format value
            formatGet = line.substring(1,formatEnd);//Set the format string (Starting value is one to remove the quotation mark)
            idx+=formatEnd+2;//Get the index after the format value (add two to remove the closing quotation mark and the comma)
        }
        else if (line.startsWith(",")){//Check to see if there is no format value
            formatGet = "";
            idx+=1;//Add one to remove the comma
        }
        else{//The format has no quotations (Starts with a letter
            formatEnd = line.indexOf(",");//Getting the index of the comma after the format
            formatGet = line.substring(0,formatEnd);//Creating the substring
            idx+=formatEnd+1;//updating the idx to the index after the end of the format(idx is added by one for comma)
        }
        return new ParseData(idx, formatGet);//Return a parse info object containing the parse information for the Format
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
