package aps.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * The {@link Config}.
 * <p>
 * This class is responsible for managing the offline defined configuration
 * parameters for the Automatic Parking Simulator (APS)
 * <p>
 * @author szeyick
 * StudentID - 1763662
 */
public class Config {
    
    /***
     * The name of the configuration file.
     */
    private static final String CONFIG_FILE_NAME = "APS.properties";
    
    /***
     * An singleton instance of the {@link Config}
     */
    private static Config config;
    
    /**
     * The number of floors defined in the configuration. 
     */ 
    public int NF;
    
    /***
     * The turntable Y value
     */ 
    public double TURNTABLEY;

    /**
     * The turntable X value 
     */
    public double TURNTABLEX;
    
    /**
     * The width of a single bay.
     */
    public double BAY_WIDTH;
    
    /**
     * The length of a single bay. 
     */
    public double BAY_LENGTH;
    
    /**
     * The Y point defining the centre  of the aisle.
     */ 
    public double AISLE_CENTRE_Y;
    
    /**
     * The number of bays located north of the aisle. 
     */
    public int NUMBER_OF_BAYS_NORTH;
    
    /**
     * The number of bays located south of the aisle.
     */
    public int NUMBER_OF_BAYS_SOUTH;
    
    /**
     * The X coordinate of the centre of the first bay south of the 
     * aisle.
     */
    public double SOUTH_BAY_CENTER_X;
    
    /**
     * The X coordinate of the centre of the lift.
     */ 
    public double LIFT_CENTRE_X;
    
    /***
     * The properties object containing the offline defined values.
     */ 
    private Properties properties;
    
    /***
     * Private constructor.
     */ 
    private Config() {
        readConfiguration();
        initialiseProperties();
    }
    
    /***
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
    
    /***
     * Initialise the properties from the configuration.
     */
    private void initialiseProperties() {
        NF = getIntProperty("NF", 5);
        TURNTABLEY = getDoubleProperty("turnTableY", 5.5);
        TURNTABLEX = getDoubleProperty("turnTableX", 4.0);
        BAY_WIDTH = getDoubleProperty("BayWidth", 2.2);
        BAY_LENGTH = getDoubleProperty("BayLength", 5.75);
        NUMBER_OF_BAYS_NORTH = getIntProperty("NumberOfNorthBays", 5);
        NUMBER_OF_BAYS_SOUTH = getIntProperty("NumberOfSouthBays", 5);
        SOUTH_BAY_CENTER_X = getDoubleProperty("SouthBayCentre", 2.46);
        LIFT_CENTRE_X = getDoubleProperty("LiftCentre", 1.6);
    }
    
    /***
     * Retrieve a value from the properties file.
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
    
    /***
     * Retrieve a value from the properties file.
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
    
    /***
     * Retrieve a value from the properties file.
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
    
    /***
     * @return - The singleton instance of the class.
     */
    public static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }
}
