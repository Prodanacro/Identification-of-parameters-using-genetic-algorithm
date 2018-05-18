import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mutation {
	private double mutationProbability;
	private double rUpperBound;
	private double rLowerBound;
	private double tUpperBound;
	private double tLowerBound;
	private double rStandardDev;
	private double tStandardDev;

	public Mutation(ParamLoader paramLoader) {
		mutationProbability = Double.parseDouble(paramLoader.getParameter(Parameter.MUTATION_PROBABILITY));
		rLowerBound = Double.parseDouble(paramLoader.getParameter(Parameter.R_LOWER_BOUND));
		rUpperBound = Double.parseDouble(paramLoader.getParameter(Parameter.R_UPPER_BOUND));
		tLowerBound = Double.parseDouble(paramLoader.getParameter(Parameter.T_LOWER_BOUND));
		tUpperBound = Double.parseDouble(paramLoader.getParameter(Parameter.T_UPPER_BOUND));
		rStandardDev = Double.parseDouble(paramLoader.getParameter(Parameter.R_STANDARD_DEV));
		tStandardDev = Double.parseDouble(paramLoader.getParameter(Parameter.T_STANDARD_DEV));
	}

	/** Gaussian mutation.*/
	public void mutate(Individual individual) {
		Random random = new Random();
		double newValue;

		// Mutation for thermal resistance.
		for (int i = 0; i < individual.getDataLength() / 2; i++) {
			double randomNumber = ThreadLocalRandom.current().nextDouble(0, 1);
			if (randomNumber <= mutationProbability) {
				do {
					// Add Gaussian random value.
					newValue = individual.getData(i) + random.nextGaussian() * rStandardDev;
					individual.setData(newValue, i);
				} while (!(newValue > rLowerBound && newValue < rUpperBound));
			}
		}
		
		// Mutation for time constant.
		for (int i = individual.getDataLength() / 2; i < individual.getDataLength(); i++) {
			double randomNumber = ThreadLocalRandom.current().nextDouble(0, 1);
			if (randomNumber <= mutationProbability) {
				do {
					// Add Gaussian random value.
					newValue = individual.getData(i) + random.nextGaussian() * tStandardDev;
					individual.setData(newValue, i);
				} while (!(newValue > tLowerBound && newValue < tUpperBound));
			}
		}
	}
}
