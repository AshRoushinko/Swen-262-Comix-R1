package model.parsefiles;

public class ParseDescription implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParsePublisher());
    }

    /**
     * Purpose - to get the Description from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Description and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String descGet;//Creates an empty string that will be initialized with the description and returned
        int descEnd;//Declares the integer that will represent the index following the end of the description.

        //Check for no description
        if (line.startsWith(",")){
            descGet = "";//Set description to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if (line.startsWith("\"")){//Checks for Quotes in front
            descEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the description
            String descGetTemp = line.substring(1,descEnd);//Get the description (Still has embedded quotes) (Start is one to remove first quote)
            descGet = descGetTemp.replaceAll("\"\"","\"");//Replace all double quotes with singe quotes
            idx += descEnd+2;//Update the index to the next value (Add 2 for quote and comma)
        }
        else{//No quotes in description
            descEnd = line.indexOf(",");//Gets the index of the comma following the description
            descGet = line.substring(0,descEnd);//Generates the description
            idx+=descEnd+1;//Add the index of the end of the description to the current index (add one for comma)
        }
        return new ParseData(idx, descGet);//Return a parse info object containing the parse information for the Description
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
