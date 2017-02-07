package salesman.solver;

import java.util.LinkedList;

/**
 * A group of paths, all with the same set of points
 *
 * @author Tiger He
 */
public class Population {

    // paths in population
    int numPaths;
    LinkedList<Path> paths;

    /**
     * Create a copy of a population
     *
     * @param p original population
     */
    Population(Population p) {
        this.numPaths = p.numPaths;
        this.paths = new LinkedList<>(p.paths);
    }

    /**
     * Create population of random paths
     *
     * @param space all points which exist
     * @param numPaths number of paths
     */
    Population(Space space, int numPaths) {
        this.numPaths = numPaths;
        this.paths = new LinkedList<>();

        for (int i = 0; i < numPaths; i++) {
            paths.add(new Path(space));
        }
    }

    /**
     * Create empty population
     *
     * @param size number of paths
     */
    Population(int numPaths) {
        this.numPaths = numPaths;
        this.paths = new LinkedList<>();

        for (int i = 0; i < numPaths; i++) {
            this.paths.add(null);
        }
    }

    /**
     * Add path to population
     *
     * @param p path
     */
    public void add(Path p) {
        if (paths.size() + 1 < numPaths) {
            paths.add(p);
        } else {
            System.out.println("Population already full");
        }
    }

    /**
     * Set path at given index
     *
     * @param i index
     * @param p new path
     */
    public void set(int i, Path p) {
        paths.set(i, p);
    }

    /**
     * Get path at given index
     *
     * @param i index
     * @return path at index
     */
    public Path get(int i) {
        return paths.get(i);
    }

    /**
     * @return fittest path in population
     */
    public Path getFittest() {
        Path fittest = this.get(0);

        for (int i = 1; i < numPaths; i++) {
            if (fittest.compareTo(get(i)) < 0) {
                fittest = get(i);
            }
        }

        return fittest;
    }

    /**
     * Print string representation of population to console
     */
    public void print() {
        for (int i = 0; i < numPaths; i++) {
            System.out.println(paths.get(i).toString());
        }
    }

}
