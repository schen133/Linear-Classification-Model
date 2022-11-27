package learn.lc.examples;

import learn.lc.core.Example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    public static ArrayList<Example> readEarthData(String filename) throws FileNotFoundException {
        ArrayList<Example> arr = new ArrayList<Example>();

        Scanner scnr = new Scanner(new File(filename));

        while(scnr.hasNext()){
        Example ex = new Example(3);
        


        }
        return arr;

    }

    public static ArrayList<Example> readHouseData(String filename) {
        ArrayList<Example> arr = new ArrayList<Example>();

        return arr;
        
    }


}
