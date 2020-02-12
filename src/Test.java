import java.io.FileNotFoundException;

/**
 * I am using a simple test.csv file to test all function.
 * you can comment the test and delete the last comment to test
 */
public class Test {
    public static void main(String arg[]) throws FileNotFoundException {
        AirlineConnections airFlight = new AirlineConnections();
        //debug method
        //airFlight.printGraph();
        //using my own simple file to test it. It show all function clearly

        System.out.println("There are "+airFlight.totalCount()+" flight routes.");
        System.out.println("There are "+airFlight.numPort()+" airport.");
        //TO SHOW REMOVE FLIGHT FUNCTION****************
        System.out.println(airFlight.findFlight("JFK","YVR"));
        System.out.println(airFlight.cheapestFlight("JFK","YVR"));
        System.out.println(airFlight.cheapestFlight("SNU","YEG"));
        System.out.println(airFlight.cheapestFlight("YXE","YYG"));
    }
}
