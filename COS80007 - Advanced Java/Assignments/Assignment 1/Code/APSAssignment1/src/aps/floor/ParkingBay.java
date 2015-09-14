package aps.floor;

import aps.car.CarModel;

/**
 * The {@link ParkingBay}.
 * <p>
 * This class represents the parking bay. A parking bay holds information regarding
 * the car parked in it, whether it has a car parked in it, its direction, and bay
 * number.
 * <p>
 * Each floor will have the same number of parking bays.
 * <p>
 * @author szeyick
 */
public class ParkingBay {
    
    /**
     * Boolean representing whether a bay currently holds a car.
     */
    private boolean containsCar;
    
    /**
     * A reference to the car parked in this bay.
     */
    private CarModel car;
    
    /**
     * The bay number.
     */
    private Integer bayNumber;
    
    /**
     * The floor number.
     */ 
    private Integer floorNumber;
    
    /**
     * The enum representing whether the bay is in the north or south.
     */
    public enum direction {
        NORTH, SOUTH;
    }
    
    /**
     * Constructor.
     * @param bayNumberTmp - The bay number assigned to this car.
     * @param floorNoTmp  - The floor that this bay belongs to.
     */
    public ParkingBay(Integer bayNumberTmp, Integer floorNoTmp) {
        containsCar = false;
        floorNumber = floorNoTmp;
        bayNumber = bayNumberTmp;
    }
    
    /**
     * The car currently parked in this bay.
     * @param carModel - The car in this bay.
     */ 
    public void setCarModel(CarModel carModel) {
        this.car = carModel;
        containsCar = false;
    }
    
    /**
     * @return the car parked in this bay. 
     */
    public CarModel getCarModel() {
        return this.car;
    }
    
    /**
     * @return the car in this parking bay and remove it.
     */ 
    public CarModel removeCarModel() {
        containsCar = false;
        CarModel carModelTmp = car;
        car = null;
        return carModelTmp;
    }
    
    /**
     * @param true if the bay contains a car, false otherwise.
     */
    public boolean containsCar() {
        return containsCar;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public int getBayNumber() {
        return bayNumber;
    }
}
