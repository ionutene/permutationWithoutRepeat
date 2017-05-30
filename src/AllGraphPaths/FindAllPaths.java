package AllGraphPaths;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FindAllPaths<T> {

	private final GraphFindAllPaths<T> graph;

	/**
	 * Takes in a graph. This graph should not be changed by the client
	 */
	public FindAllPaths(GraphFindAllPaths<T> graph) {
		if (graph == null) {
			throw new NullPointerException("The input graph cannot be null.");
		}
		this.graph = graph;
	}

	private void validate (T source, T destination) {

		if (source == null) {
			throw new NullPointerException("The source: " + source + " cannot be  null.");
		}
		if (destination == null) {
			throw new NullPointerException("The destination: " + destination + " cannot be  null.");
		}
		if (source.equals(destination)) {
			throw new IllegalArgumentException("The source and destination: " + source + " cannot be the same.");
		}
	}

	/**
	 * Returns the list of paths, where path itself is a list of nodes.
	 *
	 * @param source            the source node
	 * @param destination       the destination node
	 * @return                  List of all paths
	 */
	public List<List<T>> getAllPaths(T source, T destination) {
		validate(source, destination);

		List<List<T>> paths = new ArrayList<List<T>>();
		recursive(source, destination, paths, new LinkedHashSet<T>());
		return paths;
	}

	// so far this dude ignore's cycles.
	private void recursive (T current, T destination, List<List<T>> paths, LinkedHashSet<T> path) {
		path.add(current);

		if (current == destination) {
			paths.add(new ArrayList<T>(path));
			path.remove(current);
			return;
		}

		final Set<T> edges  = graph.edgesFrom(current).keySet();

		for (T t : edges) {
			if (!path.contains(t)) {
				recursive (t, destination, paths, path);
			}
		}

		path.remove(current);
	}

	public static void main(String[] args) {
		GraphFindAllPaths<String> graphFindAllPaths = new GraphFindAllPaths<String>();
		graphFindAllPaths.addNode("A");
		graphFindAllPaths.addNode("B");
		graphFindAllPaths.addNode("C");
		graphFindAllPaths.addNode("D");
		graphFindAllPaths.addNode("E");
		graphFindAllPaths.addNode("F");
		graphFindAllPaths.addNode("G");

		graphFindAllPaths.addEdge("A", "B", 10);
		graphFindAllPaths.addEdge("A", "C", 10);
		graphFindAllPaths.addEdge("A", "G", 10);
		graphFindAllPaths.addEdge("C", "D", 10);
		graphFindAllPaths.addEdge("D", "E", 10);
		graphFindAllPaths.addEdge("E", "F", 10);
		graphFindAllPaths.addEdge("F", "G", 10);

		FindAllPaths<String> findAllPaths = new FindAllPaths<String>(graphFindAllPaths);
		List<List<String>> paths = new ArrayList<List<String>>();

/*		// ABD
		List<String> path1 = new ArrayList<String>();
		path1.add("A"); path1.add("B"); path1.add("D");

		// ABCD
		List<String> path2 = new ArrayList<String>();
		path2.add("A"); path2.add("B"); path2.add("C"); path2.add("D");

		//ACD 
		List<String> path3 = new ArrayList<String>();
		path3.add("A"); path3.add("C"); path3.add("D");

		//ABCD
		List<String> path4 = new ArrayList<String>();
		path4.add("A"); path4.add("C"); path4.add("B"); path4.add("D");

		paths.add(path1);
		paths.add(path2);
		paths.add(path3);
		paths.add(path4);*/

		System.out.println(findAllPaths.getAllPaths("A", "G"));

//		assertEquals(paths, findAllPaths.getAllPaths("A", "D"));
	}


}
