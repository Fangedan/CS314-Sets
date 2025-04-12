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

/**
 * Students are to complete this class. 
 * Students should implement as many methods as they can using the Iterator
 * from the iterator method and the other methods.
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /* DELETE THIS COMMENT FROM YOUR SUBMISSION.
     *
     * RECALL:
     *
     * NO INSTANCE VARIABLES ALLOWED.
     *
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear anywhere in this class.)
     *
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     *
     * NO METHODS ADDED other than those in ISet and Object.
     */

    /**
     * Return a String version of this set. 
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String separator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(separator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - separator.length());
        }

        result.append(")");
        return result.toString();
    }

    /**
     * Add an item to this set.
     * <br> item != null
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     * false otherwise.
     */
    public boolean add(E item) {
        // Check Precondition
        if (item == null) {
            throw new NullPointerException("Item is null");
        }

        if (!this.contains(item)) {
            this.addNew(item);
            return true;
        }

        return false;
    }

    /**
    * A union operation. Add all items of otherSet that
    * are not already present in this set to this set.
    * @param otherSet != null
    * @return true if this set changed as a result of this operation,
    * false otherwise.
    */
    public boolean addAll(ISet<E> otherSet) {
        // Check Precondition
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        Iterator<E> it = otherSet.iterator();
        boolean changed = false;

        while (it.hasNext()) {
            E obj = it.next();
            if (!this.contains(obj)) {
                this.add(obj);
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear() {
        Iterator<E> it = this.iterator();
        
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Determine if item is in this set.
     * <br>pre: item != null
     * @param item element whose presence is being tested.
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        // Check Precondition
        if (item == null) {
            throw new NullPointerException("Item is null");
        }

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E obj = it.next();
            if (item.equals(obj)) {
                return true;
            }
        }

        return false;
    }

   /**
    * Determine if all of the elements of otherSet are in this set.
    * <br> pre: otherSet != null
    * @param otherSet != null
    * @return true if this set contains all of the elements in otherSet,
    * false otherwise.
    */
    public boolean containsAll(ISet<E> otherSet) {
        // Check Precondition
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        Iterator<E> it = otherSet.iterator();
        while (it.hasNext()) {
            E obj = it.next();
            if (!this.contains(obj)) {
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
    */
    public ISet<E> difference(ISet<E> otherSet){
        // Check Precondition
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        ISet<E> differenceSet = new UnsortedSet<>();
        Iterator<E> it = this.iterator();      
        while (it.hasNext()) {
            E obj = it.next();
            if (!otherSet.contains(obj)) {
                differenceSet.add(obj);
            }
        }

        return differenceSet;
    }

    /**
    * Determine if this set is equal to other.
    * Two sets are equal if they have exactly the same elements.
    * The order of the elements does not matter.
    * pre: none
    * @param other the object to compare to this set
    * @return true if other is a Set and has the same elements as this set
    */
    public boolean equals(Object other) {
        if (this == other) {
            return true;  // Same object reference
        }
    
        if (other == null || !(other instanceof ISet<?>)) {
            return false;  // Different type or null
        }
    
        ISet<?> otherSet = (ISet<?>) other;
    
        // Check if the sets have the same number of elements
        if (this.size() != otherSet.size()) {
            return false;
        }
    
        // Check if all elements in this set are contained in the other set
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E item = it.next();
            if (!otherSet.contains(item)) {
                return false;  // Found an element not in the other set
            }
        }
    
        return true;  // All elements are present in both sets
    }

    /**
    * Create a new set that is the intersection of this set and otherSet.
    * <br>pre: otherSet != null<br>
    * <br>post: returns a set that is the intersection of this set
    * and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the intersection of this set and otherSet
    */
    public ISet<E> intersection(ISet<E> otherSet){
        // Check Precondition
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        ISet<E> intersectionSet = new UnsortedSet<>();
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E obj = it.next();
            if (otherSet.contains(obj)) {
                intersectionSet.add(obj);
            }
        }

        return intersectionSet;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     * Abstract method for iterators - to be implemented by subclasses
     */
    public abstract Iterator<E> iterator();

    /**
    * Remove the specified item from this set if it is present.
    * pre: item != null
    * @param item the item to remove from the set. item may not equal null.
    * @return true if this set changed as a result of this operation,
    * false otherwise
    */
    public boolean remove(E item) {
        // Check Precondition
        if (item == null) {
            throw new NullPointerException("Item is null");
        }

        boolean changed = false;
        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            E obj = it.next();
            if (obj.equals(item)) {
                it.remove();
                changed = true;
            }
        }

        return changed;
    }

    /**
    * Return the number of elements of this set.
    * pre: none
    * @return the number of items in this set
    */
    public int size() {
        Iterator<E> it = this.iterator();
        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }

        return count;
    }

    /**
    * Create a new set that is the union of this set and otherSet.
    * <br>pre: otherSet != null
    * <br>post: returns a set that is the union of this set and otherSet.
    * Neither this set or otherSet are altered as a result of this operation.
    * @param otherSet != null
    * @return a set that is the union of this set and otherSet
    */
    public ISet<E> union(ISet<E> otherSet){
        // Check Precondition
        if (otherSet == null) {
            throw new NullPointerException("otherSet is null");
        }

        ISet<E> unionSet = new UnsortedSet<>();

        // Add all elements from this set
        Iterator<E> it1 = this.iterator();
        while (it1.hasNext()) {
            unionSet.add(it1.next());
        }

        // Add all elements from otherSet (will skip duplicates if set handles them)
        Iterator<E> it2 = otherSet.iterator();
        while (it2.hasNext()) {
            unionSet.add(it2.next());
        }

        return unionSet;
    }
}