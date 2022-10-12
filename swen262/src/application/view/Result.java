package view;

import model.Comic;

import java.util.Collection;

public abstract class Result {

    public final String RESULT = "Result: \n";
    public final String BARRIER = "-----------------------------------------------------------------------------------";

    public Result(){
    }

    public abstract void initResult(Collection<Comic> collection);

    @Override
    public abstract String toString();

    //public Collection<Comic> getResCollection() {
    //    return resCollection;
    //}
}
