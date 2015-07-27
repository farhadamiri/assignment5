/**
 * Farhad Amiri date: 7/21/2015 class 202 Purpose: Spell Checking with My Binary
 * Search Tree.
 */
package assignment5;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Farhad
 */
public class assignment5 {
//Declaring global Attributes

    long wrongwords = 0;
    long rightwords = 0;
    double cRec = 0;
    double iRec = 0;
    long totalwords = 0;

    /**
     * Default Constructor
     */
    public assignment5() {
    }// Default Constractor

    /**
     * @ require: null Ensure:out put the file/ calling method and measure the
     * time
     * @param args
     */
    public static void main(String[] args) {
        assignment5 object = new assignment5();
        long startTi = System.nanoTime(); //start clock
        BinarySearchTree<String>[] dictionary = new BinarySearchTree[26];
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<>();
        }// for
        object.readFile(dictionary);
        object.readolive(dictionary);
        object.results();
        long ends = System.nanoTime();
        System.out.println("Time in Nano Second " + (ends - startTi) + "\t");

    }// main

    /**
     * @param dictionary
     * @require: data text file
     * @ensure: open the file and import the file in Binary Search Tree Array
     * ASCII key word
     * @ throws IO Exception
     */
    public void readFile(BinarySearchTree dictionary[]) {

        File f = new File("random_dictionary.txt");
        try {
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String line = input.nextLine().trim();
                // System.out.println( " all Word "+line);
                dictionary[(int) line.toLowerCase().charAt(0) - 97].insert(line);
            }// while
            input.close();

        }//try
        catch (Exception e) {
        }// Catch
    }// readFile

    /**
     * @require: linear search
     * @ensure: search
     * @param dictionary
     * @param line
     * @return true
     */
    public boolean SearchWord(BinarySearchTree dictionary[], String line) {
        return dictionary[line.toLowerCase().charAt(0) - 97].search(line);

    }// linear search

    /**
     *
     * @require: read the text file and load the search method
     * @ensure: comparing dictionary with text file to find correct quantity
     * @param dictionary
     * @ throws IO Exception
     */
    public void readolive(BinarySearchTree dictionary[]) {

        try {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String str = ""; //word to be processed
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);

                }// if statement
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    totalwords += 1;

                    if (BinarySearchTree.searchstring(str, dictionary[str.toLowerCase().charAt(0) - 97])) {
                        rightwords++;

                        //  System.out.println(str + " right words are ");
                        cRec += dictionary[str.toLowerCase().charAt(0) - 97].getcompare();
                    } else {
                        //System.out.println(str + " misseplled");
                        wrongwords++;
                        iRec += dictionary[str.charAt(0) - 97].getcompare();

                    }// else statement 
                    str = "";

                }// if statement 
            }// while loop
            inf.close();

        } // try  
        catch (IOException ex) {
            System.out.println("Not read the olive");
        }// Exception 

    }// read Olive

    /**
     * @ require: null Ensure:out put correctly the results for the users
     *
     */
    public void results() {
        System.out.println("Total words " + (totalwords));
        System.out.println("Number of right words are " + (rightwords));
        System.out.println("Number of Incorrect words are " + wrongwords);
        System.out.println("The average comparisons for words found:  "+ cRec / rightwords);
        System.out.println("The average comparisons for words not found: " + iRec / wrongwords);
        System.out.println("Number of comparsions for words found "+ cRec);
        System.out.println("Number of Comparsions for words not found " + iRec);
        int result = (int) (cRec + iRec);
        System.out.println("Total number of comparisons is:  " + (result));
    }// results

}// class

/*
 run:
 Total words 992140
 Number of right words are 925140
 Number of Incorrect words are 67000
 The average comparisons for words found:  16.429820351514365
 The average comparisons for words not found: 14.662074626865671
 Total number of comparisons is:  16182243
 Time in Nano Second 14202435328	
 BUILD SUCCESSFUL (total time: 14 seconds)
 */
