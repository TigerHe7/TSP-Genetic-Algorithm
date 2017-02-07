package salesman.solver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Point representing a 2D location
 *
 * @author Tiger He
 */
public class Point {

    // coordinates
    double x;
    double y;

    /**
     * Create random point within two bounds
     *
     * @param bound1 first bound
     * @param bound2 second bound
     */
    public Point(Point bound1, Point bound2) {

        // set random x value
        if (bound1.x < bound2.x) {
            this.x = Math.random() * (bound2.x - bound1.x) + bound1.x;
        } else if (bound1.x > bound2.x) {
            this.x = Math.random() * (bound1.x - bound2.x) + bound2.x;
        } else {
            this.x = bound1.x;
        }

        // set random y value
        if (bound1.y < bound2.y) {
            this.y = Math.random() * (bound2.y - bound1.y) + bound1.y;
        } else if (bound1.y > bound2.y) {
            this.y = Math.random() * (bound1.y - bound2.y) + bound2.y;
        } else {
            this.y = bound1.y;
        }
    }

    /**
     * Create new point
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get distance from this point to another
     *
     * @param point point being compared to
     * @return distance between points
     */
    public double distTo(Point point) {

        double xDist = Math.abs(this.y - point.y);
        double yDist = Math.abs(this.y - point.y);

        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    /**
     * Check if points match
     *
     * @param p point being checked with
     * @return if points match
     */
    public boolean equals(Point p) {
        return this.x == p.x && this.y == p.y;
    }

    /**
     * Round double to given decimal places
     *
     * @param value double value
     * @param places number of decimal places
     * @return rounded double
     */
    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * @return a string representation of the point
     */
    @Override
    public String toString() {
        return "|" + round(x, 2) + ", " + round(y, 2) + "|";
    }

    /*
     public Point(String pointStr) {

     Scanner getLocation = new Scanner(pointStr);

     this.y = getLocation.nextDouble();
     this.y = getLocation.nextDouble();

     getLocation.close();
     }
     */
}
