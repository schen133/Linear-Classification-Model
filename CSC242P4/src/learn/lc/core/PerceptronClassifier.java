package learn.lc.core;

import java.io.IOException;
import java.util.List;

import learn.math.util.VectorOps;

public class PerceptronClassifier extends LinearClassifier {
	
	public PerceptronClassifier(double[] weights) {
		super(weights);
	}
	
	public PerceptronClassifier(int ninputs, String output) throws IOException {
		super(ninputs, output);
		
	}
	
	/**
	 * A PerceptronClassifier uses the perceptron learning rule
	 * (AIMA Eq. 18.7): w_i <-- w_i+\alpha(y-h_w(x)) \times x_i 
	 */
	//Perceptron learning rule
	public void update(double[] x, double y, double alpha) {
		// This must be implemented by you
		double [] w = this.weights;

		//hw(x) = Threshold(w * x) where Threshold(z) = 1 if z>=0 and 0 otherwise
		//product of w and x
		// double wxproduct = VectorOps.dot(w,x);
		// double hw_x = threshold(wxproduct);
		double hw_x = eval(x);
		
		for(int i = 0; i<w.length; i++) {
			w[i] = w[i] + ((alpha * (y-hw_x)) * x[i]);
		}

		//update weigths' array finally
		this.weights = w;

	}
	
	/**
	 * A PerceptronClassifier uses a hard 0/1 threshold.
	 */
	//nondifferentiable at z=0, other wise, 0 or 1
	public double threshold(double z) {
		// This must be implemented by you
		//Threshold(z) = 1 if z>=0 and 0 otherwise
		if(z>=0){
			return 1;
		}
		else {
			return  0;
		}
	}

	@Override
	protected void trainingReport(List<Example> examples, int stepnum, int nsteps) throws IOException {
		System.out.println(stepnum + "\t" + accuracy(examples));
		//for logistic
	
		// writing step and accuracy per update onto a output file
		try{
		this.writer.write(stepnum + " " + accuracy(examples) + "\n");
		} catch(IOException e){
			System.out.println("And error accured");
		}

	}

	@Override
	protected void testingReport(List<Example> examples, int stepnum, int nsteps) throws IOException {
	System.out.println("Accuracy of testing set examples plugged into equation of weights array after training");
	System.out.println(stepnum + "\t" + accuracy(examples));

	}
	
}
