import java.io.IOException;

public class Selection {

	/** Three tournament elimination selection.*/
	public Population select(Population population, Crossover crossover, Mutation mutation) throws IOException {
		// Shuffle the population and iterate over groups of three.
		// This simulates random index generation without duplicates.
		population.shuffle();

		for (int i = 0; i < population.size(); i += 3) {
			// If last group has less than three elements, end selection.s
			if (i + 1 == population.size() || i + 2 == population.size()) {
				break;
			}

			Individual i1 = population.getIndividual(i);
			Individual i2 = population.getIndividual(i + 1);
			Individual i3 = population.getIndividual(i + 2);

			// Replace the worst individual with the new one.
			// All individuals are different.
			if (i1.getFitness() < i2.getFitness() && i1.getFitness() < i3.getFitness()) {
				Individual newIndividual = crossover.doCrossover(i2, i3);
				mutation.mutate(newIndividual);
				population.set(i, newIndividual);
			} else if (i2.getFitness() < i1.getFitness() && i2.getFitness() < i3.getFitness()) {
				Individual newIndividual = crossover.doCrossover(i1, i3);
				mutation.mutate(newIndividual);
				population.set(i + 1, newIndividual);
			} else if(i3.getFitness() < i1.getFitness() && i3.getFitness() < i2.getFitness()){
				Individual newIndividual = crossover.doCrossover(i1, i2);
				mutation.mutate(newIndividual);
				population.set(i + 2, newIndividual);
			}
			// Two same individuals.
			else if(i1.getFitness() == i2.getFitness() && i3.getFitness() > i1.getFitness()) {
				Individual newIndividual = crossover.doCrossover(i2, i3);
				mutation.mutate(newIndividual);
				population.set(i, newIndividual);
			}
			else if(i1.getFitness() == i3.getFitness() && i2.getFitness() > i1.getFitness()) {
				Individual newIndividual = crossover.doCrossover(i2, i3);
				mutation.mutate(newIndividual);
				population.set(i, newIndividual);
			}
			else if(i2.getFitness() == i3.getFitness() && i1.getFitness() > i2.getFitness()) {
				Individual newIndividual = crossover.doCrossover(i1, i3);
				mutation.mutate(newIndividual);
				population.set(i + 1, newIndividual);
			}
			// All individual are the same.
			else {
				Individual newIndividual = crossover.doCrossover(i2, i3);
				mutation.mutate(newIndividual);
				population.set(i, newIndividual);
			}

		}

		return population;
	}

}
