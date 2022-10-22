package model.parsefiles;

public class ParseEnd implements ParseOperationState{
    @Override
    public void nextPOS(Parser parser) {
        parser.setState(null);
    }

    @Override
    public ParseData parseValue(String line, int idx) {
        return null;
    }

    @Override
    public Boolean hasNextState() {
        return false;
    }
}
