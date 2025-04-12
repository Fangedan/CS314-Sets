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

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    // Constructor for initializing the container
    public UnsortedSet() {
        myCon = new ArrayList<>();
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

        if (!myCon.contains(item)) {
            myCon.add(item);
            return true;
        }
        
        return false;
    }


    /**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            // Check if there are more elements in the container
            public boolean hasNext() {
                return currentIndex < myCon.size();
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                // Return the next element and move the index
                return myCon.get(currentIndex++);
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove" 
                 + "operation is not supported.");
            }
        };
    }

}