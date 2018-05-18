import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/** Enables loading GA parameters from configuration file.*/
public class ParamLoader {
	private Path path;
	private HashMap<Parameter, String> parameters;

	/**
	 * Loads parameters of genetic algorithm from given path.
	 * */
	public ParamLoader(Path path) {
		parameters = new HashMap<>();
		this.path = path;
		try {
			loadParameters();
		} catch (IOException e) {
			throw new Error("Failed to load parameters.");
		}
	}

	/**
	 * Getter for String representation of the parameter.
	 * 
	 * @return String representation of parameter
	 */
	public String getParameter(Parameter p) {
		return parameters.get(p);
	}

	/**
	 * Loads parameters from configuration file.
	 * */
	private void loadParameters() throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(Files.newInputStream(path))));

		do {
			String line = reader.readLine();
			if (line == null || line.equals("")) {
				break;
			}
			if (line.startsWith("--") || line.startsWith(" ")) {
				continue;
			}

			String[] tokens = line.trim().split("\\s+");
			parameters.put(Parameter.valueOf(tokens[0]), tokens[1]);
		} while (true);

	}

}
