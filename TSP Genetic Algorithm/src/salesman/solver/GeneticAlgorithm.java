package salesman.solver;

/**
 * Get the shortest path using a genetic algorithms based on the evolution
 * of a population
 *
 * @author Tiger He
 */
public class GeneticAlgorithm {

    // Preserving the best paths
    // (true and 1 tend to produce best result)
    private static final boolean BEST_PRESERVE = true;
    private static final int NUM_PRESERVE = 1;

    // Mutating all paths
    // (low mutation tends to produce the best result)
    private static final double RATE_MUTATION = 0.0001;

    // Number of paths which compete to mate
    private static final int NUM_PATHS_COMPETE = 5;

    /**
     * Evolve a population to create the consecutive generation
     *
     * @param population population being evolved
     * @return evolved population
     */
    public static Population evolvePop(Population population) {

        // the second generation population
        Population secondGen = new Population(population.numPaths);

        // add the best individual unchanged to next generation
        if (BEST_PRESERVE) {
            for (int i = 0; i < NUM_PRESERVE; i++) {
                secondGen.set(i, population.getFittest());
            }
        }

        // fill the rest of the second generation with offspring paths
        // offspring paths contains paths from both parents
        mate(population, secondGen);

        mutate(secondGen);

        return secondGen;
    }

    /**
     * Fill second generation paths with offspring paths which are built
     * from parent paths in the original population
     *
     * @param population original population
     * @param secondGen second generation population
     */
    private static void mate(Population population, Population secondGen) {
        for (int i = NUM_PRESERVE; i < population.numPaths; i++) {

            // get parents by competing random groups of paths from 
            // original population and allowing those with the best 
            // fitness to reproduce
            Path p1 = compete(population);
            Path p2 = compete(population);

            // create offspring path from parents
            Path pNew = crossover(p1, p2);

            secondGen.set(i, pNew);
        }
    }

    /**
     * Randomly mutate population paths
     *
     * @param secondGen population
     */
    private static void mutate(Population secondGen) {
        for (int i = NUM_PRESERVE; i < secondGen.numPaths; i++) {
            mutate(secondGen.get(i));
        }
    }

    /**
     * Create offspring path from random portions of parent paths
     *
     * @param parent1 first parent path
     * @param parent2 second parent path
     * @return offspring path
     */
    private static Path crossover(Path parent1, Path parent2) {

        // offspring path
        Path offspring = new Path(parent1.numPoints);

        // generate random subpath
        int startPos = (int) (Math.random() * parent1.numPoints);
        int endPos = (int) (Math.random() * parent1.numPoints);
        if (startPos > endPos) {
            int holder;
            holder = startPos;
            startPos = endPos;
            endPos = holder;
        }

        // give subpath of parent1 to offspring
        for (int i = 0; i < parent1.numPoints; i++) {
            if (i > startPos && i < endPos) {
                offspring.set(i, parent1.get(i));
            }
        }

        // use parent2 to fill in the missing points
        for (int i = 0; i < parent2.numPoints; i++) {
            if (!offspring.contains(parent2.get(i))) {
                for (int j = 0; j < offspring.points.size(); j++) {
                    if (offspring.get(j) == null) {
                        offspring.set(j, parent2.get(i));
                        break;
                    }
                }
            }
        }

        return offspring;
    }

    /**
     * Randomly switch points in path
     *
     * @param path path being mutated
     */
    private static void mutate(Path path) {

        // repreat for each point
        for (int pointPos1 = 0; pointPos1 < path.numPoints; pointPos1++) {

            // switch with random point if mutation occurs by chance
            if (Math.random() < RATE_MUTATION) {

                // get random point
                int pointPos2 = (int) (path.numPoints * Math.random());

                // switch points
                Point point1 = path.get(pointPos1);
                Point point2 = path.get(pointPos2);
                path.set(pointPos2, point1);
                path.set(pointPos1, point2);
            }
        }
    }

    /**
     * Get most fit path from a random group of paths from population
     *
     * @param population population of paths
     * @return most fit path in random group
     */
    private static Path compete(Population population) {

        // new smaller population of competing paths
        Population newPop = new Population(NUM_PATHS_COMPETE);

        // add random paths
        for (int i = 0; i < NUM_PATHS_COMPETE; i++) {
            int rand = (int) (Math.random() * population.numPaths);
            newPop.set(i, population.get(rand));
        }

        // return fittest path in group
        Path fittest = newPop.getFittest();
        return fittest;
    }

    // Netbeans made me do this to get rid of a warning
    private GeneticAlgorithm() {
    }
}
