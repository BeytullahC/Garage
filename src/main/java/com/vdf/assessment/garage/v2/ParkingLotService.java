package com.vdf.assessment.garage.v2;

import com.vdf.assessment.garage.dto.ProcessResult;
import com.vdf.assessment.garage.model.Vehicle;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ParkingLotService {

  private final Level level;

  /*
  *Park the vehicle in a spot (or multiple spots)
  *@return false in @ProcessResult.pResult if failed with @ProcessResult.message
  * @throws UnsupportedOperationException if Duplicate Plate Detected from Level.parkVehicle
  */
  public ProcessResult parkVehicle(Vehicle vehicle) {
    final ProcessResult processResult = new ProcessResult();
    if (level.parkVehicle(vehicle)) {
      final int size = vehicle.getSpots().size();
      final String suffix = size == 1 ? " slot" : " slots";
      String message = "Allocated " + size + suffix;
      log.debug(message);
      processResult.setPStatus(true);
      processResult.setMessage(message);
    } else {
      throw new UnsupportedOperationException("Garage is full.");
    }
    return processResult;
  }

  // unPark the vehicle
  public void unParkVehicle(Vehicle vehicle) {
    log.debug("method called");
    
    vehicle.clearSpots();
  }

  public void print(List<String> result) {
    log.debug("method called");
    level.print(result);
  }
}
