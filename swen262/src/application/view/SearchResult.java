package view;

import model.Comic;

import java.util.Collection;
import java.util.Iterator;

public class SearchResult extends Result{

    public SearchResult(Collection<Comic> collection) {
        super(collection);
    }

    @Override
    public String toString() {
        String show = "";
        Iterator<Comic> results = super.collection.iterator();
        while(results.hasNext()){
            show = show+"\n"+results.next().toString();
        }
        return super.RESULT+super.BARRIER+show;
    }
}
