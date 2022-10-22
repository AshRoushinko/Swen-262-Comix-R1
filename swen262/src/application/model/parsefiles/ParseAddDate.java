package model.parsefiles;

public class ParseAddDate implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseCreators());
    }

    /**
     * Purpose - to get the AddDate from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the AddDate and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        int addEnd = line.indexOf("\",");//Get the index of the quotation followed by the comma (",) which represents the end of the add date value
        String addGet = line.substring(1,addEnd);//Set the add date string (Starting value is one to remove the quoteation mark)
        idx+=addEnd+2;//Get the index after the add date value (add two to remove the closing quotation mark and the comma)

        return new ParseData(idx, addGet);//Return a parse info object containing the parse information for the AddDate
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
