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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * 
 * We believe it would be unwise to implement all three of 
 * intersection(), union(), and difference() in the AbstractSet class
 * because in order to implement any of those methods, you would only need
 * access to one of them. For example, if you only had access to the difference()
 * method and needed to find the union() between two sets, it would be the sum of
 * the first set and the difference of the second set minus the first set. 
 * Furthermore, if you only had access to the difference() method and needed to 
 * find the intersection between two sets, it would be the difference of the second
 * set minus the first set, then you would have to check if there is any element in 
 * the first set that is not contained in the second set, and that would be your
 * intersection.
 * 
 * SORTEDSET:
 * | File | Size (kb) | Total Words | Increase from prev row (total words) | 
 * | De Jongfryske biweging.txt | 240 KB | 40781 | --- |
 * | House property & its management.txt | 119 KB | 19771 | 0.485 |
 * | Six Mrs. Greenes | 374 KB | 65929 | 3.335 |
 * | Legal antiquities.txt | 635 KB | 106198 | 1.611 |
 * 
 * | Unique Words | inc from prev row (unique words) |
 * | 7959 | --- |
 * | 4758 | 0.598 |
 * | 10677 | 2.244 |
 * | 17739 | 1.661 |
 * 
 * | Actual time | inc from prev row (actual time)
 * | 0.1296329 seconds | --- |
 * | 0.0978473 seconds | 0.752 |
 * | 0.1412596 seconds seconds | 1.439 |
 * | 0.1577625 seconds | 1.117 |
 * 
 * UNSORTEDSET:
 * | File | Size (kb) | Total Words | Increase from prev row (total words) | 
 * | De Jongfryske biweging.txt | 240 KB | 40781 | --- |
 * | House property & its management.txt | 119 KB | 19771 | 0.485 |
 * | Six Mrs. Greenes | 374 KB | 65929 | 3.335 |
 * | Legal antiquities.txt | 635 KB | 106198 | 1.611 |
 * 
 * | Unique Words | inc from prev row (unique words) |
 * | 7959 | --- |
 * | 4758 | 0.598 |
 * | 10677 | 2.244 |
 * | 17739 | 1.661 |
 * 
 * | Actual time | inc from prev row (actual time)
 * | 0.6949382 seconds | --- |
 * | 0.2070012 seconds | 0.298 |
 * | 1.3596119 seconds | 6.565 |
 * | 3.1151332 seconds | 2.292 |
 * 
 * JAVA HASHSET:
 * | File | Size (kb) | Total Words | Increase from prev row (total words) | 
 * | De Jongfryske biweging.txt | 240 KB | 40781 | --- |
 * | House property & its management.txt | 119 KB | 19771 | 0.485 |
 * | Six Mrs. Greenes | 374 KB | 65929 | 3.335 |
 * | Legal antiquities.txt | 635 KB | 106198 | 1.611 |
 * 
 * | Unique Words | inc from prev row (unique words) |
 * | 7959 | --- |
 * | 4758 | 0.598 |
 * | 10677 | 2.244 |
 * | 17739 | 1.661 |
 * 
 * | Actual time | inc from prev row (actual time)
 * | 0.0444307 seconds | --- |
 * | 0.0248737 seconds | 0.568 |
 * | 0.0648365 seconds | 2.613 |
 * | 0.0794719 seconds | 1.234 |
 * 
 * JAVA TREESET:
 * | File | Size (kb) | Total Words | Increase from prev row (total words) | 
 * | De Jongfryske biweging.txt | 240 KB | 40781 | --- |
 * | House property & its management.txt | 119 KB | 19771 | 0.485 |
 * | Six Mrs. Greenes | 374 KB | 65929 | 3.335 |
 * | Legal antiquities.txt | 635 KB | 106198 | 1.611 |
 * 
 * | Unique Words | inc from prev row (unique words) |
 * | 7959 | --- |
 * | 4758 | 0.598 |
 * | 10677 | 2.244 |
 * | 17739 | 1.661 |
 * 
 * | Actual time | inc from prev row (actual time)
 * | 0.0349137 seconds | --- |
 * | 0.0286732 seconds | 0.829 |
 * | 0.0632033 seconds | 2.172 |
 * | 0.1010292 seconds | 1.603  |
 * 
 * #2:
 * We think that the Big O of processTextCS314Sets() is O(N * M)
 * and the Big O for Java Sets is: (HashSet O(N)) (TreeSet O(N log M))
 * 
 * #3:
 * Add methods for:
 * (UnsortedSet O(N))
 * (SortedSet O(N))
 * (HashSet O(1))
 * (TreeSet O(log N))
 * 
 * #4:
 * Hashset's output is in no discernable order yet keeps track of
 * duplicate elements, while TreeSet appears to be sorted alphabetically.
 */

