package model.parsefiles;

public class Parser {

    private ParseOperationState state;

    public Parser(){
        state = new ParseSeries();
    }

    private void nextState(){
        state.nextPOS(this);
    }

    public ParseData parseValue (String line, int idx){
        ParseData currentValue = state.parseValue(line,idx);
        nextState();
        return currentValue;
    }

    public ParseOperationState getState(){
        return state;
    }

    public void setState(ParseOperationState newState){
        state = newState;
    }

    public Boolean running(){
        return state.hasNextState();
    }

}
