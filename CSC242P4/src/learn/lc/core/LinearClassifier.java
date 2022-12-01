package learn.lc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import learn.math.util.VectorOps;
import java.io.FileWriter;
import java.io.IOException;

abstract public class LinearClassifier {
	
	public double[] weights;

	//constructor declares this writer as a new instance with a output file name
	public FileWriter writer;
	
	public LinearClassifier(double[] weights) {
		this.weights = weights;
	}
	
	public LinearClassifier(int ninputs, String outputFile) throws IOException {
		this(new double[ninputs]);
		writer = new FileWriter(outputFile);
	}
	
	/**
	 * Update the weights of this LinearClassifer using the given
	 * inputs/output example and learning rate alpha.
	 */
	abstract public void update(double[] x, double y, double alpha);

	/**
	 * Threshold the given value using this LinearClassifier's
	 * threshold function.
	 */
	abstract public double threshold(double z);

	/**
	 * Evaluate the given input vector using this LinearClassifier
	 * and return the output value.
	 * This value is: Threshold(w \cdot x)
	 */
	public double eval(double[] x) {
		return threshold(VectorOps.dot(this.weights, x));
	}
	
	/**
	 * Train this LinearClassifier on the given Examples for the
	 * given number of steps, using given learning rate schedule.
	 * ``Typically the learning rule is applied one example at a time,
	 * choosing examples at random (as in stochastic gradient descent).''
	 * See AIMA p. 724.
	 * @throws IOException
	 */
	public void train(List<Example> examples, int nsteps, LearningRateSchedule schedule) throws IOException {
		Random random = new Random();
		int n = examples.size();
		for (int i=1; i <= nsteps; i++) {
			int j = random.nextInt(n);
			Example ex = examples.get(j);
			this.update(ex.inputs, ex.output, schedule.alpha(i));
			this.trainingReport(examples, i,  nsteps);
		}
	}

	/**
	 * Train this LinearClassifier on the given Examples for the
	 * given number of steps, using given constant learning rate.
	 * @throws IOException
	 */
	public void train(List<Example> examples, int nsteps, double constant_alpha) throws IOException {
		train(examples, nsteps, new LearningRateSchedule() {
			public double alpha(int t) { return constant_alpha; }
		});
	}

	//almost the same as train method, but we cut portion of data set
	//for testing a portion of dataset on the trained model or function, use eval to compare acuracy (weights array)
	//testingPortion must be an int ranged (0 < testingPortion < 100), it's for testing dataset size
	public void test(List<Example> examples, int nsteps, int testingPortion, LearningRateSchedule schedule) throws IOException {
		List<Example> testSet = dataForTest(examples, testingPortion);
		List<Example> trainSet = dataForTrain(examples, testingPortion);

		Random random = new Random();
		int n = trainSet.size();
		//let it train using trainingset
		for (int i=1; i <= nsteps; i++) {
			int j = random.nextInt(n);
			Example ex = trainSet.get(j);
			this.update(ex.inputs, ex.output, schedule.alpha(i));
		}

		System.out.println("Size of total dataset: " + examples.size() );
		System.out.println("Size of testing dataset: " + testSet.size() );
		System.out.println("Size of training dataset: " + trainSet.size() );

		System.out.println("DataSet for testing examples is about " + testingPortion + "% of Dataset of total amount of examples");
		System.out.println("DataSet for training examples is about " + (100-testingPortion) + "% of Dataset of total amount of examples");


		System.out.println("Accuracy after final update (" +nsteps+" updates)" + " using trainingset: ");
		this.trainingReport(examples, nsteps,  nsteps);
		this.testingReport(testSet, nsteps, nsteps);

	}

	public void test(List<Example> examples, int nsteps, int testingPortion, double constant_alpha) throws IOException {
		test(examples, nsteps, testingPortion, new LearningRateSchedule() {
			public double alpha(int t) { return constant_alpha; }
		});
	}

