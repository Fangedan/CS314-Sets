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
 */

public class SetTester {

    public static void main(String[] args) {

        ISet<String> s1 = new UnsortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 1
        boolean actual = s1.contains("A");
        showTestResults(actual, true, 1, s1, null, "add and contains methods UnsortedSet"
                + "/nset 1 contains A.");

        // test 2
        s1.remove("A");
        actual = s1.contains("A");
        showTestResults(actual, false, 2, s1, null, "remove and contains method UnsortedSet"
                + "/nset1 does not contain A.");

        // test 3
        actual = s1.size() == 2;
        showTestResults(actual, true, 3, s1, null, "size method UnsortedSet"
                + "/nsize of set 1 is 2.");

        ISet<String> s2 = new UnsortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 4
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 4, s1, s2, "containsAll method UnsortedSet"
                + "/ns2 contains all of s1.");

        // test 5
        actual = s1.containsAll(s2);
        showTestResults(actual, false, 5, s1, s2, "containsAll method UnsortedSet"
                + "/ns1 contains all of s2.");

        // test 6
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<>();
        expected.add("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 6, s1, s2, "difference and equals methods UnsortedSet"
                + "/ns2.difference(s1). result = " + s3 + " expected result = " + expected);

        // test 7
        s3 = s2.union(s1);
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 7, s1, s2, "union and equals methods UnsortedSet"
                + "/ns2.union(s1). actual result = " + s3
                + " expected result = " + expected);

        // test 8
        s3 = s2.intersection(s1);
        expected.remove("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 8, s1, s2, "intersection and equals methods UnsortedSet"
                + "/ns2.intersection(s1). actual result = " + s3
                + " expected result = " + expected);

        // sorted sets
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("C");
        s1.add("A");
        s1.add("B");

        // test 9
        actual = s1.contains("A");
        showTestResults(actual, true, 9, s1, null, "add and contains methods SortedSet"
                + "/nset 1 contains A.");

        // test 10
        s1.remove("A");
        actual = s1.contains("A");
        showTestResults(actual, false, 10, s1, null, "remove and contains method SortedSet"
                + "/nset1 does not contain A.");


        // test 11
        actual = s1.size() == 2;
        showTestResults(actual, true, 11, s1, null, "size method SortedSet"
                + "/nsize of set 1 is 2.");

        s2 = new SortedSet<>();
        s2.add("C");
        s2.add("A");
        s2.add("B");

        // test 12
        actual = s2.containsAll(s1);
        showTestResults(actual, true, 12, s1, s2, "containsAll method SortedSet"
                + "/ns2 contains all of s1.");

        // test 13
        actual = s1.containsAll(s2);
        showTestResults(actual, false, 13, s1, s2, "containsAll method SortedSet"
                + "/ns1 contains all of s2.");

        // test 14
        s3 = s2.difference(s1);
        expected = new SortedSet<>();
        expected.add("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 14, s1, s2, "difference and equals methods SortedSet"
                + "/ns2.difference(s1). result = " + s3 + " expected result = " + expected);

        // test 15
        s3 = s1.difference(s2);
        expected = new SortedSet<>();
        actual = s3.equals(expected);
        showTestResults(actual, true, 14, s1, s2, "difference and equals methods SortedSet"
                + "/ns1.difference(s2). result = " + s3 + " expected result = " + expected);

        // test 16
        s3 = s1.union(s2);
        expected = new SortedSet<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        actual = s3.equals(expected);
        showTestResults(actual, true, 16, s1, s2, "union and equals methods SortedSet"
                + "/ns2.union(s1). actual result = " + s3
                + " expected result = " + expected);


        // test 17
        s3 = s1.intersection(s2);
        expected.remove("A");
        actual = s3.equals(expected);
        showTestResults(actual, true, 17, s1, s2, "intersection and equals methods SortedSet"
                + "/ns1.intersection(s2). actual result = " + s3
                + " expected result = " + expected);

        // test 18
        s1.add("A");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while (good && it1.hasNext()) {
            good = it1.next().equals(it2.next());
        }
        showTestResults(good, true, 18, s1, s2, "iterator and add methods SortedSet."
                + "\nChecked all elements equal via iterators.");

        // test 19
        s1 = new UnsortedSet<>();
        UnsortedSet<Integer> si1 = new UnsortedSet<>();
        actual = si1.equals(s1);
        showTestResults(actual, true, 19, s1, s2, "equals methods UnsortedSet"
                + "\ns2.equals(s1), both sets empty");

        // test 20
        s1.add("is");
        s1.add("a");
        si1.add(12);
        si1.add(13);
        si1.add(12);
        actual = si1.equals(s1);
        showTestResults(actual, false, 20, si1, null, "equals methods UnsortedSet"
                + "\ns2.equals(s1), different data types of elements");

        // test 21
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(12);
        ar.add(13);
        actual = si1.equals(ar);
        showTestResults(actual, false, 20, si1, null, "equals methods UnsortedSet"
                + "\nsi1.equals(anArrayList), other Object is not a set");

        // test 22
        Object obj1 = s1;
        s2 = new UnsortedSet<>();
        s2.add("a");
        s2.add("is");
        Object obj2 = s2;
        actual = obj1.equals(obj2);
        showTestResults(actual, true, 22, s1, s2, "equals methods UnsortedSet"
                + "\nVerify equals overridden and not overloaded.");

        // test 23
        s1 = new SortedSet<>();
        s1.add("A");
        s1.add("A");
        s1.add("B");
        ISet<Integer> ss2 = new SortedSet<>();
        ss2.add(12);
        ss2.add(15);
        ss2.add(12);
        ss2.add(15);
        actual = s1.equals(ss2);
        showTestResults(actual, false, 23, s1, null, "equals methods SortedSet - different types"
                + "\nsecond set contains Integers: " + ss2);

        // test 24
        actual = s1.equals(null);
        showTestResults(actual, false, 24, s1, null, "equals methods SortedSet - other Object is null");

        // CS314 Students. Uncomment this section when ready to
        // run your experiments
        // try {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        // }
        // catch(Exception e) {
        // System.out.println("Unable to change look and feel");
        // }
        // Scanner sc = new Scanner(System.in);
        // String response = "";
        // do {
        // largeTest();
        // System.out.print("Another file? Enter y to do another file: ");
        // response = sc.next();
        // } while( response != null && response.length() > 0
        // && response.substring(0,1).equalsIgnoreCase("y") );

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