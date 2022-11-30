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
    
        //For earthquake files (both clean and noisy)
        //a -> clean, any step, constant alpha
        testingPerceptron("earthquake-clean.data.txt", 1, 700);

        //b -> noisy, any step, constant alpha
        testingPerceptron("earthquake-noisy.data.txt", 1, 75000);

        //c -> noisy, any step, decaying learning alpha
        testingPerceptron("earthquake-noisy.data.txt", 0, 75000);

        //For house-votes numerical file
        //a -> clean, any step, constant alpha

        //b -> noisy, any step, constant alpha

        //c -> noisy, any step, decaying learning alpha

	}

    //testing method for perceptron classifier
    public static void testingPerceptron(String filename, int alp, int updateNum) throws IOException{
        //if file is earth, call readEarthData
        //else, call readHouseData

        //-read data from a file
        readData data = new readData(filename);
        ArrayList<Example> dataSet = data.readEarthOrHouseData(filename);
        FileWriter writer = new FileWriter("output.txt");

        //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
        PerceptronClassifier hardThreshold = new PerceptronClassifier(data.inputSize);
        
        if(alp>0){    
            System.out.println("this is for clean dataset any nsteps and any constant alpha");

            //train dataset (while printing data needed for making graphs later)
            hardThreshold.train(dataSet, updateNum, alp);
            // hardThreshold.accuracy(dataSet);
        }
        else{
            System.out.println("this is for clean dataset any nsteps and decayingLearningRateSchedule alpha");

            DecayingLearningRateSchedule rate = new DecayingLearningRateSchedule();

            hardThreshold.train(dataSet, updateNum, rate);

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
