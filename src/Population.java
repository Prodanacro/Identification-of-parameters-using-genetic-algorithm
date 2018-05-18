import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Population model
 */
public class Population implements Iterable<Individual> {
	private ArrayList<Individual> population;

	public Population(ArrayList<Individual> population) {
		this.population = population;
	}

	/**
	 * Creates population of random generated individuals.
	 * 
	 * @param size
	 *            - size of population
	 * @param paramLoader
	 *            - provides reading parameters from GA configuration file
	 */
	public Population(int size, ParamLoader paramLoader) throws IOException {
		population = new ArrayList<>();
		createRandomPopulation(size, paramLoader);
	}

	public int size() {
		return population.size();
	}

	/*
	 * Returns individual with the biggest fitness.
	 * 
	 * @return individual with the biggest fitness
	 **/
	public Individual getTheBestIndividual() {
		Individual theBestIndividual = population.get(0);

		for (Individual individual : population) {
			if (individual.getFitness() > theBestIndividual.getFitness())
				theBestIndividual = individual;
		}

		return theBestIndividual;
	}

	/**
	 * Returns individual on the given index.
	 * 
	 * @param i
	 *            - index of individual
	 * 
	 * @return - individual on the given index
	 */
	public Individual getIndividual(int i) {
		return population.get(i);
	}

	@Override
	public Iterator<Individual> iterator() {
		return population.iterator();
	}

	/**
	 * Adds individual in population.
	 * 
	 * @param individual
	 *            - individual that needs to be added
	 */
	public void add(Individual individual) {
		population.add(individual);
	}

	/**
	 * Replace individual on given index with the new one.
	 * 
	 * @param index
	 *            - index of
	 */
	public void set(int index, Individual individual) {
		population.set(index, individual);
	}

	public int indexOf(Individual individual) {
		return population.indexOf(individual);
	}

	public void shuffle() {
		Collections.shuffle(population);
	}

	private void createRandomPopulation(int size, ParamLoader paramLoader) throws IOException {
		for (int i = 0; i < size; i++) {
			Individual randomIndividual = new Individual(paramLoader);
			population.add(randomIndividual);
		}
	}

}
