package model.parsefiles;

public class ParseCreators implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseEnd());
    }

    /**
     * Purpose - to get the Creators from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the Creators and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String createGet;//Creates an empty string that will be initialized with the creators and returned

        if (line.equals("")){//Check to see if there are no creators
            createGet = "";//set the string
        }
        else{//There are creators
            createGet = line.substring(1,line.length()-1);//add the rest of the line excluding the parenthesis
        }
        return new ParseData(idx, createGet);//Return a parse info object containing the parse information for the creators
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
