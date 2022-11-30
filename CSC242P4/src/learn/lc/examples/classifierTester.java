package learn.lc.examples;

import java.io.FileNotFoundException;
import learn.lc.core.LogisticClassifier;
import learn.lc.core.PerceptronClassifier;
import learn.lc.core.LinearClassifier;
import learn.lc.core.Example;
import java.util.ArrayList;

// Running a LinearClassifier should generally do the following:
//I will be doing all below steps within each testing methods
// - Read the data from a file
// - Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
// - Train the classifier on the data
//   - Printing the information needed for graphs in your report after each step

// Examples of what you might want to print (and then include in your report, usually as graphs):
// - final accuracy on the training data, the testing data, or both (separately)
// - per-step accuracy suitable for plotting in a learning curve

public class classifierTester {
    public static void main(String[] args) {

        //within main method, ask user which classifier they want to test
        //with whatever the user's choices are, we call the corresponding
        //Availiable files are:
        //earthquake clean and noisy
        //house votes numerical


        //call testing methods to test for classifiers
        //3 files
        //testingLogistic(filename)
        //testingLogistic(filename)
        //testingLogistic(filename)

        //3 files
        //testingPerceptron(filename)
        //testingPerceptron(filename)
        //testingPerceptron(filename)

	}

    //testing method for perceptron classifier (Hard Threshold)
    public static void testingPerceptron(String filename) throws FileNotFoundException{
        //if file is earth, call readEarthData
        //else, call readHouseData

        //-read data from a file
        readData data = new readData(filename);
        ArrayList<Example> dataSet = data.readEarthOrHouseData(filename);

        //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
        PerceptronClassifier hardThreshold = new PerceptronClassifier(data.inputSize);
        
        for(Example ex: dataSet){
            
        }

        //train dataset (while printing data needed for making graphs later)




    }

    //testing method for logistic classifier
    public static void testingLogistic(String filename) throws FileNotFoundException{
    //if file is earth, call readEarthData
    //else, call readHouseData

    //-read data from a file
    // readData data = new readData();
    // data.readEarthOrHouseData(filename);

    //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data

    }
    
}
