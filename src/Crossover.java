import java.util.concurrent.ThreadLocalRandom;

public class Crossover {

	/** A single point crossover. */
	public Individual doCrossover(Individual parent1, Individual parent2) {
		int size = parent1.getDataLength();
		double newData[] = new double[size];
		int point = ThreadLocalRandom.current().nextInt(0, size);
		
		if(parent1.equals(parent2)) {
			return parent1;
		}

		for (int i = 0; i < point; i++) {
			newData[i] = parent1.getData(i);
		}

		for (int i = point; i < size; i++) {
			newData[i] = parent2.getData(i);
		}

		return new Individual(newData);
	}
}
