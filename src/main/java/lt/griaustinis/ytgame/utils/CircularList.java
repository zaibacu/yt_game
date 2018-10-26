package lt.griaustinis.ytgame.utils;

import java.util.Iterator;
import java.util.List;

public class CircularList<T> implements Iterable<T> {
    private final List<T> data;
    private int cursor = 0;

    public CircularList(List<T> data){
        this.data = data;
    }

    private class CircularIterator implements Iterator<T>{

        @Override
        public boolean hasNext() {
            return data.size() > 0;
        }

        @Override
        public T next() {
            T item = data.get(cursor);
            cursor += 1;
            if(cursor >= data.size()){
                cursor = 0;
            }
            return item;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new CircularIterator();
    }

    public T first(){
        return this.data.get(0);
    }
}
