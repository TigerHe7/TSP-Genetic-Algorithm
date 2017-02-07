package salesman.solver;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * Display points and path connecting all of them
 *
 * @author Tiger He
 */
@SuppressWarnings("serial")
public class DisplayScreen extends JPanel {

    // all points
    LinkedList<Point> allPoints;

    // path between points
    LinkedList<Point> pathway;

    /**
     * Create display screen
     *
     * @param tsp JFrame to hold display screen
     */
    DisplayScreen(TSP tsp) {
        super(); // calls JPanel constructor
        setLayout(null); // no layout manager
        setPreferredSize(TSP.size); // set panel to be same size as window
        init();
    }

    /**
     * Initialize display screen variables
     */
    private void init() {
        allPoints = new LinkedList<>();
        pathway = new LinkedList<>();
    }

    /**
     * Display points and pathway graphically
     *
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawPoints(g);
        drawPath(g);

        repaint();

    }

    /**
     * Draw all points as circles
     *
     * @param g graphics object
     */
    private void drawPoints(Graphics g) {

        int x;
        int y;

        // draw first point in space as red
        if (allPoints.size() > 0) {
            g.setColor(Color.red);
            x = (int) Math.round(allPoints.get(0).x);
            y = (int) Math.round(allPoints.get(0).y);
            g.fillOval(x - 2, y - 2, 5, 5);
        }

        // draw rest of points in space as black
        g.setColor(Color.black);
        for (int i = 0; i < allPoints.size(); i++) {
            x = (int) Math.round(allPoints.get(i).x);
            y = (int) Math.round(allPoints.get(i).y);
            g.drawOval(x - 2, y - 2, 5, 5);
        }
    }

    /**
     * Draw pathway between all points
     *
     * @param g graphics object
     */
    private void drawPath(Graphics g) {

        int x;
        int y;

        int x2;
        int y2;

        // draw lines between each consecutive point in pathway to depict path
        for (int i = 0; i < pathway.size() - 1; i++) {
            x = (int) Math.round(pathway.get(i).x);
            y = (int) Math.round(pathway.get(i).y);
            x2 = (int) Math.round(pathway.get(i + 1).x);
            y2 = (int) Math.round(pathway.get(i + 1).y);
            g.drawLine(x, y, x2, y2);
        }

        // draw line from last point in pathway to first point
        if (pathway.size() > 0) {
            x = (int) Math.round(pathway.get(pathway.size() - 1).x);
            y = (int) Math.round(pathway.get(pathway.size() - 1).y);
            x2 = (int) Math.round(pathway.get(0).x);
            y2 = (int) Math.round(pathway.get(0).y);
            g.drawLine(x, y, x2, y2);
        }
    }
}
