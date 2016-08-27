package AvlTree;


import java.util.Comparator;

/**
 * Created by fisbii on 16-8-26.
 */
public class AvlTree<T extends Comparable<? super T>> {

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

        int compareResult = compare(x,t);

        if(compareResult < 0){
            t.left = insert(x, t.left);
            if(height(t.left) - height(t.right) == 2){
                if(compare(x,t.left) < 0){
                    t = rotateWithLeftChild(t);
                }else{
                    t = doubleWithLeftChild(t);
                }
            }
        }else if(compareResult > 0){
            t.right = insert(x,t.right);
            if(height(t.right) - height(t.left) == 2){
                if(compare(x,t.right) > 0){
                    t = rotateWithRightChild(t);
                }else{
                    t = doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right));
        k1.height = Math.max(height(k1.left),height(k1.right));
        return k1;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right));
        k1.height = Math.max(height(k1.left),height(k1.right));
        return k1;
    }


    private int compare(T x, AvlNode<T> t){
        return x.compareTo(t.element);
    }

}
