package com.vdf.assessment.garage.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jeep extends Vehicle {

  public Jeep(String licensePlate, String color) {
    spotsNeeded = 2;
    size = VehicleSize.Medium;
    this.licensePlate = licensePlate;
    this.color = color;
  }

  public boolean canFitInSpot(ParkingSpot spot) {
    //return spot.getSize() == VehicleSize.Large || spot.getSize() == VehicleSize.Medium;
    return true;
  }

  public String print() {
    log.debug(this.toString());
    return this.toString();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName()+super.toString();
  }
}
