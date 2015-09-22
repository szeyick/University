package aps.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The Config.
 * <p>
 * This class is responsible for managing the offline defined configuration
 * parameters for the Automatic Parking Simulator (APS)
 * <p>
 * @author szeyick StudentID - 1763662
 */
public class Config {

    /**
     * The name of the configuration file.
     */
    private static final String CONFIG_FILE_NAME = "resources/APS.properties";

    /**
     * An singleton instance of the {@link Config}
     */
    private static Config config;

    /**
     * The number of floors defined in the configuration.
     */
    public int NF;

    /**
     * The turntable Y value
     */
    public float TURNTABLEY;

    /**
     * The turntable X value
     */
    public float TURNTABLEX;

    /**
     * The width of a single bay.
     */
    public float BAY_WIDTH;

    /**
     * The length of a single bay.
     */
    public float BAY_LENGTH;

    /**
     * The Y point defining the centre of the aisle.
     */
    public float AISLE_CENTRE_Y;

    /**
     * The number of bays located north of the aisle.
     */
    public int NUMBER_OF_BAYS_NORTH;

    /**
     * The number of bays located south of the aisle.
     */
    public int NUMBER_OF_BAYS_SOUTH;

    /**
     * The X coordinate of the centre of the first bay south of the aisle.
     */
    public float SOUTH_BAY_CENTER_X;

    /**
     * The X coordinate of the centre of the lift.
     */
    public float LIFT_CENTRE_X;
    
    /**
     * The length of the elevator.
     */
    public float LIFT_LENGTH;
    
    /**
     * The width of the elevator.
     */
    public float LIFT_WIDTH;

    /**
     * The diameter of the turntable.
     */
    public float TURNTABLE_DIAMETER;

    /**
     * The X coordinate centre of the turntable.
     */
    public float TURNTABLE_CENTER_X;

    /**
     * The period in milliseconds that the timer is to trigger an update.
     */
    public int TIMER_PERIOD;
    
    /**
     * The period in milliseconds that it takes the elevator door
     * to open/close.
     */ 
    public int ELEVATOR_DOOR_PERIOD;

    /**
     * The length of the car.
     */
    public float CAR_LENGTH;

    /**
     * The width of the car.
     */
    public float CAR_WIDTH;
    
    /**
     * The width of the shuttle.
     */ 
    public float SHUTTLE_WIDTH;
    
    /**
     * The length of the shuttle.
     */
    public float SHUTTLE_LENGTH;
    
    /**
     * The period in milliseconds it takes to lock the trolley.
     */
    public int TROLLEY_LOCK_PERIOD;

    /**
     * *
     * The properties object containing the offline defined values.
     */
    private Properties properties;

    /**
     * Private constructor.
     */
    private Config() {
        readConfiguration();
        initialiseProperties();
    }

    /**
     * *
     * Read the offline configuration into the properties file.
     */
    private void readConfiguration() {
        properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(CONFIG_FILE_NAME);

            // Load the properties file.
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Error Reading Input File");
            e.printStackTrace();
        }
    }

    /**
     * *
     * Initialise the properties from the configuration.
     */
    private void initialiseProperties() {
        NF = getIntProperty("NF", 5);
        TURNTABLEY = getFloatProperty("turnTableY", 5.5f);
        TURNTABLEX = getFloatProperty("turnTableX", 4.0f);
        BAY_WIDTH = getFloatProperty("BayWidth", 2.2f);
        BAY_LENGTH = getFloatProperty("BayLength", 5.75f);
        NUMBER_OF_BAYS_NORTH = getIntProperty("NumberOfNorthBays", 5);
        NUMBER_OF_BAYS_SOUTH = getIntProperty("NumberOfSouthBays", 5);
        SOUTH_BAY_CENTER_X = getFloatProperty("SouthBayCentre", 2.46f);
        LIFT_CENTRE_X = getFloatProperty("LiftCentre", 1.6f);
        TURNTABLE_DIAMETER = getFloatProperty("TurntableDiameter", 5.75f);
        TURNTABLEX = getFloatProperty("TurntableCenterX", 3.4f);
        TIMER_PERIOD = getIntProperty("TimerPeriod", 50);
        CAR_LENGTH = getFloatProperty("CarLength", 5.0f);
        CAR_WIDTH = getFloatProperty("CarWidth", 2.0f);
        LIFT_LENGTH = getFloatProperty("LiftLength", 6.0f);
        LIFT_WIDTH = getFloatProperty("LiftWidth", 5.0f);
        ELEVATOR_DOOR_PERIOD = getIntProperty("ElevatorDoorPeriod", 100);
        SHUTTLE_LENGTH = getFloatProperty("ShuttleLength", 5.75f);
        SHUTTLE_WIDTH = getFloatProperty("ShuttleWidth", 0.5f);
        TROLLEY_LOCK_PERIOD = getIntProperty("TrolleyLockPeriod", 100);
    }

    /**
     * *
     * Retrieve a value from the properties file.
     *
     * @param name - The name of the property.
     * @param defaultValue - The default value.
     * @return the value matching the property, or default otherwise.
     */
    private int getIntProperty(String name, int defaultValue) {
        int propertyValue = defaultValue;
        if (properties.getProperty(name) != null) {
            propertyValue = Integer.parseInt(properties.getProperty(name));
        }
        return propertyValue;
    }

    /**
     * Retrieve a value from the properties file.
     * <p>
     * @param name - The name of the property.
     * @param defaultValue - The default value.
     * @return the value matching the property, or default otherwise.
     */
    private double getDoubleProperty(String name, double defaultValue) {
        double propertyValue = defaultValue;
        if (properties.getProperty(name) != null) {
            propertyValue = Double.parseDouble(properties.getProperty(name));
        }
        return propertyValue;
    }

    /**
     * Retrieve a value from the properties file.
     * <p>
     * @param name - The name of the property.
     * @param defaultValue - The default value.
     * @return the value matching the property, or default otherwise.
     */
    private float getFloatProperty(String name, float defaultValue) {
        float propertyValue = defaultValue;
        if (properties.getProperty(name) != null) {
            propertyValue = Float.parseFloat(properties.getProperty(name));
        }
        return propertyValue;
    }

    /**
     * *
     * Retrieve a value from the properties file.
     *
     * @param name - The name of the property.
     * @param defaultValue - The default value.
     * @return the value matching the property, or default otherwise.
     */
    private String getStringProperty(String name, String defaultValue) {
        String propertyValue = defaultValue;
        if (properties.getProperty(name) != null) {
            propertyValue = properties.getProperty(name);
        }
        return propertyValue;
    }

    /**
     * *
     * @return - The singleton instance of the class.
     */
    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }
}
