package com.vdf.assessment.garage.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class Vehicle implements Comparable<Vehicle> {
  
  protected int spotsNeeded;
  protected VehicleSize size;
  protected String licensePlate;  // id for a vehicle
  protected String color;

  protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<>(); // id for parking where may occupy multi

  public int getSpotsNeeded() {
    return spotsNeeded;
  }

  public VehicleSize getSize() {
    return size;
  }

  /* Park vehicle in this spot (among others, potentially) */
  public void parkInSpot(ParkingSpot spot) {
    parkingSpots.add(spot);
  }

  /* Remove car from spot, and notify spot that it's gone */
  public void clearSpots() {
    log.debug("method called");
    for (int i = 0; i < parkingSpots.size(); i++) {
      parkingSpots.get(i).removeVehicle();
    }
    parkingSpots.clear();
  }
  public List<Integer> getSpots(){
    return this.parkingSpots.stream().map(parkingSpot -> parkingSpot.getSpotNumber()).collect(
        Collectors.toList());
  }

  public List<Integer> getSpotsIncrised(){
    return this.parkingSpots.stream().map(parkingSpot -> parkingSpot.getSpotNumber()+1).collect(
        Collectors.toList());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder(" ");
    sb.append(licensePlate).append(' ');
    sb.append(color).append(' ');
    sb.append(getSpotsIncrised());
    return sb.toString();
  }
  //this need to be implement in subclass
  public abstract boolean canFitInSpot(ParkingSpot spot);
  public abstract String print();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    /*if (o == null || getClass() != o.getClass()) {
      return false;
    }*/
    Vehicle vehicle = (Vehicle) o;
    return spotsNeeded == vehicle.spotsNeeded && size == vehicle.size && licensePlate
        .equals(vehicle.licensePlate) && color.equals(vehicle.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(spotsNeeded, size, licensePlate, color);
  }

  @Override
  public int compareTo(Vehicle o) {
    return this.parkingSpots.get(0).getSpotNumber().compareTo(o.parkingSpots.get(0).getSpotNumber());
  }
}