package view;

import model.Comic;

import java.util.Collection;

public abstract class Result {

    public final String RESULT = "Result: \n";
    public final String BARRIER = "-----------------------------------------------------------------------------------";
    public Collection<Comic> collection;

    public Result(Collection<Comic> collection){
        this.collection = collection;
    }

    public Collection<Comic> getResultCollection(){
        return this.collection;
    }

    @Override
    public abstract String toString();

}