public class SetTester {

    public static void main(String[] args) {
        // Test 1,2
        ISet<String> setAdd = new UnsortedSet<>();
        // Add "X" for the first time (should return true)
        boolean result = setAdd.add("X");
        showTestResults(result, true, 1, setAdd, null, "add(): Adding 'X' should return true.");
        
        // Try to add duplicate "X" (should return false)
        result = setAdd.add("X");
        showTestResults(result, false, 2, setAdd, null, "add(): Adding duplicate 'X' should return false.");

        // Test 3,4
        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");
        ISet<String> s2 = new UnsortedSet<>();
        s2.add("B");
        s2.add("C");
        // First call: s1.addAll(s2) should successfully add B and C (returns true)
        result = s1.addAll(s2);
        showTestResults(result, true, 3, s1, s2, "addAll(): Adding s2 to s1 should return true.");
        
        // Second call: s1.addAll(s2) should add no new elements (returns false)
        result = s1.addAll(s2);
        showTestResults(result, false, 4, s1, s2, "addAll(): Adding s2 to s1 a second time should return false.");
        
        // Test 5
        ISet<String> sClear = new UnsortedSet<>();
        sClear.add("P");
        sClear.add("Q");
        sClear.add("R");
        sClear.clear();
        result = (sClear.size() == 0);
        showTestResults(result, true, 5, sClear, null, "clear(): Set should be empty after clear().");

        // Test 6,7
        ISet<String> sContains = new UnsortedSet<>();
        sContains.add("Alpha");
        result = sContains.contains("Alpha");
        showTestResults(result, true, 6, sContains, null, "contains(): 'Alpha' should be found in the set.");
        result = sContains.contains("Beta");
        showTestResults(result, false, 7, sContains, null, "contains(): 'Beta' should not be found in the set.");

        // Test 8,9
        ISet<String> sContainsAll1 = new UnsortedSet<>();
        sContainsAll1.add("1");
        sContainsAll1.add("2");
        sContainsAll1.add("3");
        ISet<String> sContainsAll2 = new UnsortedSet<>();
        sContainsAll2.add("2");
        sContainsAll2.add("3");
        // sContainsAll1 contains all elements of sContainsAll2 → true
        result = sContainsAll1.containsAll(sContainsAll2);
        showTestResults(result, true, 8, sContainsAll1, sContainsAll2, "containsAll(): sContainsAll1 should contain sContainsAll2.");
        // sContainsAll2 does NOT contain all elements of sContainsAll1 → false
        result = sContainsAll2.containsAll(sContainsAll1);
        showTestResults(result, false, 9, sContainsAll2, sContainsAll1, "containsAll(): sContainsAll2 should not contain sContainsAll1.");

        // Test 10
        ISet<String> sDiff1 = new UnsortedSet<>();
        sDiff1.add("apple");
        sDiff1.add("banana");
        sDiff1.add("cherry");
        ISet<String> sDiff2 = new UnsortedSet<>();
        sDiff2.add("banana");
        ISet<String> diffResult = sDiff1.difference(sDiff2);
        ISet<String> expectedDiff = new UnsortedSet<>();
        expectedDiff.add("apple");
        expectedDiff.add("cherry");
        result = diffResult.equals(expectedDiff);
        showTestResults(result, true, 10, sDiff1, sDiff2, "difference(): sDiff1.difference(sDiff2) should equal {apple, cherry}.");

        // Test 11
        ISet<String> sInter1 = new UnsortedSet<>();
        sInter1.add("dog");
        sInter1.add("cat");
        sInter1.add("mouse");
        ISet<String> sInter2 = new UnsortedSet<>();
        sInter2.add("cat");
        sInter2.add("elephant");
        ISet<String> interResult = sInter1.intersection(sInter2);
        ISet<String> expectedInter = new UnsortedSet<>();
        expectedInter.add("cat");
        result = interResult.equals(expectedInter);
        showTestResults(result, true, 11, sInter1, sInter2, "intersection(): Intersection should equal {cat}.");

        // Test 12
        ISet<String> sUnion1 = new UnsortedSet<>();
        sUnion1.add("red");
        sUnion1.add("green");
        ISet<String> sUnion2 = new UnsortedSet<>();
        sUnion2.add("green");
        sUnion2.add("blue");
        ISet<String> unionResult = sUnion1.union(sUnion2);
        ISet<String> expectedUnion = new UnsortedSet<>();
        expectedUnion.add("red");
        expectedUnion.add("green");
        expectedUnion.add("blue");
        result = unionResult.equals(expectedUnion);
        showTestResults(result, true, 12, sUnion1, sUnion2, "union(): Union should equal {red, green, blue}.");

        // Test 13
        ISet<String> sEquals1 = new UnsortedSet<>();
        sEquals1.add("one");
        sEquals1.add("two");
        ISet<String> sEquals2 = new UnsortedSet<>();
        sEquals2.add("two");
        sEquals2.add("one");
        result = sEquals1.equals(sEquals2);
        showTestResults(result, true, 13, sEquals1, sEquals2, "equals(): Two sets with the same elements should be equal.");

        // Test 14, 15
        ISet<String> sRemove = new UnsortedSet<>();
        sRemove.add("removeMe");
        result = sRemove.remove("removeMe");
        showTestResults(result, true, 14, sRemove, null, "remove(): Removing an existing element should return true.");
        result = sRemove.contains("removeMe");
        showTestResults(result, false, 15, sRemove, null, "remove(): Set should not contain the removed element.");

        // Test 16
        ISet<String> sSize = new UnsortedSet<>();
        sSize.add("first");
        sSize.add("second");
        sSize.add("third");
        result = (sSize.size() == 3);
        showTestResults(result, true, 16, sSize, null, "size(): Size should be 3 after adding three elements.");

        // Test 17
        SortedSet<Integer> sortedSet = new SortedSet<>();
        sortedSet.add(30);
        sortedSet.add(10);
        sortedSet.add(20);
        int minVal = sortedSet.min();
        int maxVal = sortedSet.max();
        boolean minMaxResult = (minVal == 10 && maxVal == 30);
        showTestResults(minMaxResult, true, 17, sortedSet, null, 
                "min() and max(): For SortedSet {10,20,30}, min should be 10 and max should be 30.");

    
        //CS314 Students. Uncomment this section when ready to
        //run your experiments
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
        System.out.println("Unable to change look and feel");
        }
        Scanner sc = new Scanner(System.in);
        String response = "";
        do {
        largeTest();
        System.out.print("Another file? Enter y to do another file: ");
        response = sc.next();
        } while( response != null && response.length() > 0
        && response.substring(0,1).equalsIgnoreCase("y") );
    }

    // print out results of test
    private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
                                            int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
        if (actualResult == expectedResult) {
            System.out.println("Passed test " + testNumber);
        } else {
            System.out.print("Failed test ");
            System.out.println(testNumber + ": " + testDescription);
            System.out.println("Expected result: " + expectedResult);
            System.out.println("Actual result  : " + actualResult);
            System.out.println("Set 1: " + set1);
            if (set2 != null) {
                System.out.println("Set 2: " + set2);
            }
        }

    }

    /*
     * Method asks user for file and compares run times to add words from file
     * to various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest() {
        System.out.println();
        System.out.println("Opening Window to select file. "
                + "You may have to minimize other windows.");
        String text = convertFileToString();
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text, keyboard);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets(new HashSet<String>(), text, keyboard);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets(new TreeSet<String>(), text, keyboard);
        keyboard.close();
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for CS314
     * sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * pre: set != null, text != null Method to add all words in text to the
     * given set. Words are delimited by white space. This version for Java
     * Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text,
                                            Scanner keyboard) {
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while (sc.hasNext()) {
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size(), keyboard);
    }

    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
                                                int setSize, Scanner keyboard) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString());
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);

        System.out.print("Enter y to see the contents of this set: ");
        String response = keyboard.next();

        if (response != null && response.length() > 0
                && response.substring(0, 1).equalsIgnoreCase("y")) {
            for (Object o : set) {
                System.out.println(o);
            }
        }
        System.out.println();
    }

    /*
     * Ask user to pick a file via a file choosing window and convert that file
     * to a String. Since we are evaluating the file with many sets convert to
     * string once instead of reading through file multiple times.
     */
    private static String convertFileToString() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        // read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            Scanner s = null;
            try {
                s = new Scanner(new FileReader(source));

                while (s.hasNextLine()) {
                    text.append(s.nextLine());
                    text.append(" ");
                }

                s.close();
            } catch (IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            } finally {
                if (s != null) {
                    s.close();
                }
            }
        }

        return text.toString();
    }
}