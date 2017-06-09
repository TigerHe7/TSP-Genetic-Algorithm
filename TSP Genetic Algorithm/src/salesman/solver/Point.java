package salesman.solver;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Point representing a 2D location
 */
public class Point {

    private double x;
    private double y;

    // rand point between bound1 and bound2
    public Point(Point bound1, Point bound2) {

        // set random x value
        this.x = Math.random() * (bound2.x - bound1.x) + bound1.x;
        this.y = Math.random() * (bound2.y - bound1.y) + bound1.y;
    }

    // specific point
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // dist to second point
    public double distTo(Point point) {
        double xDist = Math.abs(this.getY() - point.getY());
        double yDist = Math.abs(this.getY() - point.getY());

        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    // if points match
    public boolean equals(Point p) {
        return this.getX() == p.getX() && this.getY() == p.getY();
    }

    // round double to given decimal places
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
        return "|" + round(getX(), 2) + ", " + round(getY(), 2) + "|";
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
