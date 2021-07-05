package com.vdf.assessment.garage.resources;

import com.vdf.assessment.garage.VehicleFactory;
import com.vdf.assessment.garage.dto.ProcessResult;
import com.vdf.assessment.garage.dto.VehicleDto;
import com.vdf.assessment.garage.model.Vehicle;
import com.vdf.assessment.garage.v2.Level;
import com.vdf.assessment.garage.v2.ParkingLotService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GarageResource {
  private final ParkingLotService service;
  private final Level level;
  @Operation(summary = "Park the vehicle in a spot (or multiple spots)")
  @PutMapping
  public ResponseEntity park(@RequestBody @Valid  VehicleDto vehicle){
    final Vehicle vehicleEntity = VehicleFactory
        .getVehicle(vehicle.getVehicleType(), vehicle.getColor().toUpperCase(), vehicle.getLicencePlate().toUpperCase());
    final ProcessResult processResult = service.parkVehicle(vehicleEntity);
    return ResponseEntity.ok(processResult.getMessage());
  }
  @Operation(summary = "Leave the vehicle by LicencePlate")
  @DeleteMapping(path = "/leave")
  public ResponseEntity leaveByLicencePlate(@RequestParam  @NotBlank @Pattern(regexp = VehicleDto.regexPlate) String licencePlate){
    service.unParkVehicle(level.findVehicle(licencePlate));
    return ResponseEntity.noContent().build();
  }
  @Operation(summary = "Leave the vehicle by parkingSpotNumber")
  @DeleteMapping(path = "/leave/{parkingSpotNumber}")
  public ResponseEntity leaveByParkingSpot(@PathVariable @NotBlank @Min(1) Integer parkingSpotNumber){
    service.unParkVehicle(level.findVehicleBySpotNumber(parkingSpotNumber));
    return ResponseEntity.noContent().build();
  }
  @Operation(summary = "Get Garage Status")
  @GetMapping
  public ResponseEntity status(){
    final List<String> results = new ArrayList<>();
    service.print(results);
    return  ResponseEntity.ok(results);
  }

}
