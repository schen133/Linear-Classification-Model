package learn.lc.core;

import java.io.IOException;
import java.util.List;

import learn.math.util.VectorOps;

public class LogisticClassifier extends LinearClassifier {
	
	public LogisticClassifier(double[] weights) {
		super(weights);
	}
	
	public LogisticClassifier(int ninputs, String output) throws IOException {
		super(ninputs, output);
	}

	/**
	 * A LogisticClassifier uses the logistic update rule
	 * (AIMA Eq. 18.8): w_i \leftarrow w_i+\alpha(y-h_w(x)) \times h_w(x)(1-h_w(x)) \times x_i 
	 */
	public void update(double[] x, double y, double alpha) {
		// This must be implemented by you
		double [] w = this.weights;

		//hw_x = logistic(w*x), where logistic(z) = 1/(1+e^(-z))
		double hw_x = eval(x);

		for(int i = 0; i < w.length; i++) {
			//wi <-- wi + a(y-hw_x) * hwx(1-hwx) * xi
			w[i] = w[i] + (alpha * (y-hw_x)) * (hw_x * (1-hw_x)) * x[i];
		}
		this.weights = w;
		
	}
	
	/**
	 * A LogisticClassifier uses a 0/1 sigmoid threshold at z=0.
	 */
	//hard 
	public double threshold(double z) {
		// This must be implemented by you
		//logistic(z) = 1/(1+e^(-z))
		//Math.exp(x) = e^(x)
		return 1/(1+Math.exp(-z));
	}

	@Override
	protected void trainingReport(List<Example> examples, int stepnum, int nsteps) throws IOException {
		System.out.println(stepnum + "\t" + (1.0-squaredErrorPerSample(examples)));

		// writing step and accuracy per update onto a output file
		try{
		this.writer.write(stepnum + " " + (1.0-squaredErrorPerSample(examples)) + "\n");
		} catch(IOException e){
			System.out.println("And error accured");
		}

	}

}
