package poc.rest.config.util;

import java.util.Collection;
import java.util.Iterator;

public class StringProcessor {

    public static String wrapInDQuotes(String str){
        return "\"" + str + "\"";
    }

    public static String wrapInSQuotes(String str){
        return "\'" + str + "\'";
    }

    public static String separateCollection(Separator begin, Collection collection, Separator between, Separator end) {
        String result = "";
        if (begin != null)
            result += begin.getSeparator();

        Iterator iterator = collection.iterator();
        if (collection != null && between != null && collection.size() != 0) {
            result += iterator.next();
            while (iterator.hasNext()){
                result += between.getSeparator() + iterator.next();
            }
        }

        if (end != null)
            result += end.getSeparator();

        return result;
    }

    public static String separateCollection(Collection collection, Separator between, Separator end) {
        return separateCollection(null, collection, between, end);
    }

    public static String separateCollection(Separator begin, Collection collection, Separator between) {
        return separateCollection(begin, collection, between, null);
    }

    public static String separateCollection(Collection collection, Separator between) {
        return separateCollection(null, collection, between, null);
    }
}
