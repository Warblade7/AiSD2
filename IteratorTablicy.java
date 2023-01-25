import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorTablicy<T> implements Iterator<T>{

    private T tab[];
    private int pos=0;

    public IteratorTablicy(T tabl[]) {
        tab=tabl;
    }

    @Override
    public boolean hasNext() {
        if(pos<tab.length) {
            if(tab[pos]==null)
                return false;
            return true;
        }
        return true;
    }

    public void moveTo(int a) {
        pos=a;
    }

    @Override
    public T next() throws NoSuchElementException{
        if(hasNext()) {
            T e = tab[pos];
            pos++;
            return e;
        }
        else
            throw new NoSuchElementException();
    }

    public void replace(T cos) {
        tab[pos] = cos;
        pos++;
    }

}
