package com.vdf.assessment.garage.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Car extends Vehicle {

  
  public Car(String licensePlate,String color) {
    spotsNeeded = 1;
    size = VehicleSize.Small;
    this.licensePlate=licensePlate;
    this.color=color;
  }

  public boolean canFitInSpot(ParkingSpot spot) {
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
