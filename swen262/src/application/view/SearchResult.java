package view;

import model.Comic;

import java.util.Collection;
import java.util.Iterator;

public class SearchResult extends Result{

    Collection<Comic> collection;

    public SearchResult() {

    }

    @Override
    public void initResult(Collection<Comic> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        String show = "";
        Iterator<Comic> results = collection.iterator();
        while(results.hasNext()){
            show = show+"\n"+results.next().toString();
        }
        return super.RESULT+super.BARRIER+show;
    }
}
