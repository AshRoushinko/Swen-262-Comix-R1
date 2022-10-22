package model.parsefiles;

public class ParsePublisher implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseReleaseDate());
    }

    /**
     * Purpose - to get the Publisher from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Publisher and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String pubGet;//Creates an empty string that will be initialized with the publisher name and returned
        int pubEnd;//Declares the integer that will represent the index following the end of the publisher name.

        //Check for no publisher
        if (line.startsWith(",")){
            pubGet = "";//Set publisher to an empty string
            idx+=1;//Offset the idx by one for the comma
        }
        else if(line.startsWith("\"")){//Check for parenthesis
            pubEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the publisher name
            pubGet = line.substring(1,pubEnd);//Set the publishers name (initial idx is one to offset the parenthesis)
            idx+=pubEnd+2;//Update idx to the index after the end of the publisher name(idx is added by two to remove (",))
        }
        else{//The publisher value starts with a letter
            pubEnd = line.indexOf(",");//Getting the index of the comma after the publisher name
            pubGet = line.substring(0,pubEnd);//Creating the substring
            idx+=pubEnd+1;//updating the idx to the index after the end of the publisher name(idx is added by one for comma)
        }
        return new ParseData(idx, pubGet);//Return a parse info object containing the parse information for the Publisher
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
