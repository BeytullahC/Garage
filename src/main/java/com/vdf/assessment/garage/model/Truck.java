package com.vdf.assessment.garage.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class Truck extends Vehicle {
  
  public Truck(String licensePlate,String color) {
    super();
    spotsNeeded = 4;
    size = VehicleSize.Large;
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

  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
