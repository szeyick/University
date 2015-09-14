package aps.floor;

import aps.config.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@link ParkingBayManager}.
 * <p>
 * This class represents the manager that manages all the parking bays. It is
 * used by the Automatic Parking Simulator to maintain a list of available bays
 * to park a car and the bay that a particular car is stored in.
 * <p>
 * @author szeyick
 */
public class ParkingBayManager {
    
    /**
     * An instance of the manager.
     */ 
    private static ParkingBayManager manager;
    
    /**
     * The north bays. 
     */ 
    private Map<Integer, List<ParkingBay>> northBays;
    
    /**
     * The south bays. 
     */
    private Map<Integer, List<ParkingBay>> southBays;
    
    /**
     * Constructor.
     */
    private ParkingBayManager() {
        northBays = new HashMap<Integer, List<ParkingBay>>();
        southBays = new HashMap<Integer, List<ParkingBay>>();
        initialiseParkingBays();
    }
    
    /**
     * Initialise the parking bays.
     */ 
    private void initialiseParkingBays() {        
        // Create the north and south bays.
        Config config = Config.getConfig();
        createBays(northBays, config.NUMBER_OF_BAYS_NORTH);
        createBays(southBays, config.NUMBER_OF_BAYS_SOUTH);
    }
    
    /**
     * Creating the parking bays for the entire parking complex.
     */
    private void createBays(Map<Integer, List<ParkingBay>> bay, int numberOfBays) {
        Config config = Config.getConfig();
        int numberOfFloors = config.NF;

        // Create the bays.
        for (int floorNo = 1; floorNo < numberOfFloors; floorNo++) {
            List<ParkingBay> parkingBayList = new ArrayList<ParkingBay>();
            for (int bayNo = 1; bayNo < numberOfBays; bayNo++) {
                ParkingBay parkingBay = new ParkingBay(Integer.valueOf(bayNo), Integer.valueOf(floorNo));
                parkingBayList.add(parkingBay);
            }
            bay.put(floorNo, parkingBayList);
        }
    }
    
    /**
     * @return the first available bay, null if there are no bays
     * free.
     */
    public ParkingBay getFreeBay() {
        ParkingBay freeBay = searchParkingBays(northBays);
        if (freeBay == null) {
            freeBay = searchParkingBays(southBays);
        }
        return freeBay;
    }
    
    /**
     * Search the parking bay for a free bay.
     */ 
    private ParkingBay searchParkingBays(Map<Integer, List<ParkingBay>> bay) {
        ParkingBay parkingBay = null;
        
        // Iterate through the north bays to find the first free bay.
        for (Integer floorNo: northBays.keySet()) {
            List<ParkingBay> baysList = northBays.get(floorNo);
            for (ParkingBay parkingBayTmp : baysList) {
                if (!parkingBayTmp.containsCar()) {
                    parkingBay = parkingBayTmp;
                    break;
                }
            }
            if (parkingBay != null) {
                break;
            }
        }
        return parkingBay;
    }
    
    /**
     * @return an instance of the parking manager.
     */
    public static ParkingBayManager getManager() {
        if (manager == null) {
            manager = new ParkingBayManager();
        }
        return manager;
    }
}
