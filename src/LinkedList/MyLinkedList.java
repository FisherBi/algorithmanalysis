package LinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fisbii on 16-8-4.
 */
public class MyLinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        public Node(T d, Node<T> p, Node<T> n){
            data = d;
            prev = p;
            next = n;
        }
        public T data;
        public Node<T> prev;
        public Node<T> next;
    }

    public MyLinkedList(){
        clear();
    }

    public void clear(){
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null,beginMarker,null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public boolean add(T x){
        add(size(), x);
        return true;
    }

    public void add(int idx,T x){
        addBefore(getNode(idx), x);
    }

    public Node<T> getNode(int idx) {

        Node<T> p;

        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }

        if(idx < size() / 2){
            p = beginMarker.next;
            for(int i = 0; i < idx; i++){
                p = p.next;
            }
        }else{
            p = endMarker;
            for(int i = size(); i > idx; i--){
                p = p.prev;
            }
        }

        return p;
    }

    private void addBefore(Node<T> p,T x){
        Node<T> newNode = new Node<>(x,p.prev,p);
        p.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(int idx,T newVal){
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public T remove(int idx){
        return remove(getNode(idx));
    }

    private T remove(Node<T> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class LinkedListIterator implements Iterator<T>{

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!okToRemove){
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }
    }

    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;
}
