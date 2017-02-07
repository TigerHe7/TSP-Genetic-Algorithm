package Unused;

import java.util.Scanner;

/**
 * 3D points needed to be traveled to.
 *
 * @author Tiger He
 */
public class Point3D {

    double x;
    double y;
    double z;

    public Point3D(Point3D bound1, Point3D bound2) {

        if (bound1.x < bound2.x) {
            this.x = Math.random() * (bound2.x - bound1.x) + bound1.x;
        } else if (bound1.x > bound2.x) {
            this.x = Math.random() * (bound1.x - bound2.x) + bound2.x;
        } else {
            this.x = bound1.x;
        }

        if (bound1.y < bound2.y) {
            this.y = Math.random() * (bound2.y - bound1.y) + bound1.y;
        } else if (bound1.y > bound2.y) {
            this.y = Math.random() * (bound1.y - bound2.y) + bound2.y;
        } else {
            this.y = bound1.y;
        }

        if (bound1.z < bound2.z) {
            this.z = Math.random() * (bound2.z - bound1.z) + bound1.z;
        } else if (bound1.z > bound2.z) {
            this.z = Math.random() * (bound1.z - bound2.z) + bound2.z;
        } else {
            this.z = bound1.z;
        }
    }

    /**
     * Create new node
     *
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        this.y = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(String pointLine) {

        Scanner getLocation = new Scanner(pointLine);

        this.y = getLocation.nextDouble();
        this.y = getLocation.nextDouble();
        this.z = getLocation.nextDouble();

        getLocation.close();

    }

    /**
     * Get distance from this point to another
     *
     * @param p point being compared to
     * @return distance between points
     */
    public double dist(Point3D p) {

        double xDist = Math.abs(this.y - p.y);
        double yDist = Math.abs(this.y - p.y);
        double zDist = Math.abs(this.z - p.z);

        return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
    }

    @Override
    public String toString () {
        return x + ", " + y + ", " + z;
    }
    
}
