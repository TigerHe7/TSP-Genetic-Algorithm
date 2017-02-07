package salesman.solver;

import java.util.LinkedList;

/**
 * All points that exist
 *
 * @author Tiger He
 */
public class Space {

    // all points
    int size;
    LinkedList<Point> points;

    /**
     * Create a space of random points between two bounds
     *
     * @param size number of points
     * @param bound1 first bound
     * @param bound2 second bound
     */
    Space(int size, Point bound1, Point bound2) {
        this.size = size;
        points = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            points.add(new Point(bound1, bound2));
        }
    }

    /**
     * @return String representation of space
     */
    @Override
    public String toString() {
        return points.toString();
    }

    /*
     Space(File file) {
     points = new LinkedList<>();
     setPoints(file);
     size = points.size();
     }

     private void setPoints(File file) {
     String pointLine;

     // Fill list with points from file
     try (Scanner nodeReader = new Scanner(file)) {
     while (nodeReader.hasNextLine()) {
     pointLine = nodeReader.nextLine();
     points.add(new Point(pointLine));
     }
     nodeReader.close();
     } catch (IOException ex) {
     System.err.println("Error reading from " + file.getName());
     }
     }
     */
}
