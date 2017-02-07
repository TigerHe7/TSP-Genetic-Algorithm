package salesman.solver;

/**
 * Get the shortest path using brute force algorithms
 *
 * @author Tiger He
 */
public class BruteAlgorithm {

    /**
     * Find the shortest path through continuous random generation
     *
     * @param space all points which exist
     * @param tries number of random paths to check
     * @return shortest path
     */
    public static Path bogoBest(Space space, int tries) {

        // best path
        Path bestPath = new Path(space);
        double bestDist = bestPath.getDistance();

        // new path
        Path newPath;

        // repeat predefined amount of times
        // generate new path and save if the shortest so far
        for (int i = 0; i < tries; i++) {
            newPath = new Path(space);
            if (newPath.getDistance() < bestDist) {
                bestPath = newPath;
                bestDist = bestPath.getDistance();
            }
        }

        return bestPath;
    }

    /**
     * Get the shortest path by checking every possibility * NOT WORKING
     * YET*
     *
     * @param space all points which exist
     * @return the shortest path
     */
    public static Path exhaustBest(Space space) {
        return nextPath(new Path(space), space.size);
    }

    /**
     * Get shortest path of all combinations of paths a predefined number
     * of points from the end of the path * NOT WORKING YET*
     *
     * @param path current path of points
     * @param layer number of points from the end of the path
     * @return shortest path
     */
    private static Path nextPath(Path path, int layer) {
        if (layer == 1) {
            return path;
        } else {
            Path bestPath = new Path(path);
            double bestDistance = path.getDistance();
            Point pointHolder = path.get(path.numPoints - layer);
            Path newPath;

            for (int i = path.numPoints - layer; i < path.numPoints; i++) {
                newPath = new Path(path);
                newPath.set(path.numPoints + 1 - layer, path.get(i));
                newPath.set(i, pointHolder);
                newPath = nextPath(newPath, layer - 1);
                if (newPath.getDistance() < bestDistance) {
                    bestPath = new Path(newPath);
                    bestDistance = bestPath.getDistance();
                }
            }

            return bestPath;
        }
    }

}
