package com.vdf.assessment.garage.model;

import com.vdf.assessment.garage.v2.Level;
import java.util.Objects;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ParkingSpot {
  
  private Vehicle vehicle;
  private VehicleSize spotSize;
  private Integer row;
  private Integer spotNumber;
  private Level level;

  public ParkingSpot(Level lvl, int r, int n, VehicleSize sz) {
    level = lvl;
    row = r;
    spotNumber = n;
    spotSize = sz;
  }

  public boolean isAvailable() {
    return vehicle == null;
  }
  /* Checks if the spot is big enough for the vehicle (and is available). This compares
   * the SIZE only. It does not check if it has enough spots. */
  public boolean canFitVehicle(Vehicle vehicle) {
    return isAvailable() && vehicle.canFitInSpot(this);
  }
  /* Park vehicle in this spot. */
  public boolean park(Vehicle v) {
    if (!canFitVehicle(v)) {
      return false;
    }
    vehicle = v;
    vehicle.parkInSpot(this);
    return true;
  }
  /* Remove vehicle from spot, and notify level that a new spot is available */
  public void removeVehicle() {
    level.spotFreed();
    log.debug("{}. spot removed for {}",this.getSpotNumber(),vehicle.licensePlate);
    vehicle = null;

  }



  public void print() {
    if (vehicle != null) {
      vehicle.print();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParkingSpot that = (ParkingSpot) o;
    return row == that.row && spotNumber == that.spotNumber && vehicle.equals(that.vehicle)
        && spotSize == that.spotSize && level.getFloor()==that.level.getFloor();
  }

  @Override
  public int hashCode() {
    return Objects.hash( spotSize, spotNumber, level.getFloor());
  }
}