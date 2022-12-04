//Created by: Sifeng Chen
//**Data reading file
//package learn.lc.examples;
//import learn.lc.core.Example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Required testing files:
//earthquake clean
//earthquake noisy
//house votes numerical version
public class readData {
    //for Earthquake data:
    //        6.1,4.9,0
    //        5.3,4.4,1
    //what we want:
    //              1.0,5.3,4.4,1
    //Array index   0   1   2
    //The end of the data input is the output
    //examples have input array and outputs
    //input[], output

    protected int inputSize;

    //when scanning a file with name
    //constructor will set needed variables for classifier to use later
    public readData(String filename) throws FileNotFoundException {

    ArrayList<Example> arr = readEarthOrHouseData(filename);
    inputSize = arr.get(0).inputs.length;
    
    }

    public ArrayList<Example> readEarthData(String filename) throws FileNotFoundException {
        ArrayList<Example> arr = new ArrayList<Example>();

        Scanner scnr = new Scanner(new File(filename));

        while(scnr.hasNext()){
        //create new Example instance called exam
        Example exam = new Example(3);
        String input = scnr.nextLine();
        String[] doubArray = input.split(",");

        //sets exam's input array and output
        exam.inputs[0] = 1.0;
        //first double of the input from file
        exam.inputs[1] = Double.parseDouble(doubArray[0]);
        //second double of the input
        exam.inputs[2] = Double.parseDouble(doubArray[1]);
        //third double of the input
        exam.output = Double.parseDouble(doubArray[2]);
        
        //add example to arrayList of examples
        arr.add(exam);
        }

        return arr;
    }

    public ArrayList<Example> readEarthOrHouseData(String filename) throws FileNotFoundException {

        ArrayList<Example> arr = new ArrayList<Example>();
        Scanner scnr = new Scanner(new File(filename));

        while(scnr.hasNext()){
            //create new Example instance called exam
            String input = scnr.nextLine();
            
            //make an input array that separates input string by , 
            String[] doubArray = input.split(",");

            //create new exam for each line of data file, exam's input array size will be the length, because 
            //although it should be length - 1 because output is not in the input array, but since later
            //we will have to add x_0 = 1.0 to exam's input array anyway. It's the same size still
            Example exam = new Example(doubArray.length);

            //For each line in the earth data file, we set exam's first index to 1.0
            //x_0 = 1.0 always
            exam.inputs[0] = 1.0;

            //then for the rest of the exam's array, we will read through the input array (doubArray)
            //goes through every line from start till the output value
            for(int i=0; i<doubArray.length-1; i++) {
            //first double of the input from file
            exam.inputs[i+1] = Double.parseDouble(doubArray[i]);
            //second double of the input
            //third double of the input
            }
            
            //output is the end of the doubleArray
            exam.output = Double.parseDouble(doubArray[doubArray.length-1]);

            //add example to arrayList of examples
            arr.add(exam);

            }

        return arr;
        
    }

    public static void printArrayOfExamples(ArrayList<Example> array) {
    
    for(Example e: array) {
    System.out.println(e);
    }

    }

    public static void main(String[] args) throws FileNotFoundException {
    
    // ArrayList<Example> array = readEarthData("earthquake-clean.data.txt");

    // ArrayList<Example> arrForEarth = readEarthOrHouseData("earthquake-clean.data.txt");

    // ArrayList<Example> arrForHouse = readEarthOrHouseData("house-votes-84.data.num.txt");

    // printArrayOfExamples(arrForEarth);
    // System.out.println("GAPPP");
    // printArrayOfExamples(arrForHouse);

    }


}
