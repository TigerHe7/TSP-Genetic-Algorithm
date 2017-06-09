package salesman.solver;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Path of points in the order that they're visited
 */
public class Path implements Comparable<Path> {

    // points in path
    int nPoints;
    LinkedList<Point> points;

    // length of path.
    private double distance;

    // repre fitness inv prop to distance
    private double fitness;

    // empty path
    Path(int numPoints) {

        this.nPoints = numPoints;

        this.points = new LinkedList<>();
        for (int i = 0; i < numPoints; i++) {
            points.add(null);
        }

        distance = 0;
        fitness = 0;
    }

    // path from shuffled version of all points in space
    Path(Space space) {
        this.nPoints = space.size;
        this.points = new LinkedList<>(space.points);
        Collections.shuffle(points);
        calcDistance();
        fitness = 1 / this.distance;
    }

    // copy of current path
    Path(Path path) {
        this.nPoints = path.nPoints;
        this.points = new LinkedList<>(path.points);
        this.distance = path.distance;
        this.fitness = path.fitness;
    }

    // check if path contains point
    public boolean contains(Point p) {
        return points.contains(p);
    }

    // get point at index 
    public Point get(int i) {
        return points.get(i);
    }

    // set point at index
    public void set(int i, Point p) {
        points.set(i, p);
    }

    // return distance
    public double getDistance() {
        if (distance == 0) {
            calcDistance();
        }
        return distance;
    }

    // calc distance
    private void calcDistance() {
        assert this.nPoints >= 3;
        distance = 0;

        for (int i = 0; i < nPoints - 2; i++) {
            this.distance += points.get(i).distTo(points.get(i + 1));
        }
        this.distance += points.get(nPoints - 1).distTo(points.get(0));
    }

    // get fitness of class
    public double getFitness() {
        if (fitness == 0) {
            this.fitness = 1 / getDistance();
        }
        return fitness;
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
        if (this.getFitness() < p.getFitness()) 
            return -1;
        else if (this.getFitness() > p.getFitness()) 
            return 1;
        else 
            return 0;
    }

    /*
     private void shuffle() {
     LinkedList<Point> newPoints = new LinkedList<>();
     int pos;

     for (int i = 0; i < this.nPoints; i++) {
     pos = (int) (Math.random() * (this.points.size()));
     newPoints.add(this.points.get(pos));
     this.points.remove(pos);
     }

     this.points = newPoints;
     }
     */
}
