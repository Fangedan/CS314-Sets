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
     * Big O - O(1)
     */
    public SortedSet() {
    	myCon = new ArrayList<>();
    }

    // Constructor for creating new Set
    protected ISet<E> createNewSet() {
        return new SortedSet<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * @param other != null
     * Big O - O(NlogN)
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
     * Big O - O(1)
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
     * Big O - O(1)
     */
    public E max() {
    	if (myCon.isEmpty()) {
            throw new IllegalStateException("SortedSet is empty");
        }
        return myCon.get(myCon.size() - 1);
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
        int index = Collections.binarySearch(myCon, item);
        if (index >= 0) {
            return false;
        } else {
            int insertionPoint = -index - 1;
            myCon.add(insertionPoint, item);
            return true;
        }
    }

    /**
    * A union operation. Add all items of otherSet that
    * are not already present in this set to this set.
    * @param otherSet != null
    * @return true if this set changed as a result of this operation,
    * false otherwise.
    * Big O - O(N)
    */
    public boolean addAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        boolean changed = false;

        // Special case: if otherSet is not a SortedSet, 
        // fall back to O(N log N) method
        if (!(otherSet instanceof SortedSet<?>)) {
            Iterator<E> it = otherSet.iterator();
            while (it.hasNext()) {
                if (this.add(it.next())) {
                    changed = true;
                }
            }
            return changed;
        }

        // Both sets are sorted, merge-like approach (O(N))
        SortedSet<E> sortedOther = (SortedSet<E>) otherSet;
        ArrayList<E> merged = new ArrayList<>();

        int i = 0, j = 0;
        int size1 = myCon.size();
        int size2 = sortedOther.myCon.size();

        while (i < size1 && j < size2) {
            E item1 = myCon.get(i);
            E item2 = sortedOther.myCon.get(j);
            int cmp = item1.compareTo(item2);
            if (cmp < 0) {
                merged.add(item1);
                i++;
            } else if (cmp > 0) {
                merged.add(item2);
                j++;
                changed = true;
            } else {
                merged.add(item1); // equal elements, add one
                i++;
                j++;
            }
        }

        while (i < size1) {
            merged.add(myCon.get(i++));
        }

        while (j < size2) {
            merged.add(sortedOther.myCon.get(j++));
            changed = true;
        }

        if (changed) {
            myCon = merged;
        }

        return changed;
    }

    /**
    * Determine if item is in this set.
    * <br>pre: item != null
    * @param item element whose presence is being tested.
    * Item may not equal null.
    * @return true if this set contains the specified item, false otherwise.
    * Big O - O(log N)
    */
    public boolean contains(E item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
        return Collections.binarySearch(myCon, item) >= 0;
    }

    /**
    * Determine if all of the elements of otherSet are in this set.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return true if this set contains all of the elements in otherSet,
    * false otherwise.
    * Big O - O(N)
    */
    public boolean containsAll(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = otherSet.iterator();
    
        E thisElement = null;
        E otherElement = null;
    
        if (thisIterator.hasNext()) {
            thisElement = thisIterator.next();
        }
        if (otherIterator.hasNext()) {
            otherElement = otherIterator.next();
        }

        while (otherElement != null) {
            while (thisElement != null && thisElement.compareTo(otherElement) < 0) {
                if (thisIterator.hasNext()) {
                    thisElement = thisIterator.next();
                } else {
                    // Reached the end of this set before finding the element
                    return false;
                }
            }

            if (thisElement == null || thisElement.compareTo(otherElement) > 0) {
                return false; // Element not found
            }

            // Elements match, move both iterators forward
            if (thisElement.compareTo(otherElement) == 0) {
                if (otherIterator.hasNext()) {
                    otherElement = otherIterator.next();
                } else {
                    break; // All elements of otherSet are found
                }
            }

            if (thisIterator.hasNext()) {
                thisElement = thisIterator.next();
            } else {
                thisElement = null; // Reached end of this set
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
    * Big O - O(N)
    */
    public ISet<E> difference(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        ISet<E> resultSet = new SortedSet<>();
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = otherSet.iterator();

        E thisElement = null;
        E otherElement = null;

        if (thisIterator.hasNext()) {
            thisElement = thisIterator.next();
        }
        if (otherIterator.hasNext()) {
            otherElement = otherIterator.next();
        }

        while (thisElement != null) {
            if (otherElement == null || thisElement.compareTo(otherElement) < 0) {
                // thisElement is not in otherSet, add it to resultSet
                resultSet.add(thisElement);
                if (thisIterator.hasNext()) {
                    thisElement = thisIterator.next();
                } else {
                    thisElement = null; // Reached the end of this set
                }
            } else if (thisElement.compareTo(otherElement) > 0) {
                // otherElement is smaller, move the iterator for otherSet forward
                if (otherIterator.hasNext()) {
                    otherElement = otherIterator.next();
                } else {
                    otherElement = null; // Reached the end of otherSet
                }
            } else {
                // thisElement equals otherElement, skip it from the result
                if (thisIterator.hasNext()) {
                    thisElement = thisIterator.next();
                } else {
                    thisElement = null; // Reached the end of this set
                }
                if (otherIterator.hasNext()) {
                    otherElement = otherIterator.next();
                } else {
                    otherElement = null; // Reached the end of otherSet
                }
            }
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
    * Big O - O(N)
    */
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Check if they are the same object
        }
        if (other == null || !(other instanceof ISet)) {
            return false; // Check if other is null or not an instance of ISet
        }
    
        ISet<E> otherSet = (ISet<E>) other;
    
        // First check if the sizes are the same
        if (this.size() != otherSet.size()) {
            return false; // Sets with different sizes are not equal
        }
    
        // Compare elements using iterators
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = otherSet.iterator();
    
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            E thisElement = thisIterator.next();
            E otherElement = otherIterator.next();
        
            if (!thisElement.equals(otherElement)) {
                return false; // If any elements are different, the sets are not equal
            }
        }
    
        return true; // All elements are equal
    }

    /**
     * Create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set
     * and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     * Big O - O(N)
     */
    public ISet<E> intersection(ISet<E> otherSet) {
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }
        
        // Create a new set to store the result
        ISet<E> resultSet = new UnsortedSet<>();
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> otherIterator = otherSet.iterator();
        
        E thisElement = thisIterator.hasNext() ? thisIterator.next() : null;
        E otherElement = otherIterator.hasNext() ? otherIterator.next() : null;
        
        while (thisElement != null && otherElement != null) {
            int comparison = thisElement.compareTo(otherElement);
            
            if (comparison == 0) {
                // Elements are equal, add to the result set
                resultSet.add(thisElement);
                thisElement = thisIterator.hasNext() ? thisIterator.next() : null;
                otherElement = otherIterator.hasNext() ? otherIterator.next() : null;
            } else if (comparison < 0) {
                // This element is smaller, move to the next element in this set
                thisElement = thisIterator.hasNext() ? thisIterator.next() : null;
            } else {
                // Other element is smaller, move to the next element in the other set
                otherElement = otherIterator.hasNext() ? otherIterator.next() : null;
            }
        }
        
        return resultSet;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     * Big O - O(1)
     */
    public Iterator<E> iterator() {
        return myCon.iterator();
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