package AvlTree;


/**
 * Created by fisbii on 16-8-26.
 */
public class AvlTree<T> {

    private static class AvlNode<T>{

        AvlNode(T theElement){
            this(theElement, null, null);
        }

        AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt){
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
    }

    public int height(AvlNode<T> t){
        return t == null ? -1 : t.height;
    }

    public AvlNode<T> insert(T x, AvlNode<T> t){
        if(t == null){
            return new AvlNode<T>(x, null, null);
        }
        
    }

}
