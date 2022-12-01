//Created by: Sifeng Chen
//**Linear Classifiers testing file
package learn.lc.examples;
import java.io.FileNotFoundException;
import learn.lc.core.LogisticClassifier;
import learn.lc.core.PerceptronClassifier;
import learn.lc.core.LinearClassifier;
import learn.lc.core.DecayingLearningRateSchedule;
import learn.lc.core.Example;
import java.util.ArrayList;
import java.util.List;
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
        //Text book requirements: 6 graphs total (3 each) AIMA 19.16(HardThreshold) and 19.18(Logistic)
        //a -> clean, any step, constant alpha
        // testingPerceptron("earthquake-clean.data.txt", 1, 700);
        // testingLogistic("earthquake-clean.data.txt", 1, 5000);

        // //b -> noisy, any step, constant alpha
        // testingPerceptron("earthquake-noisy.data.txt", 1, 100000);
        // testingLogistic("earthquake-noisy.data.txt", 1, 100000);

        // //c -> noisy, any step, decaying learning alpha
        // testingPerceptron("earthquake-noisy.data.txt", 0, 100000);
        // testingLogistic("earthquake-noisy.data.txt", 0, 100000);

        // //For house-votes numerical file
        // //6 graphs total: 
        // //a -> Small amount of updates, constant alpha
        // testingPerceptron("house-votes-84.data.num.txt", 1, 700);
        // testingLogistic("house-votes-84.data.num.txt", 1, 5000);

        // //b -> Big amount of updates, constant alpha
        // testingPerceptron("house-votes-84.data.num.txt", 1, 100000);
        // testingLogistic("house-votes-84.data.num.txt", 1, 100000);

        // //c -> Big amount of updates, decaying learning alpha
        // testingPerceptron("house-votes-84.data.num.txt", 0, 100000);
        // testingLogistic("house-votes-84.data.num.txt", 0, 100000);

        readData data = new readData("earthquake-clean.data.txt");
        ArrayList<Example> dataSet = data.readEarthOrHouseData("earthquake-clean.data.txt");
        PerceptronClassifier ht = new PerceptronClassifier(data.inputSize, "TestingFileOutput");
        // ht.train(dataSet, 700, 1);

        // List<Example> testingSet = ht.dataForTest(dataSet, 10);

        

        // List<Example> trainingSet = ht.dataForTrain(dataSet, 10);

        // ht.printArrayOfExamples(testingSet);
        ht.test(dataSet, 10000, 20, 1);
        System.out.println("\n");
        // ht.printArrayOfExamples(trainingSet);

        DecayingLearningRateSchedule rate = new DecayingLearningRateSchedule();

        LogisticClassifier lc = new LogisticClassifier(data.inputSize, "TestingFileOutput");
        lc.test(dataSet, 10000, 20, 1);
        lc.test(dataSet, 10000, 20, rate);
        System.out.println("\n");


        ht.writer.close();

        
	}

    //testing method for perceptron classifier
    public static void testingPerceptron(String filename, int alp, int updateNum) throws IOException{
        //-read data from a file
        readData data = new readData(filename);
        ArrayList<Example> dataSet = data.readEarthOrHouseData(filename);

        if(alp>0){    
            System.out.println("this is for dataset any nsteps and any constant alpha");
            //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
            PerceptronClassifier hardThreshold = new PerceptronClassifier(data.inputSize, "(H)(a:C)OutputConstantAlpha."+updateNum+"."+filename);

            //train dataset (while printing data needed for making graphs later)
            hardThreshold.train(dataSet, updateNum, alp);
            hardThreshold.writer.close();
            // hardThreshold.accuracy(dataSet);
        }
        else{
            System.out.println("this is for dataset any nsteps and decayingLearningRateSchedule alpha");

            //-Create the appropriate type of LinearClassifier with the appropriate number of inputs for the data
            PerceptronClassifier hardThreshold = new PerceptronClassifier(data.inputSize, "(H)(a:D)OutputDecayingAlpha."+updateNum+"."+filename);

            DecayingLearningRateSchedule rate = new DecayingLearningRateSchedule();

            hardThreshold.train(dataSet, updateNum, rate);
            hardThreshold.writer.close();

            }

    }

    //testing method for logistic classifier
    public static void testingLogistic(String filename, int alp, int updateNum) throws IOException{
        readData data = new readData(filename);
        ArrayList<Example> dataSet = data.readEarthOrHouseData(filename);
        // data.printArrayOfExamples(dataSet);
        
        if(alp>0){    
            System.out.println("this is for dataset any nsteps and any constant alpha");

            LogisticClassifier logistic = new LogisticClassifier(data.inputSize, "(L)(a:C)OutputConstantAlpha."+updateNum+"."+filename);

            logistic.train(dataSet, updateNum, alp);
            logistic.writer.close();
            // hardThreshold.accuracy(dataSet);
        }
        else{
            System.out.println("this is for dataset any nsteps and decayingLearningRateSchedule alpha");

            LogisticClassifier logistic = new LogisticClassifier(data.inputSize, "(L)(a:D)OutputDecayingAlpha."+updateNum+"."+filename);

            DecayingLearningRateSchedule rate = new DecayingLearningRateSchedule();

            logistic.train(dataSet, updateNum, rate);
            logistic.writer.close();

            }

    }
    
}
