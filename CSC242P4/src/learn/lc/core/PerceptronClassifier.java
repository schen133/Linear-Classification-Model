package learn.lc.core;

import learn.math.util.VectorOps;

public class PerceptronClassifier extends LinearClassifier {
	
	public PerceptronClassifier(double[] weights) {
		super(weights);
	}
	
	public PerceptronClassifier(int ninputs) {
		super(ninputs);
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
		double wxproduct = VectorOps.dot(w,x);
		double hw_x = threshold(wxproduct);
		
		for(int i = 0; i<w.length; i++) {
			w[i] = w[i] + ((alpha * (y-hw_x)) * x[i]);
		}

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
	
}
