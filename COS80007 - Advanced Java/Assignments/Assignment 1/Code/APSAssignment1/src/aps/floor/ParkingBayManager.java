package aps.floor;

import aps.car.CarModel;
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
 * StudentID - 1763652
 */
public class ParkingBayManager {

    /**
     * An instance of the manager.
     */
    private static ParkingBayManager manager;

    /**
     * The bay currently allocated to a car being moved.
     */
    private ParkingBay currentDesignatedBay;

    /**
     * The north bays.
     */
    private final Map<Integer, List<ParkingBay>> northBays;

    /**
     * The south bays.
     */
    private final Map<Integer, List<ParkingBay>> southBays;
    
    /**
     * Constructor.
     */
    private ParkingBayManager() {
        northBays = new HashMap<>();
        southBays = new HashMap<>();
        currentDesignatedBay = null;
        initialiseParkingBays();
    }

    /**
     * Initialise the parking bays.
     */
    private void initialiseParkingBays() {
        // Create the north and south bays.
        Config config = Config.getConfig();
        createBays(northBays, config.NUMBER_OF_BAYS_NORTH, ParkingBayDirection.NORTH);
        createBays(southBays, config.NUMBER_OF_BAYS_SOUTH, ParkingBayDirection.SOUTH);
    }

    /**
     * Creating the parking bays for the entire parking complex.
     */
    private void createBays(Map<Integer, List<ParkingBay>> bay, int numberOfBays, ParkingBayDirection direction) {
        Config config = Config.getConfig();
        int numberOfFloors = config.NF;

        // Create the bays.
        for (int floorNo = 1; floorNo < numberOfFloors; floorNo++) {
            List<ParkingBay> parkingBayList = new ArrayList<>();
            for (int bayNo = 1; bayNo <= numberOfBays; bayNo++) {
                ParkingBay parkingBay = new ParkingBay(Integer.valueOf(bayNo), Integer.valueOf(floorNo), direction);
                parkingBayList.add(parkingBay);
            }
            bay.put(floorNo, parkingBayList);
        }
    }

    /**
     * @return the first available bay, null if there are no bays free.
     */
    public ParkingBay getFreeBay() {
        if (currentDesignatedBay == null) {
            ParkingBay freeBay = searchParkingBays(southBays);
            if (freeBay == null) {
                freeBay = searchParkingBays(northBays);
            }
            currentDesignatedBay = freeBay;
        }
        return currentDesignatedBay;
    }
    
    /**
     * Car has successfully been parked in the allocated bay.
     */ 
    public void carParkedInBay() {
        currentDesignatedBay = null;
    }
    
    /**
     * @param carModel - The car model to look for.
     * @return the parking bay information for the a given car.
     */ 
    public ParkingBay getParkingBayForCar(CarModel carModel) {
        List<ParkingBay> northBaysList = northBays.get(carModel.getFloor());
        List<ParkingBay> southBaysList = southBays.get(carModel.getFloor());
        
        ParkingBay parkingBay = searchBayForCar(northBaysList, carModel);
        if (parkingBay == null) {
            parkingBay = searchBayForCar(southBaysList, carModel);
        }
        return parkingBay;
    }
    
    /**
     * Search all the bays in the car park for a given car.
     * @param baysList - The list of bays on a floor.
     * @param carModel - The car model to find.
     * @return the bay that the car is in, null otherwise.
     */
    private ParkingBay searchBayForCar(List<ParkingBay> baysList, CarModel carModel) {
        ParkingBay parkingBay = null;
        for (ParkingBay bay : baysList) {
            if (bay.getCarModel() != null && bay.getCarModel().equals(carModel)) {
                parkingBay = bay;
                break;
            }
        }
        return parkingBay;
    }

    /**
     * Search the parking bay for a free bay.
     * @param bay - a map of bays.
     */
    private ParkingBay searchParkingBays(Map<Integer, List<ParkingBay>> bay) {
        ParkingBay parkingBay = null;

        // Iterate through the north bays to find the first free bay.
        for (Integer floorNo : bay.keySet()) {
            List<ParkingBay> baysList = bay.get(floorNo);
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
