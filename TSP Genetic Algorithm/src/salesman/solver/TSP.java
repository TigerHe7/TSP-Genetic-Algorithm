package salesman.solver;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Gives an approximate solution to the traveling salesman problem using a
 * genetic algorithm or "bogo" check (random generation)
 *
 * Also gives an exact solution by exhausting every possible path for a
 * given set of points (NOT WORKING YET)
 *
 * @author Tiger He
 */
public class TSP extends JFrame {

    // scenario settings
    public static final int NUM_PATHWAYS = 200;
    public static final int NUM_POINTS = 100;

    // very important: (higher value tends to improve shortest path)
    public static final int NUM_GENETIC_GENERATIONS = 1000;

    // times bogo generates and compares a new random path
    public static final int NUM_BOGO_TRIES = 100000;

    // delay to make the viewing experience that much sweeter
    public static final int DELAY_EVOLUTION_DISPLAY = 0;
    public static final int DELAY_ALGORITHMS = 1000;

    // size of window
    public static Dimension size;

    public static void main(String[] args) {

        // TSP GUI
        TSP tsp = new TSP();

        // all points which exist
        Space space;
        Path path;

        // population of paths
        Population population;

        Point lowerBound = new Point(50, 50);
        Point upperBound = new Point(950, 900);

        // random scenario
        space = new Space(NUM_POINTS, lowerBound, upperBound);
        path = new Path(space);
        tsp.displayScreen.allPoints = space.points;

        // random starting population
        population = new Population(space, NUM_PATHWAYS);

        // starting info
        System.out.println("The points include: " + space.toString());
        System.out.println("Initial distance: " + path.getDistance());
        System.out.println("");

        // evolve population to have paths of a shorter distance
        evolve(space, population, NUM_GENETIC_GENERATIONS, tsp);

        delay(DELAY_ALGORITHMS);

        // bogo search for best path
        // bogo(space, tsp);

        // exhaustive search doesnt work yet
        // exhaust(space, tsp);
    }

    /**
     * Reset everything *DOESN'T WORK YET*
     *
     * @param space to be reset
     * @param path to be reset
     * @param population to be reset
     * @param lowerBound lower bound of points in space
     * @param upperBound upper bound of points in space
     * @param tsp GUI with displayed points to be reset
     */
    public static void reset(Space space, Path path, Population population, Point lowerBound, Point upperBound, TSP tsp) {

        // reset everything
        space = new Space(NUM_POINTS, lowerBound, upperBound);
        path = new Path(space);
        population = new Population(space, NUM_PATHWAYS);
        tsp.displayScreen.allPoints = space.points;

        // new scenario
        System.out.println("The points include: " + space.toString());
        System.out.println("Initial distance: " + path.getDistance());
        System.out.println("");
    }

    /**
     * Evolve population to have paths of a smaller distance
     *
     * @param space all points which exist
     * @param population population being evolved
     * @param generations how many generations of evolution to undergo
     * @param tsp GUI to display paths
     */
    public static void evolve(Space space, Population population, int generations, TSP tsp) {

        System.out.println("BEGINNING EVOLUTION");

        // repeat for each generation
        for (int i = 0; i < generations; i++) {

            // evolve generation
            population = GeneticAlgorithm.evolvePop(population);

            // display each path in population
            for (int ii = 0; ii < NUM_PATHWAYS; ii++) {
                tsp.displayScreen.pathway = population.get(ii).points;
                delay(DELAY_EVOLUTION_DISPLAY);
            }
        }

        // distance of shortest path
        System.out.println("Genetic best: " + population.getFittest().getDistance());

        System.out.println("EVOLUTION FINISHED");
        System.out.println("");
    }

    /**
     * Find the shortest path by checking every possibility
     *
     * @param space all points which exist
     * @param tsp GUI to display the shortest path
     */
    public static void exhaust(Space space, TSP tsp) {

        System.out.println("BEGINNING EXHAUSTIVE SEARCH");

        // 
        Path bestPath = BruteAlgorithm.exhaustBest(space);

        // display shortest path
        tsp.displayScreen.pathway = bestPath.points;

        // display distance of shortest path
        System.out.println("Exhause best: " + bestPath.getDistance());

        System.out.println("EXHAUSTIVE SEARCH FINISHED");
        System.out.println("");
    }

    /**
     * Find a shorter path by randomly generating paths and keeping the
     * shortest on each time
     *
     * @param space all points which exist
     * @param tsp GUI to display shortest path generated
     */
    public static void bogo(Space space, TSP tsp) {

        System.out.println("BEGINNING BOGO");

        // find the shortest path produced by randomly checking them
        Path bogo = BruteAlgorithm.bogoBest(space, NUM_BOGO_TRIES);

        // display this paths
        tsp.displayScreen.pathway = bogo.points;

        // display distance of this path
        System.out.println("Bogo best: " + bogo.getDistance());

        System.out.println("BOGO FINNISHED");
    }

    /**
     * Delay program
     *
     * @param n milliseconds to display
     */
    public static void delay(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    // Panel to display points and paths
    public DisplayScreen displayScreen;

    /**
     * Instantiate GUI frame to display points and paths
     */
    public TSP() {
        super();
        displayScreen = new DisplayScreen(this);

        // set window properties
        super.setTitle("Traveling Salesman Simulator");
        size = new Dimension(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(size);
        setPreferredSize(size);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // begin with display screen
        setContentPane(displayScreen);
        pack();
    }

}
