package com.vdf.assessment.garage.v2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vdf.assessment.garage.dto.ProcessResult;
import com.vdf.assessment.garage.model.Car;
import com.vdf.assessment.garage.model.Jeep;
import com.vdf.assessment.garage.model.Truck;
import com.vdf.assessment.garage.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractParkingLotServiceCoreJavaTest {

  private ParkingLotService parkingLotService;

  public AbstractParkingLotServiceCoreJavaTest() {
  }

  public void setParkingLot(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  @Test
  @Order(0)
  public void parkCar() {
    final Car vehicle = new Car("41CAR01", "BLUE");
    assertTrue(park(vehicle).isPStatus());
    assertTrue(vehicle.getSpots().equals(createSpotListBySize(vehicle.getSpotsNeeded())));
    print();
  }

  @Test
  @Order(1)
  public void parkJeep() {
    final Jeep vehicle = new Jeep("41JEEP01", "WHITE");
    assertTrue(park(vehicle).isPStatus());
    assertTrue(vehicle.getSpots().equals(createSpotListBySize(vehicle.getSpotsNeeded())));
    print();
  }

  @Test
  @Order(2)
  public void parkTruck() {
    final Truck vehicle = new Truck("41TRK01", "GREEN");
    assertTrue(park(vehicle).isPStatus());
    assertTrue(vehicle.getSpots().equals(createSpotListBySize(vehicle.getSpotsNeeded())));
  }

  @Test
  @Order(3)
  public void duplicateParkDetection() {
    final Truck vehicle = new Truck("41TRK01", "GREEN");
    assertTrue(park(vehicle).isPStatus());
    print();
    final UnsupportedOperationException ex = assertThrows(
        UnsupportedOperationException.class, () -> {
          park(vehicle);
        });
    assertTrue(ex.getMessage().contains("41TRK01"));
    print();
    assertTrue(vehicle.getSpots().equals(createSpotListBySize(vehicle.getSpotsNeeded())));
  }

  @Test
  @Order(4)
  public void parkAndUnParkVehicles() {
    final Truck trucks = new Truck("41TRK01", "GREEN");
    final Truck trucks2 = new Truck("41TRK02", "RED");
    final Jeep jeep = new Jeep("41JEEP01", "WHITE");
    final Car car = new Car("41CAR01", "BLUE");
    final Car car2 = new Car("41CAR02", "RED");
    final Car car3 = new Car("41CAR03", "WHITE");
    final Car car4 = new Car("41CAR04", "PURPLE");
    final Car car5 = new Car("41CAR05", "PURPLE");
    assertTrue(park(trucks).isPStatus());
    print();
    assertTrue(park(trucks2).isPStatus());
    print();
    assertTrue(park(jeep).isPStatus());
    print();
    unpark(trucks);
    print();
    assertTrue(park(car).isPStatus());
    print();
    assertTrue(park(car2).isPStatus());
    print();
    assertThrows(
        UnsupportedOperationException.class, () ->
            park(trucks)
    );
    print();
    unpark(trucks2);
    print();
    assertTrue(park(trucks).isPStatus());
    assertTrue(park(car3).isPStatus());
    print();
    assertTrue(park(car4).isPStatus());
    print();
    assertThrows(
        UnsupportedOperationException.class, () ->
            park(car5));
    print();
  }

  private void print() {
    log.debug("-------start-------");
    parkingLotService.print(new ArrayList<>());
    log.debug("-------end-------");
  }

  private ProcessResult park(Vehicle vehicle) {
    return parkingLotService.parkVehicle(vehicle);
  }

  private void unpark(Vehicle vehicle) {
    parkingLotService.unParkVehicle(vehicle);
  }

  private List<Integer> createSpotListBySize(int size) {
    List<Integer> checkList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      checkList.add(i);
    }
    return checkList;
  }

}