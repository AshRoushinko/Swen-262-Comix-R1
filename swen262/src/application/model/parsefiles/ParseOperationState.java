package model.parsefiles;

public interface ParseOperationState {

    void nextPOS(Parser parser);
    ParseData parseValue(String line, int idx);
    Boolean hasNextState();

}
