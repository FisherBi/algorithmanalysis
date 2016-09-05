package BinaryHeap;

/**
 * Created by fisbii on 16-9-5.
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private int currentSize;

    private T[] array;

    public BinaryHeap(){

    }

    public BinaryHeap(int capacity){

    }

    public BinaryHeap(T[] items){
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for(T item : items){
            array[i++] = item;
        }

        buildHeap();
    }

    private void buildHeap(){
        for(int i = currentSize / 2; i > 0; i--){
            percolateDown(i);
        }
    }

    private void percolateDown(int hole){

    }

    public void insert(T x){
        if(currentSize == array.length+1){
            enlargeArray(array.length*2+1);
        }
        // percolate up
        int hole = ++currentSize;
        for(; hole > 1 && x.compareTo(array[hole/2]) < 0; hole /= 2){
            array[hole] = array[hole/2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int i) {
        T[] newArray = (T[]) new Comparable[i];
        for(int j = 0; j < array.length; j++){
            newArray[j] = array[i];
        }
        array = newArray;
    }
}
