package com.vdf.assessment.garage.v2;

import com.vdf.assessment.garage.exceptions.ResourceNotFoundException;
import com.vdf.assessment.garage.model.ParkingSpot;
import com.vdf.assessment.garage.model.Vehicle;
import com.vdf.assessment.garage.model.VehicleSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/* Represents a level in a parking garage */

@Getter
public class Level {

  
  private int floor;
  private ParkingSpot[] spots;
  private int availableSpots = 0; // number of free spots
  private int SPOTS_PER_ROW;


  public Level(int flr, int num_rows, int spots_per_row) {
    log.debug("method called");
    floor = flr;
    int SPOTS_PER_ROW = spots_per_row;
    int numberSpots = 0;
    spots = new ParkingSpot[num_rows * spots_per_row];

    //init size for each spot in array spots
    for (int row = 0; row < num_rows; ++row) {
      for (int spot = 0; spot < spots_per_row; ++spot) {
        VehicleSize sz = VehicleSize.Small;
        spots[numberSpots] = new ParkingSpot(this, row, numberSpots, sz);
        numberSpots++;
      }
    }

    availableSpots = numberSpots;
  }

  /*
  *Try to find a place to park this vehicle. Return false if failed.
  * @throws UnsupportedOperationException if Duplicate Plate Detected
  */
  public boolean parkVehicle(Vehicle vehicle) {
    log.debug("method called");
    if (availableSpots() < vehicle.getSpotsNeeded()) {
      return false; // no enough spots
    }
    if (spots != null && findDuplicate(vehicle.getLicensePlate())) {
      throw new UnsupportedOperationException("Duplicate Licance Plate Detected for "+vehicle.getLicensePlate());
    }
    int spotNumber = findAvailableSpots(vehicle);
    if (spotNumber < 0) {
      return false;
    }
    return parkStartingAtSpot(spotNumber, vehicle);
  }

  /*
  *find a spot to park this vehicle.
  * @return index of spot, or -1 on failure.
  */
  private int findAvailableSpots(Vehicle vehicle) {
    log.debug("method called");
    int spotsNeeded = vehicle.getSpotsNeeded();
    int lastRow = -1;
    int spotsFound = 0;

    for (int i = 0; i < spots.length; i++) {
      ParkingSpot spot = spots[i];
      if (lastRow != spot.getRow()) {
        spotsFound = 0;
        lastRow = spot.getRow();
      }
      if (spot.canFitVehicle(vehicle)) {
        spotsFound++;
      } else {
        spotsFound = 0;
      }
      if (spotsFound == spotsNeeded) {
        return i - (spotsNeeded - 1); // index of spot
      }
    }
    return -1;
  }

  /* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */
  private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
    log.debug("method called");
    vehicle.clearSpots();

    boolean success = true;

    for (int i = spotNumber; i < spotNumber + vehicle.getSpotsNeeded(); i++) {
      success &= spots[i].park(vehicle);
    }

    availableSpots -= vehicle.getSpotsNeeded();
    return success;
  }

  /* When a car was removed from the spot, increment availableSpots */
  public void spotFreed() {
    log.debug("method called");
    availableSpots++;
  }

  public int availableSpots() {
    log.debug("method called");
    return availableSpots;
  }

  public void print(List<String> results) {
    log.debug("method called");
    int lastRow = -1;
    Set<Vehicle> spotSet = new HashSet<>();
    for (int i = 0; i < spots.length; i++) {
      ParkingSpot spot = spots[i];
      if (spot.getRow() != lastRow) {
        lastRow = spot.getRow();
      }
      if (spot.getVehicle() != null) {
        spotSet.add(spot.getVehicle());
      }
    }
    if (!spotSet.isEmpty()) {
      spotSet.stream().sorted(Vehicle::compareTo).forEach(e -> results.add(e.print()));
    }
  }

  public boolean findDuplicate(String licencePlate) {
    return Arrays.stream(spots).filter(e -> e != null && e.getVehicle() != null)
        .anyMatch(e -> e.getVehicle().getLicensePlate().equals(licencePlate));
  }

  public Vehicle findVehicleBySpotNumber(@NotNull @Min(1) int parkingSpotNumber) {
    final List<String> collect = Arrays.stream(spots).filter(
        e -> e != null && e.getVehicle() != null)
        .map(e -> e.getVehicle().getLicensePlate()).collect(Collectors.toList());
    final ArrayList<String> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(collect));
    log.error(listWithoutDuplicates.toString());

    if (listWithoutDuplicates!=null && !listWithoutDuplicates.isEmpty()) {
      return findVehicle(listWithoutDuplicates.get(parkingSpotNumber-1));
    }
    throw new ResourceNotFoundException("Parking Spot '" + parkingSpotNumber + "' is empty...");
  }

  public Vehicle findVehicle(String licencePlate) {
    final Optional<ParkingSpot> first = Arrays.stream(spots).filter(
        e -> e != null && e.getVehicle() != null && e.getVehicle().getLicensePlate()
            .equals(licencePlate))
        .findFirst();
    if (first.isPresent() && !first.isEmpty()) {
      return first.get().getVehicle();
    }
    throw new ResourceNotFoundException("Licence Plate '" + licencePlate + "' is not found...");
  }
}
