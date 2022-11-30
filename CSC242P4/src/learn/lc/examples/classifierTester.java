package learn.lc.examples;

import java.io.FileNotFoundException;
import learn.lc.core.LogisticClassifier;
import learn.lc.core.PerceptronClassifier;
import learn.lc.core.LinearClassifier;
import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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
    public static void main(String[] args) throws IOException {

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
        
        
        //a -> clean, any step, constant alpha, 
        testingPerceptron("earthquake-clean.data.txt");

        //b -> noisy, any step, any alpha
        // testingPerceptron(filename);

        //c -> noisy, any step, decaying learning alpha
        //testingPerceptron(filename)

	}

    //testing method for perceptron classifier
    public static void testingPerceptron(String filename) throws IOException{
        //if file is earth, call readEarthData
        //else, call readHouseData
        
        if(filename.equals("earthquake-clean.data.txt")){
            
            //-read data from a file
            readData data = new readData(filename);
            ArrayList<Example> dataSet = data.readEarthOrHouseData(filename);
            FileWriter writer = new FileWriter("output.txt");

            //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
            PerceptronClassifier hardThreshold = new PerceptronClassifier(data.inputSize);
            
            DecayingLearningRateSchedule rate = new DecayingLearningRateSchedule();
            
            
            System.out.println("this is for clean dataset any nsteps and any constant alpha");
            hardThreshold.train(dataSet, 700, 1);
            hardThreshold.accuracy(dataSet);

            //train dataset (while printing data needed for making graphs later)
        }else if(filename.equals("earthquake-noidy.data.txt")){

        }else if(filename.equals("house-votes-84.data.num.txt")){

        }else if(filename.equals("house-votes-84.names.num.txt")){

        }else{
            System.out.println("please insert a valid text file");
        }

    }

    //testing method for logistic classifier
    public static void testingLogistic(String filename) throws FileNotFoundException{
    //if file is earth, call readEarthData
    //else, call readHouseData

    //-read data from a file
    // readData data = new readData();
    // data.readEarthOrHouseData(filename);
    // readData data = new readData(fileName);
    // ArrayList<Example> dataSet = data.readEarthData(fileName);


    //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data

    }
    
}