	//returns a ArrayList that's a percentage of total dataSet for testing purpose
	public List<Example> dataForTest(List<Example> examples, int portionPercent){
		//cut example array to half, ex: if we want 20% of data for testing
		//Take 20% of data from start index to middle of example array
		//And 20% of data from middle index to end of other half of example array

		//break example array to two arrays
		int middleIndex = examples.size()/2;

		System.out.println("This is final index of example array " + (examples.size()-1));
		System.out.println("This is middle index of example array " + middleIndex);

		List<Example> t1 =  new ArrayList<Example>();
		List<Example> t2 =  new ArrayList<Example>();

		for(int i=0; i<examples.size(); i++){
			if(i<middleIndex){
				t1.add(examples.get(i));
			}
			else{
				t2.add(examples.get(i));
			}
		}

		System.out.println("This is first half of examples");
		printArrayOfExamples(t1);
		System.out.println("This is second half of examples");
		printArrayOfExamples(t2);

		System.out.println("t1's size: " + t1.size());
		System.out.println("t2's size: " + t2.size());

		//Take a percentage of data from both arrays, then add them both Testing array (List for testing data)
		double portion = portionPercent/100.00;

		System.out.println("This is the int value of the percentage portion " + portionPercent);
		System.out.println("This is the decimal value of the percentage portion " + portion);

		// System.out.println("This is the")
		List<Example> testing = new ArrayList<Example>();
		t1 = t1.subList(0, (int)(t1.size()*(portion)));
		t2 = t2.subList(0, (int)(t2.size()*(portion)));
		System.out.println("This is a portion taken out of t1");
		printArrayOfExamples(t1);
		System.out.println("This is a portion taken out of t2");
		printArrayOfExamples(t2);

		for(Example ex: t1){
			testing.add(ex);
		}
		for(Example ex: t2){
			testing.add(ex);
		}

		System.out.println("This is final dataSet for testing examples for about " + portionPercent + "% of total dataset of examples");
		printArrayOfExamples(testing);

		return testing;
	}

	//returns a ArrayList that's a percentage of total dataSet for training purpose
	public List<Example> dataForTrain(List<Example> examples, int portionPercent){
		//get the testing data set
		List<Example> testing = dataForTest(examples, portionPercent);

		List<Example> training = new ArrayList<Example>();
		//if testing does not contain a specific example from total dataset (examples), then add that example to training dataset
		//in a way, we include any example that's not inside the testing dataset to training dataset
		//This way, training set avoids having any examples that are in the dataSet
		//(Questions on exam are never seen within pratice questions)
		for(Example ex: examples){
			if(!testing.contains(ex)){
				training.add(ex);
			}
		}

		// System.out.println("This is final dataSet for training examples for about " + (100-portionPercent) + "% of total dataset of examples");
		printArrayOfExamples(training);
		// System.out.println("Size of training dataset: " + training.size() );

		return training;
	}


	/**
	 * This method is called after each weight update during training.
	 * Subclasses can override it to gather statistics or update displays.
	 * @throws IOException
	 */
	protected void trainingReport(List<Example> examples, int stepnum, int nsteps) throws IOException {
		// System.out.println(stepnum + "\t" + accuracy(examples));
		//for logistic
		// System.out.println(stepnum + "\t" + (1.0-squaredErrorPerSample(examples)));
		//writing step and accuracy per update onto a output file
		// try{
		// this.writer.write(stepnum + " " + accuracy(examples) + "\n");
		// } catch(IOException e){
		// 	System.out.println("And error accured");
		// }
	}

	protected void testingReport(List<Example> examples, int stepnum, int nsteps) throws IOException {
		
	}
	
	/**
	 * Return the squared error per example (Mean Squared Error) for this
	 * LinearClassifier on the given Examples.
	 * The Mean Squared Error is the total L_2 loss divided by the number
	 * of samples.
	 */
	public double squaredErrorPerSample(List<Example> examples) {
		double sum = 0.0;
		for (Example ex : examples) {
			double result = eval(ex.inputs);
			double error = ex.output - result;
			sum += error*error;
		}
		return sum / examples.size();
	}

	/**
	 * Return the proportion of the given Examples that are classified
	 * correctly by this LinearClassifier.
	 * This is probably only meaningful for classifiers that use
	 * a hard threshold. Use with care.
	 */
	public double accuracy(List<Example> examples) {
		int ncorrect = 0;
		for (Example ex : examples) {
			double result = eval(ex.inputs);
			if (result == ex.output) {
				ncorrect += 1;
			}
		}
		return (double)ncorrect / examples.size();
	}

	public void printArrayOfExamples(List<Example> array) {
    
		for(Example e: array) {
		System.out.println(e);
		}
	
		}

}
