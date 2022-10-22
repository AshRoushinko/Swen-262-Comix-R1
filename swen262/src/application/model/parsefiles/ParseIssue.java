package model.parsefiles;

public class ParseIssue implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(new ParseTitle());
    }

    /**
     * Purpose - to get the issue from the line
     * @param line - The current line
     * @param idx - the current idx
     * @return - returns a ParseInfo object containing the issue and the updated line index
     */
    @Override
    public ParseData parseValue(String line, int idx) {
        String issueGet;//Creates an empty string that will be initialized with the issue of the comic and returned
        if(line.startsWith(",")){
            issueGet = "";
        }
        else{
            int issueEnd = line.indexOf(",");
            issueGet = line.substring(0,issueEnd);
            idx+=issueEnd;
        }
        idx+=1;
        return new ParseData(idx, issueGet);//Return a parse info object containing the parse information for the issue
    }
    @Override
    public Boolean hasNextState() {
        return true;
    }
}
