package aps.floor;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * The ParkingLevelFloorView.
 * <p>
 * This class is responsible for generating the ground floor for a parking level
 * in the Automatic Parking Simulator.
 * <p>
 * This class will generate the ground floor off a configuration and return a
 * shape object that will represent the floor that cars will enter/exit.
 * <p>
 * @author szeyick StudentID - 1763652
 */
public class GroundLevelFloorView {

    /**
     * The shape object representing the ground floor.
     */
    private final GeneralPath groundFloorOutline;

    /**
     * Default constructor.
     */
    public GroundLevelFloorView() {
        groundFloorOutline = new GeneralPath();
        initialiseFloorPlan();
    }

    /**
     * Read the input from an external file and plot the shape.
     */
    private void initialiseFloorPlan() {
        int lineNumber = 0;
        try {
            FileReader fileReader = new FileReader("resources/floorplan_data.txt");
            LineNumberReader reader = new LineNumberReader(fileReader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                lineNumber = reader.getLineNumber();
                String lineChars[] = line.split("[ ,]");
                double x = Double.parseDouble(lineChars[1]);
                double y = Double.parseDouble(lineChars[2]);

                if ("m".equals(lineChars[0])) {
                    // move to
                    groundFloorOutline.moveTo(x, y);
                } else {
                    // line to
                    groundFloorOutline.lineTo(x, y);
                }
            }
            // Close the file.
            reader.close();
        } catch (Exception e) {
            System.out.println("Error - Cannot find input file");
            System.out.println("Error - At Line - " + lineNumber);
            e.printStackTrace();
        }
    }

    /**
     * @return - The shape representing the ground floor.
     */
    public Shape getGroundFloorPlan() {
        return groundFloorOutline;
    }
}
