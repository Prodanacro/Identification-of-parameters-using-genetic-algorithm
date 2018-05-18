import java.nio.file.Paths;
import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

public class GeneticAlgorithm {

	private Evaluation evaluation;
	private Selection selection;
	private Crossover crossover;
	private Mutation mutation;

	private Population population;
	private ParamLoader paramLoader = new ParamLoader(
			Paths.get("P:\\Projects\\Eclipse\\Genetic algorithm\\Parameters.ini"));

	/**
	 * Creates instance of simple genetic algorithm that provides optimization of
	 * transient thermal impedance parameters.
	 */
	public GeneticAlgorithm() throws EngineException, InterruptedException {
		evaluation = new Evaluation();
		crossover = new Crossover();
		selection = new Selection();
		mutation = new Mutation(paramLoader);
	}


	public Individual start() throws Exception {
		int nOfIter = Integer.parseInt(paramLoader.getParameter(Parameter.TOTAL_NUMBER_OF_ITERATIONS));
		int popSize = Integer.parseInt(paramLoader.getParameter(Parameter.POPULATION_SIZE));

		// Generates population with random individuals.
		population = new Population(popSize, paramLoader);

		// Connecting Java to running MATLAB session.
		String[] engines = MatlabEngine.findMatlab();
		MatlabEngine matEng = MatlabEngine.connectMatlab(engines[0]);

		for (int iteration = 1; iteration < nOfIter; iteration++) {
			evaluation.evaluateFitness(population, matEng);
			population = selection.select(population, crossover, mutation);
		}

		// Evaluate fitness one more time because some individuals
		// changed in last iteration without updating their fitness.
		evaluation.evaluateFitness(population, matEng);
		Individual res = population.getTheBestIndividual();

		// Drawing graph for the best individual.
		Object[] result = matEng.feval(2, "z_thBest", res.getData());

		// End MATLAB session.
		matEng.close();

		// Return the best individual from population.
		return res;
	}

}
