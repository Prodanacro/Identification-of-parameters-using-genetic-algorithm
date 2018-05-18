public class Main {
	public static void main(String[] args) throws Exception {
		GeneticAlgorithm ga = new GeneticAlgorithm();
		Individual result = ga.start();
		System.out.println(result);
	}
}
