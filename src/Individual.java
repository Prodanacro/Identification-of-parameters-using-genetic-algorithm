import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Structure of one individual.
 */
public class Individual implements Comparator<Individual> {

	private double[] data;
	private Double fitness;

	/**
	 * Creates individual with randomly generated data in specified range.
	 * 
	 * @param paramLoader
	 *            - provides reading parameters from GA configuration file
	 */
	public Individual(ParamLoader paramLoader) {
		int dimensionality = Integer.parseInt(paramLoader.getParameter(Parameter.DIMENSIONALITY));

		double randomData[] = new double[dimensionality];

		double rLowerBound = Double.parseDouble(paramLoader.getParameter(Parameter.R_LOWER_BOUND));
		double rUpperBound = Double.parseDouble(paramLoader.getParameter(Parameter.R_UPPER_BOUND));
		double tLowerBound = Double.parseDouble(paramLoader.getParameter(Parameter.T_LOWER_BOUND));
		double tUpperBound = Double.parseDouble(paramLoader.getParameter(Parameter.T_UPPER_BOUND));

		for (int i = 0; i < dimensionality / 2; i++) {
			randomData[i] = ThreadLocalRandom.current().nextDouble(rLowerBound, rUpperBound);
		}

		for (int i = dimensionality / 2; i < dimensionality; i++) {
			randomData[i] = ThreadLocalRandom.current().nextDouble(tLowerBound, tUpperBound);
		}

		this.data = randomData;
	}

	/**
	 * Creates individual with given data.
	 * 
	 * @param data
	 *            - array of values for calculating fitness
	 */
	public Individual(double[] data) {
		this.data = data;
	}

	public int getDataLength() {
		return data.length;
	}

	/**
	 * Getter for the array that contains data.
	 * 
	 * @return array of data values
	 */
	public double[] getData() {
		return data;
	}

	/**
	 * Getter for the fitness value.
	 * 
	 * @return fitness value
	 */
	public Double getFitness() {
		return fitness;
	}

	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Setter for the the data value on given index.
	 * 
	 * @param data
	 *            - new data value
	 * @param index
	 *            - index of value that needs to be replaced
	 */
	public void setData(double value, int i) {
		data[i] = value;
	}

	@Override
	public String toString() {
		return "data=" + Arrays.toString(data) + ", fitness=" + fitness;
	}

	/**
	 * Returns data value on given index.
	 * 
	 * @return data value
	 * */
	public double getData(int index) {
		return data[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((fitness == null) ? 0 : fitness.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Individual other = (Individual) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (fitness == null) {
			if (other.fitness != null)
				return false;
		} else if (!fitness.equals(other.fitness))
			return false;
		return true;
	}

	/**
	 * Acts identical to the compareTo from the Double class. It uses the fitness
	 * value from first and second individual and compares them lexicographically.
	 * 
	 * @param i1
	 *            - first individual for comparison
	 * @param i2
	 *            - second individual for comparison
	 * @return i1.getFitness().compareTo(i1.getFitness());
	 */
	@Override
	public int compare(Individual i1, Individual i2) {
		return i1.getFitness().compareTo(i1.getFitness());
	}
}
