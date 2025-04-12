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
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    // Instance variable(s)
    private ArrayList<E> myCon;

    // Constructor for initializing the container
    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    // Constructor for creating new Set 
    protected ISet<E> createNewSet(){
    	return new UnsortedSet<E>();
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     * Big O - O(N)
     */
    public boolean add(E item) {
        // Check Precondition
        if (item == null) {
            throw new NullPointerException("Item is null");
        }

        if (!myCon.contains(item)) {
            myCon.add(item);
            return true;
        }

        return false;
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     * Big O - O(N)
     */
    protected boolean addImpl(E item) {
    	return myCon.add(item);
    }

    /**
    * A union operation. Add all items of otherSet that
    * are not already present in this set to this set.
    * @param otherSet != null
    * @return true if this set changed as a result of this operation,
    * false otherwise.
    * Big O - O(N^2)
    */
   public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        boolean changed = false;
        Iterator<E> it = otherSet.iterator();
        while (it.hasNext()) {
            E item = it.next();
            if (this.add(item)) {
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     * Big O - O(N)
     */
    public void clear() {
        myCon.clear();
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested.
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     * Big O - O(N)
     */
    public boolean contains(E item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
        return myCon.contains(item);
    }

    /**
    * Determine if all of the elements of otherSet are in this set.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return true if this set contains all of the elements in otherSet,
    * false otherwise.
    * Big O - O(N^2)
    */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        Iterator<E> it = otherSet.iterator();
        while (it.hasNext()) {
            if (!this.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /**
    * Create a new set that is the difference of this set and otherSet.
    * Return an ISet of elements that are in this Set but not in otherSet.
     * Also called the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z]
    * then A.difference(B) would return an ISet with elements [X, Y] while
    * B.difference(A) would return an ISet with elements [W].
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the difference of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the difference of this set and otherSet
    * Big O - O(N^2)
    */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        ISet<E> resultSet = new UnsortedSet<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E item = it.next();
            if (!otherSet.contains(item)) {
                resultSet.add(item);
            }
        }
        return resultSet;
    }

    /**
    * Create a new set that is the intersection of this set and otherSet.
    * <br>pre: otherSet != null<br>
    * <br>post: returns a set that is the intersection of this set
    * and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the intersection of this set and otherSet
    * Big O - O(N^2)
    */
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        ISet<E> resultSet = new UnsortedSet<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E item = it.next();
            if (otherSet.contains(item)) {
                resultSet.add(item);
            }
        }
        return resultSet;
    }

    /**
    * Create a new set that is the union of this set and otherSet.
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the union of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the union of this set and otherSet
    * Big O - O(N^2)
    */
    public ISet<E> union(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        ISet<E> resultSet = new UnsortedSet<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            resultSet.add(it.next());
        }
        it = otherSet.iterator();
        while (it.hasNext()) {
            resultSet.add(it.next());
        }
        return resultSet;
    }

    /**
    * Determine if this set is equal to other.
    * Two sets are equal if they have exactly the same elements.
    * The order of the elements does not matter.
    * pre: none
    * @param other the object to compare to this set
    * @return true if other is a Set and has the same elements as this set
    * Big O - O(N^2)
    */
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof ISet)) {
            return false;
        }
        ISet<E> otherSet = (ISet<E>) other;
        return this.size() == otherSet.size() && this.containsAll(otherSet);
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     * Big O - O(1)
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;
            private int lastReturnedIndex = -1; // Index of the last returned element

            public boolean hasNext() {
                return currentIndex < myCon.size();
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturnedIndex = currentIndex;
                return myCon.get(currentIndex++);
            }

            // Big O - O(N)
            public void remove() {
                if (lastReturnedIndex < 0) {
                    throw new IllegalStateException("Call next() before remove(), or remove already called.");
                }
                // Remove the last returned element from the list
                myCon.remove(lastReturnedIndex);
                // Adjust currentIndex to reflect removal
                currentIndex = lastReturnedIndex;
                // Reset lastReturnedIndex to prevent consecutive remove() calls
                lastReturnedIndex = -1;
            }
        };
    }

    /**
    * Return the number of elements of this set.
    * pre: none
    * @return the number of items in this set
    * Big O - O(1)
    */
    public int size() {
    	return myCon.size();
    }

}