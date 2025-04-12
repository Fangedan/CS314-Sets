/*  Student information for assignment:
*
*  On our honor, Andrew Lin and Vishal Vijayakumar's,
*  this programming assignment is our own work
*  and we have not provided this code to any other student.
*
*  Number of slip days used: 1
*
*  Student 1: Andrew Lin
*  UTEID: al58444
*  email address: alin257274@utexas.edu
*
*  Student 2: Vishal Vijayakumar
*  UTEID: vv8945
*  email address: vishal.vijayakumar@utexas.edu
*
*  Grader name: Casey
*  Section number: 50760
*/

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
    	myCon = new ArrayList<>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
    	if (other == null) {
            throw new NullPointerException("other is null");
        }
        myCon = new ArrayList<>();
        if (other instanceof SortedSet<?>) {
            SortedSet<E> sortedOther = (SortedSet<E>) other;
            myCon.addAll(sortedOther.myCon);
        } else {
            Iterator<E> it = other.iterator();
            while(it.hasNext()) {
                this.add(it.next());
            }
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the smallest element in this SortedSet.
     */
    public E min() {
    	if (myCon.isEmpty()) {
            throw new IllegalStateException("SortedSet is empty");
        }
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br> pre: size() != 0
     * @return the largest element in this SortedSet.
     */
    public E max() {
    	if (myCon.isEmpty()) {
            throw new IllegalStateException("SortedSet is empty");
        }
        return myCon.get(myCon.size() - 1);
    }

    protected ISet<E> createNewSet() {
        return new SortedSet<E>();
    }
    
    protected boolean addImpl(E item) {
        int index = Collections.binarySearch(myCon, item);
        if (index >= 0) {
            return false;
        } else {
            int insertionPoint = -index - 1;
            myCon.add(insertionPoint, item);
            return true;
        }
    }
    
    protected boolean removeImpl(E item) {
        int index = Collections.binarySearch(myCon, item);
        if (index >= 0) {
            myCon.remove(index);
            return true;
        }
        return false;
    }
    
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    public int size() {
        return myCon.size();
    }
}