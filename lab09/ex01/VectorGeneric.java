package lab09.ex01;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorGeneric<T> {
    private T[] vec;
    private int nElem;
    private final static int ALLOC = 50;
    private int dimVec = ALLOC;

    @SuppressWarnings("unchecked")
    public VectorGeneric() {
        vec = (T[]) new Object[dimVec];
        nElem = 0;
    }

    public boolean addElem(T elem) {
        if (elem == null)
            return false;
        ensureSpace();
        vec[nElem++] = elem;
        return true;
    }

    private void ensureSpace() {
        if (nElem >= dimVec) {
            dimVec += ALLOC;
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[dimVec];
            System.arraycopy(vec, 0, newArray, 0, nElem);
            vec = newArray;
        }
    }

    public boolean removeElem(T elem) {
        for (int i = 0; i < nElem; i++) {
            if (vec[i].equals(elem)) {
                if (nElem - i - 1 > 0) // not last element
                    System.arraycopy(vec, i + 1, vec, i, nElem - i - 1);
                vec[--nElem] = null; // libertar último objecto para o GC
                return true;
            }
        }
        return false;
    }

    public int totalElem() {
        return nElem;
    }

    public T getElem(int i) {
        return (T) vec[i];
    }

    //Iterador
    public Iterator<T> iterator() {
        return (this).new VectorIterator<T>();
    }

    private class VectorIterator<K> implements Iterator<K> {
        private int indice;

        VectorIterator() {
            indice = 0;
        }

        @Override
        public boolean hasNext() {
            return (indice< nElem);
        }

        @SuppressWarnings("unchecked")
        public K next() {
            if (hasNext())
                return (K) VectorGeneric.this.getElem(indice++);
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        public void remove() {
            throw new UnsupportedOperationException("Iterator<T>.remove() is optional, so it wasn't implemented.");
        }
    }

    //listIterator
    public ListIterator<T> listIterator(int index) {
        return (this).new VectorListIterator<T>(index);
    }

    public ListIterator<T> listIterator() {
        return (this).new VectorListIterator<T>();
    }

    private class VectorListIterator<K> implements ListIterator<K>{
        private int indice;

        private VectorListIterator() {
            indice = 0;
        }

        private VectorListIterator(int indicex) {
            if (indice < nElem) indice = indicex;
            else throw new NoSuchElementException("Only " + nElem + " elements!");
        }
        @Override
        public boolean hasNext() {
            return (indice< nElem);
        }

        @Override
        public K next() {
            if (hasNext())
                return (K) VectorGeneric.this.getElem(indice++);
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        @Override
        public boolean hasPrevious() {
            return (indice >0);
        }

        @SuppressWarnings("unchecked")
        public K previous() {
            if (hasPrevious())
                return (K) VectorGeneric.this.vec[--indice];
            throw new NoSuchElementException(String.format("There are only %d elements!", nElem));
        }

        @Override
        public int nextIndex() {
            if (hasNext())
                return indice+1;
            return nElem;  //Error
        }

        @Override
        public int previousIndex() {
            if (hasPrevious())
                return indice-1;
            return -1;  //Error
        }

        @Override
        public void remove() {
            // T e = VectorGeneric.this.getElem(idx);
            // VectorGeneric.this.removeElem(e);
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(K k) {
            // VectorGeneric.this.vec[idx] = (T) e;
            throw new UnsupportedOperationException("ListIterator<T>.set(E e) is optional, so it wasn't implemented.");

        }

        @Override
        public void add(K k) {
            // VectorGeneric.this.addElem((T) e);
            throw new UnsupportedOperationException("ListIterator<T>.add(E e) is optional, so it wasn't implemented.");
        }
    }
}


