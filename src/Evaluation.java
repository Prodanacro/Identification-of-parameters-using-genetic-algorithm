import java.util.concurrent.ExecutionException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabExecutionException;
import com.mathworks.engine.MatlabSyntaxException;

/**
 * Enables calculating fitness values.
 * */
public class Evaluation {
	Individual best = null;

	/*
	 * Calculates and sets fitness values for whole population.
	 * 
	 * @param population - population for calculating fitness
	 * 
	 * @param matEng - MATLAB engine that is currently connected to Java
	 */
	public void evaluateFitness(Population population, MatlabEngine matEng)
			throws InterruptedException, MatlabExecutionException, MatlabSyntaxException, ExecutionException {

		for (Individual individual : population) {
			// If individual does not have calculated fitness value, evaluate and set that
			// fitness.
			if (individual.getFitness() == null) {
				Double fitness = evaluateFitness(individual, matEng);
				individual.setFitness(fitness);
			}
		}
		return;
	}

	private double evaluateFitness(Individual individual, MatlabEngine matEng)
			throws InterruptedException, MatlabExecutionException, MatlabSyntaxException, ExecutionException {

		// Saving result from MATLAB in new array. Result is a negative fitness value.
		Object[] result = matEng.feval(2, "z_th", individual.getData());

		Double fitness = -(Double) result[0];
		return fitness;
	}

}
