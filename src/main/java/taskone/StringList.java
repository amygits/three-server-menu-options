package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {

    List<String> strings = new ArrayList<String>();

    public synchronized void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }

    public synchronized String remove(int index) throws IndexOutOfBoundsException {
        return strings.remove(index);
    }

    public synchronized boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public synchronized int size() {
        return strings.size();
    }

    public synchronized void reverse(int index) throws IndexOutOfBoundsException{
        String str = strings.get(index);
        String nStr = "";
        //reverse string
        for(int i = str.length() - 1; i >= 0; i--){
            nStr += str.charAt(i);
        }    
        //add to original index in list
        strings.set(index, nStr);        
    }

    public synchronized String toString() {
        return strings.toString();
    }
}