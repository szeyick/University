package aps.car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The CarModelManager.
 * <p>
 * This class is responsible for managing all the car instances that are within
 * the Automatic Parking Simulator. It maintains a collection of
 * {@link CarModel} that will indicate which car is on which floor. In addition,
 * for cars that are being moved around the car park, it will manage the single
 * instance of it.
 * <p>
 * @author szeyick StudentID - 1763652.
 */
public class CarModelManager {

    /**
     * The singleton instance of the car model manager.
     */
    private static CarModelManager manager;

    /**
     * A collection that will map the car models to the floor that they appear
     * on.
     */
    private final Map<Integer, List<CarModel>> carModelMap;

    /**
     * The current car being moved in the car park.
     */
    private CarModel currentCarModel;

    /**
     * The number plate assigned to the car..
     */
    private int numberPlate;
    
    /**
     * Constructor.
     */
    private CarModelManager() {
        carModelMap = new HashMap<>();
        numberPlate = 0;
    }

    /**
     * Get all the cars parked on a particular floor.
     * <p>
     * @param floorNumber - The floor number to retrieve the cars for.
     * @return a list of cars for a given floor.
     */
    public List<CarModel> getCarModelsForFloor(Integer floorNumber) {
        return carModelMap.get(floorNumber);
    }

    /**
     * Add a car to the floor.
     * <p>
     * @param floorNumber - The floor to add the car to.
     * @param car - The car model to add.
     */
    public void addCarToFloor(Integer floorNumber, CarModel car) {
        List<CarModel> carList = carModelMap.get(floorNumber);
        if (carList == null) {
            carList = new ArrayList<>();
        }
        carList.add(car);
        carModelMap.put(floorNumber, carList);
    }

    /**
     * Remove a car from the floor it was on.
     * <p>
     * @param car - The car to update
     */
    public void removeCarFromFloor(CarModel car) {
        Integer carFloorToRemove = null;
        for (Integer floorNumber : carModelMap.keySet()) {
            List<CarModel> carList = carModelMap.get(floorNumber);
            for (CarModel carModelTmp : carList) {
                if (car.equals(carModelTmp)) {
                    carFloorToRemove = floorNumber;
                    break;
                }
            }
        }
        if (carFloorToRemove != null) {
            carModelMap.get(carFloorToRemove).remove(car);
        }
    }

    /**
     * Set the current car to be moved.
     * <p>
     * @param car - The current car to be operated on.
     */
    public void setCurrentCarModel(CarModel car) {
        currentCarModel = car;
        numberPlate++;
        // The number plates are just incrementing integers.
        currentCarModel.assignNumberPlate(String.valueOf(numberPlate));
    }

    /**
     * Remove the current car from being updated.
     */
    public void stopCurrentCarModel() {
        currentCarModel = null;
    }

    /**
     * Remove a car from the model manager. A car will be removed if it has
     * departed the car park.
     * <p>
     * @param car - The car to remove.
     */
    public void removeCarModel(CarModel car) {
        removeCarFromFloor(car);
        currentCarModel = null;
    }

    /**
     * @return the current car model being moved.
     */
    public CarModel getCurrentCarModel() {
        return currentCarModel;
    }

    /**
     * @return a car that has been parked. It will return the first car found on
     * a floor that contains a car. It will return null, if there is a car
     * already being moved.
     */
    public CarModel getParkedCarModel() {
        CarModel carModel = null;
        for (Integer floorNumber : carModelMap.keySet()) {
            List<CarModel> carList = carModelMap.get(floorNumber);
            for (CarModel carModelTmp : carList) {
                carModel = carModelTmp;
                break;
            }
        }
        currentCarModel = carModel;
        return currentCarModel;
    }
    
    /**
     * @return the instance of the model manager.
     */
    public static CarModelManager getModelManager() {
        if (manager == null) {
            manager = new CarModelManager();
        }
        return manager;
    }
}
