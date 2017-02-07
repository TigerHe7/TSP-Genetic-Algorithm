package salesman.solver;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Path of points in the order that they're visited
 *
 * @author Tiger He
 */
public class Path implements Comparable<Path> {

    // points in path
    int numPoints;
    LinkedList<Point> points;

    // distance to travel through path.
    private double distance;

    // fitness to reproduce inversely proportional to distance
    private double fitness;

    /**
     * Create empty path
     *
     * @param numPoints number of points
     */
    Path(int numPoints) {

        this.numPoints = numPoints;

        this.points = new LinkedList<>();
        for (int i = 0; i < numPoints; i++) {
            points.add(null);
        }

        distance = 0;
        fitness = 0;
    }

    /**
     * Create shuffled version of all points
     *
     * @param space all points to include
     */
    Path(Space space) {

        this.numPoints = space.size;

        this.points = new LinkedList<>(space.points);
        Collections.shuffle(points);

        calcDistance();
        calcFitness();
    }

    /**
     * Create copy of current path
     *
     * @param path original path
     */
    Path(Path path) {

        this.numPoints = path.numPoints;

        this.points = new LinkedList<>(path.points);

        calcDistance();
        calcFitness();
    }

    /**
     * Check if point exists in path
     *
     * @param p point being searched for
     * @return if point exists
     */
    public boolean contains(Point p) {
        return points.contains(p);
    }

    /**
     * Get point at given index
     *
     * @param i index of point
     * @return point at index
     */
    public Point get(int i) {
        return points.get(i);
    }

    /**
     * Replace point at given index
     *
     * @param i index of point
     * @param p replacement point
     */
    public void set(int i, Point p) {
        points.set(i, p);
    }

    /**
     * Return distance between path points
     *
     * @return distance
     */
    public double getDistance() {
        if (distance == 0) {
            calcDistance();
        }
        return distance;
    }

    /**
     * Calculate distance between all points
     */
    private void calcDistance() {
        distance = 0;

        for (int i = 0; i < numPoints - 2; i++) {
            this.distance += points.get(i).distTo(points.get(i + 1));
        }

        this.distance += points.get(numPoints - 1).distTo(points.get(0));
    }

    /**
     * Get fitness of path
     *
     * @return fitness
     */
    public double getFitness() {
        if (fitness == 0) {
            calcFitness();
        }
        return fitness;
    }

    /**
     * Calculate fitness of path
     */
    private void calcFitness() {
        fitness = 1 / getDistance();
    }

    /** 
     * @return String representation of path
     */
    @Override
    public String toString() {
        return this.points.toString();
    }
    
    /**
     * Compare fitness of paths
     * 
     * @param p path being compared to
     * @return int (-1 if less fit, +1 if more, 0 if equal)
     */
    @Override
    public int compareTo(Path p) {
        if (this.getFitness() < p.getFitness()) {
            return -1;
        } else if (this.getFitness() > p.getFitness()) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     private void shuffle() {
     LinkedList<Point> newPoints = new LinkedList<>();
     int pos;

     for (int i = 0; i < this.numPoints; i++) {
     pos = (int) (Math.random() * (this.points.size()));
     newPoints.add(this.points.get(pos));
     this.points.remove(pos);
     }

     this.points = newPoints;
     }
     */
}
