package aps.userStation;

import aps.car.CarModel;
import aps.car.CarModelManager;
import aps.elevator.Elevator;
import aps.floor.ParkingBay;
import aps.floor.ParkingBayManager;

/**
 * The {@link UserStationModel}.
 * <p>
 * This class is responsible for providing customer actions within the Automatic
 * Parking Simulator. When a car arrives at the turntable, the customer will
 * activate this machine to call the elevator to begin the cycle of loading the
 * car.
 * <p>
 * When a customer picks up a car, they will provide the details of the car
 * and will call the elevator to go and pick up the vehicle.
 * <p>
 * @author szeyick
 */
public class UserStationModel {
    
    /**
     * An instance of the elevator.
     */
    private Elevator elevator;
    
    /**
     * Constructor.
     */
    public UserStationModel() {

    }
    
    /**
     * Pick up a car.
     */
    public void pickupCar() {
        
    }
    
    /**
     * Park a car.
     * @param carModel - The car to park in the parking lot.
     */
    public void parkCar(CarModel carModel) {
        // When parking a car, the turntable will invoke the user station
        // 
        // The car will be added to the CarModelManager, but will need to be
        // added to a bay, the bay will need to be designated by
        // Direction (North or South), Bay Number and Floor.
        ParkingBayManager manager = ParkingBayManager.getManager();
        ParkingBay parkingBay = manager.getFreeBay();
        if (parkingBay != null) {
            parkingBay.setCarModel(carModel);
            CarModelManager carManager = CarModelManager.getModelManager();
            carManager.setCurrentCarModel(carModel);
            carManager.addCarToFloor(parkingBay.getFloorNumber(), carModel);
            
            // Load the car into the elevator.
            elevator.moveToFloor(0);
        }
    }
}
